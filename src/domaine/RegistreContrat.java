package domaine;

import java.util.ArrayList;
import java.util.Date;

//Singleton
public class RegistreContrat {
	private static RegistreContrat _instance;
	private ArrayList<Contrat> listeContrats;
	private final int MNT_DEPOT_GARANTIE = 200;	// montant du depot de garantie
	
	private RegistreContrat() {
		listeContrats = new ArrayList<Contrat>();
	}
	
	public static RegistreContrat getInstance() {
		if (_instance == null) {
			_instance = new RegistreContrat();
		}
		
		return _instance;
	}

	public ArrayList<Contrat> getListeContrats() {
		return listeContrats;
	}

	public void setListeContrats(ArrayList<Contrat> listeContrats) {
		this.listeContrats = listeContrats;
	}
	
	/*
	 * Creer un nouveau contrat et l'ajoute au registre
	 * Parametres: client et vechicule associes au contrat, date de debut et de fin du contrat
	 * Valeur de retour: aucune
	 */
	public void faireContrat(Client client, Vehicule vehicule, Date debut, Date fin) {
		Contrat nouvContrat = new Contrat(client,vehicule,debut,fin);
		listeContrats.add(nouvContrat);
	}
}
