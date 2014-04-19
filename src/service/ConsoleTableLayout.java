package service;

import java.util.ArrayList;
import java.util.List;


/* Classe utilitaire qui permet de faire du formatage de type "tableau" dans la console.
 * 
 */
public class ConsoleTableLayout {
	
List<Integer> dimentionsColonnes = new ArrayList<Integer>();
	
	//Set les dimensions des colonnes. Utiliser des entiers.
	public ConsoleTableLayout(Object ... args)
	{
		int columnNumber = 0;
		
		//Conserve les dimensions des colonnes (nb de charact�res)
		for (Object o : args) {
			dimentionsColonnes.add(columnNumber, (Integer)o);
			
			columnNumber++;
	    }
	}
	
	public void newLine(Object ... args) 
	{
		int columnNumber = 0;
		
		//Affiche tous les String en ajoutant des espaces pour l'alignement des colonnes
		for (Object o : args) {
			String value = (String)o;
			
			//Imprime la valeurs
			//System.out.print(value);
		    
			//Imprime les caract�res pour compl�ter la ligne
			for(int i=value.length(); i<=dimentionsColonnes.get(columnNumber); i++)
			{
				//System.out.print(" ");
				value += " ";
				
			}
			
			System.out.print(value);
			
			columnNumber++;
		}
		
		//Fin de la ligne
		System.out.print("\n");
		
	}
}
