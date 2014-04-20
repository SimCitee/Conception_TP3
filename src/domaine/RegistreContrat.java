package domaine;

import java.util.ArrayList;
import java.util.Date;

//Singleton
public class RegistreContrat {
	private static RegistreContrat _instance;
	private static ArrayList<Contrat> listeContrats;
	
	private RegistreContrat() {
	}
	
	public static RegistreContrat getInstance() {
		if (_instance == null) {
			_instance = new RegistreContrat();
			listeContrats = new ArrayList<Contrat>();
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
	
	/*
	 * Parcours les contrats jusqu'a ce l'on celui au numero correspondant
	 * Parametre: numero du contrat
	 * Valeur de retour: le contrat recherche
	 */
	public static Contrat rechercherContrat(String noContrat) {
		for(Contrat c : listeContrats)
			if (c.getNoContrat() == Integer.parseInt(noContrat))
				return c;
		
		return null;
	}
}
