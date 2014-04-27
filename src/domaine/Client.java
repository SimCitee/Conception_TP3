package domaine;

import java.util.ArrayList;

public class Client {

	private String noPermisConduire;
	private String nom;
	private String prenom;
	private String adresse;
	private String noTel;
	private String noCarteBancaire;
	private ArrayList<ConducteurSecondaire> listeConducteurSec;

	public Client(String noPermisConduire, String nom, String prenom, String adresse, String noTel, String noCarteBancaire, ArrayList<ConducteurSecondaire> listeConducteurSec) {
		this.noPermisConduire = noPermisConduire;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.noTel = noTel;
		this.noCarteBancaire = noCarteBancaire;
		this.listeConducteurSec = listeConducteurSec;
	}
	
	public String getConducteurSecFormate() {
		String ConducteurSecFormate = "";
		
		if(listeConducteurSec != null) {
			for(ConducteurSecondaire cs : listeConducteurSec) {
				ConducteurSecFormate += cs.getNoPermisConduire() + " ";
				ConducteurSecFormate += cs.getNom() + " ";
				ConducteurSecFormate += cs.getPrenom() + ", ";
			}
			
			ConducteurSecFormate = ConducteurSecFormate.substring(0, ConducteurSecFormate.length()-2);
		}
		else {
			ConducteurSecFormate = "Aucun";
		}
		
		return ConducteurSecFormate;
	}

	public String getNoPermisConduire() {
		return noPermisConduire;
	}

	public void setNoPermisConduire(String noPermisConduire) {
		this.noPermisConduire = noPermisConduire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNomComplet() {
		return this.prenom + " " + this.nom;
	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNoTel() {
		return noTel;
	}

	public void setNoTel(String noTel) {
		this.noTel = noTel;
	}

	public ArrayList<ConducteurSecondaire> getListeConducteurSec() {
		return listeConducteurSec;
	}

	public void setListeConducteurSec(ArrayList<ConducteurSecondaire> listeConducteurSec) {
		this.listeConducteurSec = listeConducteurSec;
	}
}
