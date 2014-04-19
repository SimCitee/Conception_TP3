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
}
