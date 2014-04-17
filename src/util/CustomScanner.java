package util;
import java.util.Scanner;
/*
 * Contient une encapsulation de la classe Scanner de java.
 * Permet d'utiliser une seule instance de scanner pour toute l'application (singleton)
 * ainsi qu'une seule fermeture du scanner.
 */

public class CustomScanner {
	private Scanner scanner = null;
	private static CustomScanner instance;
	
	//Constructeur priv� (singleton)
	private CustomScanner()
	{
		scanner = new Scanner(System.in);
	}
	
	public static CustomScanner getInstance()
	{
		if(instance == null)
		{
			instance = new CustomScanner();
		}
		
		return instance;
	}
	
	public String getUserInput(String message)
	{
		System.out.println(message);
		return scanner.nextLine();
	}
	
	//Cette classe (singleton) une instance de cr��
	public static boolean isInstanceCreated()
	{
		if(instance == null)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	//Ferme le scanneur
	public void close()
	{
		scanner.close();
	}
	
}
