package domaine;

import java.util.ArrayList;
import java.util.Random;

public class Vehicule {
	
	private int tauxQuotidien;
	private Integer identifiant;
	private Integer coteClassification;
	private Integer kilometrage;
	private String marque;
	private String modele;
	private Integer forceMoteur;
	private String transmission;
	private Integer nbPlaces;
	private String immatriculation;
	private Boolean estDisponible;
	private ArrayList<String> accessoires = new ArrayList<String>();
	
	public Vehicule() {
		Random randGenerator = new Random();
		tauxQuotidien += 50 + randGenerator.nextInt(250);		// definir le prix aleatoirement (pour fin de test)
		kilometrage = randGenerator.nextInt(100000);	// definir le kilometrage aleatoirement (pour fin de test)
	}
	
	public int getTauxQuotidien() {
		return tauxQuotidien;
	}

	public void setTauxQuotidien(int tauxQuotidien) {
		this.tauxQuotidien = tauxQuotidien;
	}

	
	public Integer getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(Integer identifiant) {
		this.identifiant = identifiant;
	}

	public Integer getCoteClassification() {
		return coteClassification;
	}

	public void setCoteClassification(Integer coteClassification) {
		this.coteClassification = coteClassification;
	}
	
	public Integer getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(Integer kilometrage) {
		this.kilometrage = kilometrage;
	}

	//Retourne le type de véhicule (String) en fonction de sa cote
	public String getTypeVehicule()
	{
		return TypeVehicule.getInstance().getType(coteClassification);
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public Integer getForceMoteur() {
		return forceMoteur;
	}

	public void setForceMoteur(Integer forceMoteur) {
		this.forceMoteur = forceMoteur;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public Integer getNbPlaces() {
		return nbPlaces;
	}

	public void setNbPlaces(Integer nbPlaces) {
		this.nbPlaces = nbPlaces;
	}

	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public Boolean getEstDisponible() {
		return estDisponible;
	}

	public void setEstDisponible(Boolean estDisponible) {
		this.estDisponible = estDisponible;
	}

	public ArrayList<String> getAccessoires() {
		return accessoires;
	}
	
	//Retourne une chaine formatté qui représente la liste d'Accessoires
	public String getAccessoiresFormate() {
		String acessoiresFormat = "";
		
		for(String s : accessoires)
			acessoiresFormat += s + ", ";
		
		//Enlève le dernier ", "
		if(acessoiresFormat.length() > 2)
			acessoiresFormat = acessoiresFormat.substring(0, acessoiresFormat.length()-2);
			
		return acessoiresFormat;
	}

	public void setAccessoires(ArrayList<String> accessoires) {
		this.accessoires = accessoires;
	}

	@Override
	public String toString() {
		return "Vehicule [tauxQuotidien=" + tauxQuotidien + ", Cote=" + coteClassification
				+ ", marque=" + marque + ", modele=" + modele + ", moteur="
				+ forceMoteur + ", transmission=" + transmission + ", nbPlaces="
				+ nbPlaces + ", immatriculation=" + immatriculation
				+ ", estDisponible=" + estDisponible + ", accessoires="
				+ accessoires + "]";
	}
	
	
}
