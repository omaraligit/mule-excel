package com.helpers;

public class Ligne {
	
	public String description, unite, qte, Punit, tva;

	public Ligne(String description, String unite, String qte, String punit, String tva) {
		super();
		this.description = description;
		this.unite = unite;
		this.qte = qte;
		Punit = punit;
		this.tva = tva;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnite() {
		return unite;
	}

	public void setUnite(String unite) {
		this.unite = unite;
	}

	public String getQte() {
		return qte;
	}

	public void setQte(String qte) {
		this.qte = qte;
	}

	public String getPunit() {
		return Punit;
	}

	public void setPunit(String punit) {
		Punit = punit;
	}

	public String getTva() {
		return tva;
	}

	public void setTva(String tva) {
		this.tva = tva;
	}
	
}
