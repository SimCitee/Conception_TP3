package domaine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import service.DateUtils;
import ui.Interface;

public class Contrat {
	private static int compteurContrat = 0;
	private int noContrat;		// numero du contrat
	private Client signataire;	// personne qui signe le contrat
	private Vehicule vehicule;	// vehicule associe au contrat
	private Etat etat;			// etat du contrat (location ou reservation) 
	private Date dateDebut;		// date ou le contrat sera effectif
	private Date dateFin;		// date ou se termine le contrat
	private Date dateRemise;	// date ou a ete remis le vehicule
	private boolean actif;		// indique si le contrat est effectif
	private ArrayList<Paiement> listePaiements;	// liste des paiements effectuees sur ce contrat
	private double depotGarantie;	// montant du depot de garantie a la creation du contrat
	private DegatVehicule degat;	// contient les details des degats effectues au vehicule (s'il y a lieu)
	
	public final int MNT_DEPOT_GARANTIE = 200;	// montant du depot de garantie
	public final int FRAIS_MODIFICATION = 10;	// frais de modification du contrat
	public final int MAX_KILO_GRATUIT = 100;	// indique le nombre de kilometre gratuit
	public final double FRAIS_DEPASSEMENT_KILO = 0.25;	// montant pour chaque kilometre depasse les kilometre gratuit
	public final int FRAIS_DEPASSEMENT_HEURE = 2;	// montant a l'heure pour la 1ere journee depasse la date de fin
	public final int FRAIS_DEPASSEMENT_JOUR = 75;	// montant par jour pour les autres jours depasse le 1er apres la date de fin
	
	//Constructeur
	public Contrat(Client c, Vehicule v, Date debut, Date fin) {
		this.noContrat = ++compteurContrat;
		this.signataire = c;
		this.vehicule = v;
		this.dateDebut = debut;
		this.dateFin = fin;
		this.actif = true;
		this.depotGarantie = MNT_DEPOT_GARANTIE;
		this.degat = null;
		this.listePaiements = new ArrayList<Paiement>();
		
		// Si la date correspond a aujourd'hui, creer une location
		if (DateUtils.isToday(debut)) 
			etat = new Location(v.getKilometrage());
		// sinon, reservation
		else
			etat = new Reservation();
		
		// creer le paiment initial du contrat
		// on suppose le paiement acquitte
		Paiement paiementInitial = new Paiement(calculerTotalFacture(), "Paiement initial (incluant le depot de garantie)", true);
		listePaiements.add(paiementInitial);
	
		// Mettre le vehicule non disponible
		v.setEstDisponible(false);
	} 
	
	
	/*
	 * Validation des dates
	 * Parametre : date de debut, date de fin
	 * Valeur de retour : booleen
	 */
	public boolean validerDates(Date debut, Date fin) {
		return (DateUtils.isAfterDay(fin, debut)) ? true : false;
	}
	
	/*
	 * Calcul du total de la facture (fait au moment de la location)
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
	public double calculerTotalFacture() {
		int tauxQuotidien = vehicule.getTauxQuotidien();
		int joursLocation = getJourLocation(); 
		double depotGarantie = getDepotGarantie();
		double total =  (tauxQuotidien * joursLocation) + depotGarantie;
		
		return total;
	}
	
	/*
	 * Calcul la difference entre le total precedent et le nouveau total
	 * Ajout d'un paiement au besoin
	 * Parametre : aucun
	 * Valeur de retour : aucune
	 */
	public void calculerDifferenceTotal(int fraisSpeciaux) {
		// Obtenir le dernier total
		Paiement p = listePaiements.get(listePaiements.size()-1);
		// obtenir le nouveau total
		double montant = calculerTotalFacture();
		double diff = montant - p.getMontant();
		Paiement nouveauPaiement = null;
		
		// si la difference est positive, le client doit payer pour les frais supplementaire
		if (diff > 0)
			nouveauPaiement = new Paiement(montant, "Nouvelle facture", false);
		// sinon, on credite le client
		else {
			montant = Math.abs(montant);
			nouveauPaiement = new Paiement(montant, "Credit", false);
		}
		
		listePaiements.add(nouveauPaiement);
		
	}
	
	/*
	 * Modifier les dates de debut et fin de la location
	 * Parametre : date du debut / date de fin de location
	 * Valeur de retour : aucune
	 */
	public void modifierDates(Date dateDebut, Date dateFin) {
		if (validerDates(dateDebut, dateFin)) { 
			this.dateDebut = dateDebut;
			this.dateFin = dateFin;
			
			// Si l'etat etait une reservation et la nouvelle date est aujourd'hui
			// changer l'etat de reservation a location
			if (DateUtils.isToday(dateDebut) && etat instanceof Reservation && vehicule != null)
				etat = new Location(vehicule.getKilometrage());
		}
	}
	
	/*
	 * Assigne un nouveau vehicule au contrat
	 * Parametre : le nouveau vehicule
	 * Valeur de retour : aucune
	 */
	public void modifierVehicule(Vehicule vehiculeChoisi) {
		vehicule.setEstDisponible(true);
		vehiculeChoisi.setEstDisponible(false);
		setVehicule(vehiculeChoisi);
	}
	
	/*
	 * Assigne un nouveau vehicule et de nouvelles dates au contrat
	 * Parametre : le nouveau vehicule et les nouvelles dates
	 * Valeur de retour : aucune
	 */
	public void modifierVehiculeEtDate(Vehicule vehiculeChoisi, Date debut, Date fin) {
		modifierDates(debut, fin);
		modifierVehicule(vehiculeChoisi);
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
	 * Met fin au contrat est remet le vehiicule disponible
	 * Parameter : aucun
	 * Valeur de retour : aucune
	 */
	public void mettreFinContrat(int kilometrage, Date dateRemise) {
		((Location)etat).setKilometrageArrivee(kilometrage);
		vehicule.setKilometrage(kilometrage);
		vehicule.setEstDisponible(true);
		setDateRemise(dateRemise);
		setActif(false);
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
	
	/*
	 * Calcul des frais supplementaires du contrat
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	public void calculerFraisSupplementaire() {
		if (etat instanceof Location) {
			int montantDegat = (degat != null) ? degat.getMontantDegat() : 0;
			int totalKiloGratuit = getJourLocation() * MAX_KILO_GRATUIT;
			int intevalleKilo = ((Location) etat).getIntervalleKilometrage();
			long totalRetard = 0;
			long intervalleFinRemise = DateUtils.getDayInterval(getDateFin(), getDateRemise());
			double totalMontantKilo = 0;
			double fraisSupplementaire = 0;
			
			// si le retard est de plus d'une journee
			if (intervalleFinRemise > 1)
				totalRetard = (DateUtils.getDayInterval(getDateFin(), getDateRemise())) * FRAIS_DEPASSEMENT_JOUR;
			// si le retard est d'une journee
			else if (intervalleFinRemise == 1) {
				Date debut = getDateRemise();
				Calendar cal = Calendar.getInstance(); // locale-specific
				cal.setTime(debut);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				debut = cal.getTime();
				
				totalRetard = (DateUtils.getHourInterval(debut, getDateRemise())) * FRAIS_DEPASSEMENT_JOUR;
			}
			
			if (intevalleKilo > totalKiloGratuit) {
				int differenceKilo = intevalleKilo - totalKiloGratuit;
				totalMontantKilo = differenceKilo * FRAIS_DEPASSEMENT_KILO;
			}
			
			fraisSupplementaire = totalRetard + totalMontantKilo + montantDegat;
			
			// S'il y a des frais supplementaires, ajouter un paiement
			if (fraisSupplementaire > 0) {
				Paiement paiementFrais = new Paiement(fraisSupplementaire, "Frais supplementaire", false);
				listePaiements.add(paiementFrais);
			}
		}
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
	public Date getDateRemise() {
		return dateRemise;
	}

	public void setDateRemise(Date dateRemise) {
		this.dateRemise = dateRemise;
	}

	public DegatVehicule getDegat() {
		return degat;
	}

	public void setDegat(DegatVehicule degat) {
		this.degat = degat;
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

	public int getNoContrat() {
		return noContrat;
	}

	public void setNoContrat(int noContrat) {
		this.noContrat = noContrat;
	}
	
	@Override
	public String toString() {
		
		String strRemise = (dateRemise == null) ? "aucune" : dateRemise.toString();
		String strDegat = (degat == null) ? "aucun" : degat.toString();
		
		return "No. contrat: " + this.noContrat + "\n" +
				"Signataire: " + this.signataire.getNomComplet() + "\n" +
				"Details vehicule: " + this.vehicule.toString() + "\n" +
				"Details etat: " + etat.toString() + "\n" +
				"Date debut: " + this.dateDebut.toString() + "\n" +
				"Date fin: " + this.dateFin.toString() + "\n" +
				"Date remise: " + strRemise + "\n" +
				"Est actif: " + this.actif + "\n" +
				"Depot garantie:" + this.depotGarantie + "\n" +
				"Details paiement: " + listePaiements.toString() + "\n" +
				"Degat vehicule: " + strDegat;
				
	}
	
}
