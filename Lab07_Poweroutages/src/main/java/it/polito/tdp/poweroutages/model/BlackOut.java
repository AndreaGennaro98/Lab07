package it.polito.tdp.poweroutages.model;

import java.util.Date;

public class BlackOut {
	
	int NercId;
	int coinvolti;
	Date inizio;
	Date fine;
	
	public BlackOut(int nercId, int coinvolti, Date inizio, Date fine) {
		super();
		NercId = nercId;
		this.coinvolti = coinvolti;
		this.inizio = inizio;
		this.fine = fine;
	}

	public int getNercId() {
		return NercId;
	}

	public void setNercId(int nercId) {
		NercId = nercId;
	}

	public int getCoinvolti() {
		return coinvolti;
	}

	public void setCoinvolti(int coinvolti) {
		this.coinvolti = coinvolti;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + NercId;
		result = prime * result + coinvolti;
		result = prime * result + ((fine == null) ? 0 : fine.hashCode());
		result = prime * result + ((inizio == null) ? 0 : inizio.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackOut other = (BlackOut) obj;
		if (NercId != other.NercId)
			return false;
		if (coinvolti != other.coinvolti)
			return false;
		if (fine == null) {
			if (other.fine != null)
				return false;
		} else if (!fine.equals(other.fine))
			return false;
		if (inizio == null) {
			if (other.inizio != null)
				return false;
		} else if (!inizio.equals(other.inizio))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("BlackOut [NercId=%s, coinvolti=%s, inizio=%s, fine=%s]", NercId, coinvolti, inizio, fine);
	}
	
	
	
	

}
