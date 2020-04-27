package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.model.BlackOut;
import it.polito.tdp.poweroutages.model.Nerc;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public List<BlackOut> getBlackOutList(Nerc nerc) {

		String sql = "SELECT nerc_id, customers_affected, date_event_began, date_event_finished\r\n" + 
				"FROM poweroutages\r\n" + 
				"WHERE nerc_id=?\r\n" + 
				"ORDER BY date_event_began ASC";
		List<BlackOut> blackOutList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(nerc.getId(), 1);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				BlackOut b = new BlackOut(res.getInt("nerc_id"), res.getInt("customers_affected"), res.getDate("date_event_began"),res.getDate("date_event_finished"));
				blackOutList.add(b);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return blackOutList;
	}
	

}
