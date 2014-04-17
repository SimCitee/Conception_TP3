import java.util.ArrayList;

import modele.Catalogue;
import application.ControlleurPrincipal;


public class Program {

	public static void main(String[] args) {
		
		
		//Données bidon
		Catalogue c = Catalogue.getInstance();
		
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("AC");
		a1.add("Vitres électrique");
		c.AjouterVehicule(1, "Honda", "Civic", 145, "automatique", 5, "JJJ 777", true, a1);
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("Radio");
		a2.add("Toit ouvrant");
		c.AjouterVehicule(1, "Mazda", "3", 120, "Manuelle", 4, "FFF 123", false, a2);
		
		//Affiche le menu principal
		ControlleurPrincipal.fenetrePrincipale();
		

	}

}
