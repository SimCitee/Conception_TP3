package domaine;

import java.util.ArrayList;
import java.util.Date;

import service.DateUtils;

public class Contrat {
	private Client signataire;	// personne qui signe le contrat
	private Vehicule vehicule;	// vehicule associe au contrat
	private Etat etat;			// etat du contrat (location ou reservation) 
	private Date dateDebut;		// date ou le contrat sera effectif
	private Date dateFin;		// date ou se termine le contrat
	private boolean actif;		// indique si le contrat est effectif
	private ArrayList<Paiement> listePaiements;	// liste des paiements effectuees sur ce contrat
	private double depotGarantie;	// montant du depot de garantie
	
	//Constructeur
	public Contrat(Client c, Vehicule v, Date debut, Date fin) {
		this.signataire = c;
		this.vehicule = v;
		this.dateDebut = debut;
		this.dateFin = fin;
		this.actif = true;
		
		// Si la date correspond a aujourd'hui, creer une location
		if (DateUtils.isToday(debut)) 
			this.etat = new Location(v.getKilometrage());
		// sinon, reservation
		else
			this.etat = new Reservation();
		
		// creer le paiment initial du contrat
		// on suppose le paiement acquitte
		Paiement paiementInitial = new Paiement(calculerTotalFacture(), "Paiement initial (incluant le depot de garantie)", true);
		listePaiements.add(paiementInitial);
	} 
	
	/*
	 * Calcul du total de la facture (fait au moment de la location)
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
	public double calculerTotalFacture() {
		double total = vehicule.getTauxQuotidien() * getJourLocation()  * getDepotGarantie();
		
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
	 * Assigne un nouveau vehicule et de nouvelles dates au contrat
	 * Parametre : le nouveau vehicule et les nouvelles dates
	 * Valeur de retour : aucune
	 */
	public void modifierVehiculeEtDate(Vehicule vehiculeChoisi, Date debut, Date fin) {
		this.vehicule = vehiculeChoisi;
		this.dateDebut = debut;
		this.dateFin = fin;
	}
	
	/*
	 * Ajoute un paiement a la liste
	 * Parametre : montant, raison du paiement, est acquitte ou non
	 * Valeur de retour : aucune
	 */
	public void ajouterPaiement(double montant, String raison, boolean acquitte) {
		if(!raison.equalsIgnoreCase("")) {
			Paiement nouveauPaiement = new Paiement(montant, raison, acquitte);
			listePaiements.add(nouveauPaiement);
		}
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
	
	// Getters & setters
	public Client getSignataire() {
		return signataire;
	}
	public void setSignataire(Client signataire) {
		this.signataire = signataire;
	}
	public Vehicule getVehicule() {
		return vehicule;
	}

	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

	public Etat getEtat() {
		return etat;
	}
	public void setEtat(Etat etat) {
		this.etat = etat;
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

	public double getDepotGarantie() {
		return depotGarantie;
	}

	public void setDepotGarantie(double depotGarantie) {
		this.depotGarantie = depotGarantie;
	}
	
}
