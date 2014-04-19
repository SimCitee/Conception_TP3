package domaine;

import java.util.HashMap;

//Singleton
public class TypeVehicule {
	
	//Contient les couples cote de classification / type de véhicule
	//Par exemple le tableau
	//Cote	| type
	//----------------------
	//1001  | VUS
	//1002  | Camionnette
	//...
	private HashMap<Integer, String> coupleTypeCote = new HashMap<Integer, String>();
	private static int compteurCote = 1000;			//Les cote commence à 1000
	private static TypeVehicule instance = null;
	
	private TypeVehicule(){}
	
	public static TypeVehicule getInstance()
	{
		if(instance == null)
		{
			instance = new TypeVehicule();
		}
		
		return instance;
	}
	
	//Ajoute un nouveau type et retourne la cote associée
	public Integer ajouterType(String type)
	{
		compteurCote++;
		coupleTypeCote.put(compteurCote, type);	
		
		return compteurCote;
	}
	
	//Retourne le type de véhicule en fonction d'une cote
	//Retourne null si la cote n'est pas trouvé
	public String getType(Integer cote)
	{
		return coupleTypeCote.get(cote);
	}
	
	public HashMap<Integer, String> getCoteType()
	{
		return coupleTypeCote;
	}
}
