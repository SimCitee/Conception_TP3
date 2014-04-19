package domaine;

import java.util.ArrayList;
import java.util.Date;

public class Contrat {
	private Client signataire;				// client associe au contrat
	private Vehicule vehicule;				// vehicule associe au contrat
	private Etat etat;						// location ou reservation
	private Date dateDebut;					// date ou debute le contrat
	private Date dateFin;					// date ou prend fin le contrat
	private boolean actif;					// indique si le contrat est actif ou termine
	private int MNT_DEPOT_GARANTIE = 200;	// montant du depot de garantie
	private ArrayList<Paiement> listePaiements;	// contient la liste des paiements effectues sur ce contrat
	
	/*
	 * Calcul du total de la facture (fait au moment de la location)
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
	public int calculerTotalFacture() {
		
		int total = vehicule.getTauxQuotidien() * getJourLocation();
		
		return total;
	}
	 
	/*
	 * Modifier les dates de debut et fin de la location
	 * Parametre : date du debut / date de fin de location
	 * Valeur de retour : aucune
	 */
	public void modifierDates(Date dateDebut, Date dateFin) {
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
	}
	
	/*
	 * Assigne un nouveau vehicule au contrat
	 * Parametre : le nouveau vehicule
	 * Valeur de retour : aucune
	 */
	public void modifierVehicule(Vehicule vehiculeChoisi) {
		this.vehicule = vehiculeChoisi;
	}
	
	/*
	 * Active ou desactive le contrat
	 * Parameter : aucun
	 * Valeur de retour : aucune
	 */
	public void changerStatut() {
		actif = !actif;
	}
	
	/*
	 * Fait la difference entre la date de fin et de debut de la location
	 * Donne le nombre de jours de location
	 * Parametre : aucun
	 * Valeur de retour : (entier) nombre de jours de location
	 */
	public int getJourLocation() {

		try {
			if (dateDebut != null && dateFin != null) {
				long differenceTemps = dateFin.getTime() - dateDebut.getTime();
				
				int nombreJours = (int) differenceTemps / (24 * 60 * 60 * 1000);
				return nombreJours;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;

	}
	
	public Client getSignataire() {
		return signataire;
	}
	public void setSignataire(Client signataire) {
		this.signataire = signataire;
	}
	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public ArrayList<Paiement> getListePaiements() {
		return listePaiements;
	}

	public void setListePaiements(ArrayList<Paiement> listePaiements) {
		this.listePaiements = listePaiements;
	}

	public Date getDateDebut() {
		return dateDebut;
	}
	public void setDateDebut(Date dateDebut) {
		this.dateDebut = dateDebut;
	}
	public Date getDateFin() {
		return dateFin;
	}
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}
	public boolean isActif() {
		return actif;
	}
	public void setActif(boolean actif) {
		this.actif = actif;
	}
	
}
