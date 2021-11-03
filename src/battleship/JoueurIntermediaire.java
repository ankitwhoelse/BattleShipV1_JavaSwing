package battleship;

import java.util.ArrayList;
import java.util.Vector;

/**
 * Joueur INTERMEDIAIRE
 *
 * Il retient ses coups et ne joue pas deux fois le même. On retient les
 * coordonnées adjacentes et non en diagonal (NORD, SUD, EST et OUEST seulement)
 * du dernier tir qui a été envoyé par getTir (celui qui vient de toucher).
 * 
 * @author 
 * @version Copyright A2021
 *
 */

public class JoueurIntermediaire extends Joueur {
	
	/*
	 * Stratégie : 
	 */	
	
    // On aurait pu prendre n'importe quelle collection.
    private Vector<Coord> tabCoups;
    private ArrayList<Coord> tabCoupsAdjacent;


    public JoueurIntermediaire(){
        tabCoups = getTabCoups();
        tabCoupsAdjacent = getTabCoupsAdjacent();
    }
    /**
     * Retourne le coup du joueur
     *
     * @return
     */
    public Coord getTir(){
		
		/*
		 * Stratégie : On obtient un coup pas déjà joué du module de collection
		 * et on l'ajoute à la collection.
		 *                  
		 * La méthode a été mise dans UtilitaireCollection pour réutilisation 
		 */
    	
    	Coord c;

    	// Si le tableau de coups adjacent n'est pas vide
    	if (!this.tabCoupsAdjacent.isEmpty()) {
    		// Le prochain coup serait un parmis le tableau
    		c = this.tabCoupsAdjacent.remove(0);
    		System.out.println("TIR AVI sur coord: (" + c.ligne + ", " + c.colonne + ")");
    	} else {
    		// Le prochain coups est un coup aleatoire, jamais joue
    		c = UtilitaireCollection.obtenirCoupPasDejaJouer(this.tabCoups);
    		System.out.println("TIR NEW sur coord: (" + c.ligne + ", " + c.colonne + ")");
    	}   	        
   	    	
    	this.tabCoups.add(c);
        setTabCoups(this.tabCoups);
		setTabCoupsAdjacent(this.tabCoupsAdjacent);

		return c;
    }

}
