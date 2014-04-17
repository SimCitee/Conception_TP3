package application;


import util.ConsoleHelper;
import util.InputValidator;
import util.CustomScanner;

public class ControlleurPrincipal {
	
	public static void fenetrePrincipale()
	{
		String input;
		CustomScanner userInputScanner = CustomScanner.getInstance();
	
		ConsoleHelper.printScreenSeparator("Accueil");
		System.out.println("1. Gestion des locations");
		System.out.println("2. Gestion des véhicules");
		System.out.println("3. Gestion des clients");
		
		do{
			input = userInputScanner.getUserInput("Votre choix : ");
			
			if(InputValidator.stringIsInteger(input) && InputValidator.evalRange(Integer.parseInt(input), 1, 3))
				break;
				
			System.out.println("Choix invalide!");
			
		} while(true);
		
		switch(Integer.parseInt(input))
		{
			
			
			case 1 :
				
				break;
			
			case 2 :
				ControlleurVehicules.fenetrePrincipale();
				break;
			
			case 3 :
				
				break;
				
		}
	}
}
