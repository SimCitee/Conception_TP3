package service;

public class InputValidator {
	
	/**
     * Verifie si un string peut etre convertit en entier
     */
	public static boolean stringIsInteger(String str)
	{
		
		try{
			Integer.parseInt(str);
		}
		catch(Exception e)
		{
			return false;
		}
		
		return true;
		
	}
	
	/**
     * Evalue si un entier est compris dans une plage
     * 
     */
	public static boolean evalRange(int number, int start, int end)
	{
		//Teste la plage
		if(number>= start && number <= end)
			return true;
		
		return false;
		
	}
	
	// Methode permettant de valider un entier et s'il se trouve dans un intervalle donnee
	// Parametre : Un nombre potentiel, Debut de l'intervalle, Fin de l'interface
	// Valeur de retour : Valeur boolenne representant si le nombre est valide et s'il respecte l'intervalle donnee
	public static boolean validerEntier(String valeur)
	{
		int choixUtilisateur;
		
		// Test pour valider si la chaine de caractere correspond a un nombre entier
		try { 
			choixUtilisateur = Integer.parseInt(valeur); 
			return true;
	    } 
		catch(NumberFormatException e) { 
	        return false; 
	    }
	}
	
	// Methode permettant de valider un entier et s'il se trouve dans un intervalle donnee
	// Parametre : Un nombre potentiel, Debut de l'intervalle, Fin de l'interface
	// Valeur de retour : Valeur boolenne representant si le nombre est valide et s'il respecte l'intervalle donnee
	public static boolean validerIntervalleEntier(String valeur, int plageDebut, int plageFin)
	{
		int choixUtilisateur = -1;
		
		if (validerEntier(valeur)) {
			choixUtilisateur = Integer.parseInt(valeur);
			
			// Test pour valider si le nombre se trouve dans l'intervalle donnee
			if((choixUtilisateur >= plageDebut) && (choixUtilisateur <= plageFin) && choixUtilisateur > -1)
				return true;
		}

		return false;
	}
}
