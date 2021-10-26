package battleship;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;


import javax.swing.JOptionPane;

/**
 * Simule l'intelligence de r�solution d'un jeu de bataille navale (Battleship)
 * 
 * Ce programme impl�mente quelques strat�gies possible pour trouver les navires 
 * cach�s d'un jeu.
 *  
 *@author Pierre B�lisle
 *@version Copyright A2021
 *
 */

public class DemarrerBatailleNavale {

	/*
	 * Strat�gie globale : On utilise une GrilleGui pour obtenir une
	 * grille pour afficher la flotte du joueur que l'ordinateur doit trouver.
	 *                              
	 *     Classes n�cessaires de votre tp2 : 
	 *     
	 *     Navire : Les donn�es et les m�thodes concernant un navire. 
	 *     GrilleGui : grille d'affichage et de saisie graphique
	 *     UtilitaireGrilleGui : Interface entre le programme et la grilleGui.
	 *     Flotte : Contient tous les navires du joueur
	 *            
	 *     Classe fournies :
	 *     
	 *     StrategieOrdiPremiereFois : Joue n'importe comment
	 *     StrategieOrdiDebutant : Ne jou epas 2 fois au m�me endroit.
	 *                                  
	 *     UtilitaireCollection : Recherche d'une coordonn�e dans une collection.
	 *     UtilitaireFonctions : Permet d'obtenir des tirs au hasard.
	 *                           
	 *                               
	 */

	static int nbRepetitions = 0;
	static int nbTirs = 0;

	public static void main(String[] args) {

		// Cr�ation de l'interface graphique qui permet de jouer. 
		GrilleGui gui = new GrilleGui(Constantes.TAILLE, Constantes.TAILLE,
				Constantes.COULEUR_TEXTE, 
				Constantes.COULEUR_FOND,
				Constantes.OPTIONS,
				GrilleGui.QUITTE);

		// Laisse le temps de cr�er le gui.
		UtilitaireGrilleGui.pause(250);

		// Boucle virtuellement infinie.  Le programme quitte sur X du gui.
		boolean quitter = false;

		while(!quitter){

			// Retiendra le nombre total de fois que les tirs sont all�s 
			// sur des cases d�j� tir�es.
			nbRepetitions = 0;

			//Retient le nombre total de tirs.
			nbTirs = 0;

			demarrerModeOrdi(gui);

			// Petit message qui donne le temps de voir ce qui s'est pass�e.
			JOptionPane.showMessageDialog(null,"Solution trouv�e en " +
			nbTirs 	+ 	" coups avec " + nbRepetitions + " r�p�tition de tirs");

		}
	}

	/**
	 * Proc�dure qui 
	 * @param gui
	 */
	public static void demarrerModeOrdi(GrilleGui gui){

		// On cr�e une nouvelle flotte al�atoire et on la montre.
		Flotte flotteOrdi = Flotte.obtenirFlotteAleatoire();

		UtilitaireGrilleGui.montrerFlotte(flotteOrdi, gui);

		// On attend que l'utilisateur clique sur un des boutons d'options.
		while(!gui.optionMenuEstCliquee()){
			UtilitaireGrilleGui.pause(1);	
		};

		// On remet la page blanche pour v�rifier que l'application trouve bien 
		// les navires.
		UtilitaireGrilleGui.reinitialiserGui(gui);

		// On obtient l'option du menu qui a �t� cliqu�.
		String menu = gui.getOptionMenuClique();

		// On compare les String pour trouver la strat�gie choisie.
		// Selon la s�lection, on cr�e le joueur avec la bonne strat�gie 
		// et on d�marre la partie en lui passant le param�tre appropri�.				
		if(menu.equals(
				Constantes.OPTIONS[Constantes.PREMIERE_FOIS])){

			JoueurPremiereFois ordi =  new JoueurPremiereFois();
			demarrerPartie(ordi, flotteOrdi, Constantes.PREMIERE_FOIS, gui);

		}
		else if(menu.equals(
				Constantes.OPTIONS[Constantes.DEBUTANT])){

			JoueurDebutant ordi = new JoueurDebutant();
			demarrerPartie(ordi, flotteOrdi, Constantes.DEBUTANT, gui);
		}
		else if(menu.equals(
				Constantes.OPTIONS[Constantes.INTERMEDIAIRE])){

			JoueurIntermediaire ordi = new JoueurIntermediaire();
			demarrerPartie(ordi, flotteOrdi, Constantes.INTERMEDIAIRE, gui);
		}		

		else if(menu.equals(
				Constantes.OPTIONS[Constantes.AVANCE])){

			JoueurAvance ordi = new JoueurAvance();
			demarrerPartie(ordi, flotteOrdi, Constantes.AVANCE, gui);
		}	
		
		// Le choix expert.
		else
			if(JOptionPane.showConfirmDialog(null,"Pas encore impl�ment�") == 
			JOptionPane.CANCEL_OPTION)
				System.exit(0);
	}

	/**
	 * D�marre la partie avec l'ordi re�u qui peut avoir une strat�gie
	 * diff�rente.
	 * 
	 * Principalement pour �viter la r�p�tition de code.
	 * 
	 * @param ordi
	 * @param flotte
	 * @param lequel
	 * @param gui
	 */
	public static void demarrerPartie(Object ordi, 
			Flotte flotte, 
			int lequel, 
			GrilleGui gui){

		while(!flotte.jeuTermine()){
		

			Coord tir = getTirOrdi(ordi, lequel);
			
			// Un tir de plus pour les stats.
			nbTirs++;

			// Compte les r�p�titions de tirs enregardant si dans 
			// le gui c'est touch�.			
			if(gui.getValeur(tir) == Constantes.TOUCHE)
				nbRepetitions++;

			// On montre le tir.
			UtilitaireGrilleGui.afficherTir(gui, tir);
			
			// Donne le temps de voir le tir (debug).
			UtilitaireGrilleGui.pause(100);

			// Si le tir a touch� mais � une nouvelle position
			// On affiche la case touch�e.
			if(!flotte.dejaRecuCoup(tir)){
				
				if(flotte.leTirTouche(tir)){
					UtilitaireGrilleGui.afficherNavireTouche(gui, tir);
					aviserOrdi(ordi, lequel, tir);
				}				
			}
		}
	}

	/*
	 * Retourne le tir selon le niveau du joueur fourni
	 * avec le deuxi�me param�tre.
	 * 
	 * @param ordi
	 * @param lequel Le niveau du joueur
	 * 
	 * @return La coord du tir de l'ordi.
	 */
	private static Coord getTirOrdi(Object ordi, int lequel){
		
		//Tir � retourner
		Coord tir = null;
		
		// On pr�sume que le param�tre est valide.
		switch (lequel){
	
			case Constantes.PREMIERE_FOIS : {
	
				tir =((JoueurPremiereFois) ordi).getTir();
	
			}break;
	
			case Constantes.DEBUTANT :  {
	
				tir =((JoueurDebutant) ordi).getTir();
				
			}break;
	
			case Constantes.INTERMEDIAIRE : {  
				
				// �crivez le code n�cessaire ici
				
			}break;
	
			case Constantes.AVANCE : {
				
				// �crivez le code n�cessaire ici
				
			}break;
			
			case Constantes.EXPERT :  {
	
				// �crivez le code n�cessaire ici
	
			}break;
		
		}
		return tir;		
	}
	/*
	 * S�lectionne la bonne strat�gie selon le deuxi�me param�tre
	 * qui repr�sente le niveau du joueur.
	 */	
	private static void aviserOrdi(Object ordi, int lequel, Coord tir){
		
		switch (lequel){

			case Constantes.PREMIERE_FOIS : {
						
				((JoueurPremiereFois) ordi).aviseTouche(tir);
				
			}break;
	
			case Constantes.DEBUTANT :  {
				
				((JoueurDebutant) ordi).aviseTouche(tir);
				
			}break;
				
			case Constantes.INTERMEDIAIRE :{  
				
				// �crivez le code n�cessaire ici
				
			}break;
	
			case Constantes.AVANCE :{
				
				// �crivez le code n�cessaire ici
				
			}break;
			
			case Constantes.EXPERT :  {
	
				// �crivez le code n�cessaire ici
	
			}break;
			
		}
	}
}