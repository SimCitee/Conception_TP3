package application;


import service.ConsoleHelper;
import service.CustomScanner;
import service.InputValidator;

public class ControlleurPrincipal {
	
	public static void fenetrePrincipale()
	{
		String input;
		CustomScanner userInputScanner = CustomScanner.getInstance();
	
		ConsoleHelper.printScreenSeparator("Accueil");
		System.out.println("1. Gestion des locations");
		System.out.println("2. Gestion des vehicules");
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
