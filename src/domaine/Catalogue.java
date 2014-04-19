package domaine;

import java.util.ArrayList;

//Singleton
public class Catalogue {
	
	private ArrayList<Vehicule>listeVehicules = new ArrayList<Vehicule>();
	private static int compteurVehicule = 0;	//Conserve le dernier identifiant de véhicule données
	private static Catalogue instance = null;
	
	private Catalogue(){}
	
	public static Catalogue getInstance()
	{
		if(instance == null)
		{
			instance = new Catalogue();
		}
		
		return instance;
	}
	
	public void AjouterVehicule(Integer coteClassification, String marque, String modele, Integer forceMoteur, 
			String transmission, Integer nbPlaces, String immatriculation, boolean estDisponible,
			ArrayList<String> accessoires)
	{
		
		Vehicule v = new Vehicule();
		
		v.setIdentifiant(++compteurVehicule);
		v.setCoteClassification(coteClassification);
		v.setMarque(marque);
		v.setModele(modele);
		v.setForceMoteur(forceMoteur);
		v.setTransmission(transmission);
		v.setNbPlaces(nbPlaces);
		v.setImmatriculation(immatriculation);
		v.setEstDisponible(estDisponible);
		v.setAccessoires(accessoires);
		
		listeVehicules.add(v);
	}
	
	public void modifierVehicule(Vehicule v, Integer coteClassification, String marque, String modele, Integer forceMoteur, 
			String transmission, Integer nbPlaces, String immatriculation, boolean estDisponible,
			ArrayList<String> accessoires)
	{
		v.setCoteClassification(coteClassification);
		v.setMarque(marque);
		v.setModele(modele);
		v.setForceMoteur(forceMoteur);
		v.setTransmission(transmission);
		v.setNbPlaces(nbPlaces);
		v.setImmatriculation(immatriculation);
		v.setEstDisponible(estDisponible);
		v.setAccessoires(accessoires);
	}
	
	public void supprimerVehicule(Vehicule v)
	{
		listeVehicules.remove(v);
	}
	
	//Détermine si le véhicule existe en fonction de son identifiant
	public Vehicule trouverVehicule(Integer identifiant)
	{
		for(Vehicule v : listeVehicules)
		{
			if(v.getIdentifiant() == identifiant)
				return v;
		}
		
		return null;
	}
	public ArrayList<Vehicule> getListeVehicules()
	{
		return listeVehicules;
	}
	public void afficherCatalogue()
	{
		for(Vehicule v : listeVehicules)
			System.out.println(v.toString());
	}
	
}
