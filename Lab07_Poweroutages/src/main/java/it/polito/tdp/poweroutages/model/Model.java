package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	List<BlackOut> listaBest;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	public List<BlackOut> getBlackOutList(Nerc n,int anni,int ore){
		List<BlackOut> listaBlackOut=new ArrayList<BlackOut>(podao.getBlackOutList(n));
		List<BlackOut> parziale=new ArrayList<BlackOut>();
		int livello=0;
		cerca(parziale,listaBlackOut,livello,anni,ore);
		return listaBest;
	}

	private void cerca(List<BlackOut> parziale,List<BlackOut> listaBlackOut,int livello, int anni, int ore) {
		
		if(parziale.size()==listaBlackOut.size())
			listaBest=new ArrayList<>(parziale);
		
		for(int i=parziale.size();i<listaBlackOut.size();i++) {
			if(aggiuntaValida(parziale,listaBlackOut.get(i),livello,anni,ore)) {
				parziale.add(listaBlackOut.get(i));
				cerca(parziale,listaBlackOut,livello,anni,ore);
				parziale.remove(parziale.size()-1);
			}
		}
	}
	
	private boolean aggiuntaValida(List<BlackOut> parziale,BlackOut prova,int livello, int anni, int ore) {
		
		//sono al livello zero aggiungo
		if(parziale.size()==0)
			return true;
		else {
			//ho superato gli anni massimi
			Calendar in = Calendar.getInstance();
			Calendar fine=Calendar.getInstance();
			in.setTime(parziale.get(0).getInizio());
			fine.setTime(prova.fine);
		
			//ho superato le ore massime
			int htot=0;
			for(BlackOut b:parziale) {
				Calendar hi = Calendar.getInstance();
				Calendar hf=Calendar.getInstance();
				hi.setTime(b.inizio);
				hf.setTime(b.fine);
				int h=hf.HOUR-hi.HOUR;
				htot+=h;
			}
			Calendar hiprova= Calendar.getInstance();
			Calendar hfprova=Calendar.getInstance();
			hfprova.setTime(prova.fine);
			hiprova.setTime(prova.inizio);
			int hprova=hfprova.HOUR-hiprova.HOUR;
			htot+=hprova;
			if(htot<=ore && fine.YEAR-in.YEAR<=anni)
				return true;
		}
		return false;
	}

}
