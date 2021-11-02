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

public class JoueurIntermediaire {
	
	/*
	 * Stratégie : 
	 */	
	
    // On aurait pu prendre n'importe quelle collection.
    private Vector<Coord> tabCoups;
    private ArrayList<Coord> tabCoupsAdjacent;


    public JoueurIntermediaire(){
        tabCoups = new Vector<Coord>();
        tabCoupsAdjacent = new ArrayList<Coord>();
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
        
        
        return c;
    }

    public void aviseTouche(Coord c){
    	System.out.println("TOUCHE bateau a:(" + c.ligne + ", " + c.colonne + ")");
    	
    	Coord coordAvise = null;
    	
    	if (this.tabCoupsAdjacent.isEmpty()) {
	    	// Boucle qui prend les 4 coords autour du coup recu par parametre
	    	for (int i = 0; i < 4; i++) {
	    		
	    		switch (i) {
	    		// NORD
	    		case 0: 
	    			if (c.ligne != 0) {
	    				// Coord c PAS sur la premiere ligne, coord valide au nord
	    				coordAvise = new Coord(c.ligne-1, c.colonne);
	    			} else {
	    				// Coord c sur la ligne 0, rien au nord
	    			} break;
	    			
	    		// SUD
	    		case 1:
	    			if (c.ligne != (Constantes.TAILLE)-1) {
	    				// Coord c PAS sur la derniere ligne du tableau, coord valide au sud
	    				coordAvise = new Coord(c.ligne+1, c.colonne);
	    			} else {
	    				// Coord c sur la ligne 10, rien au sud
	    			} break;
	
	    		// EST
	    		case 2: 
	    			if (c.colonne != (Constantes.TAILLE)-1) {
	    				// Coord c PAS sur la derniere colonne du tableau, coord valide a l'est
	    				coordAvise = new Coord(c.ligne, c.colonne+1);
	    			} else {
	    				// Coord c sur la colonne 10, rien a l'est
	    			} break;
	
	    		// OUEST
	    		case 3:
	    			if (c.colonne != 0) {
	    				// Coord c PAS sur la premiere colonne du tableau, coord valide a l'ouest
	    				coordAvise = new Coord(c.ligne, c.colonne-1);
	    			} else {
	    				// Coord c sur la colonne 0, rien a l'ouest
	    			} break;
	    		}
	    		
	    		if (!UtilitaireCollection.tableauContientCoord(this.tabCoups, coordAvise)) {
					// Si le coup avise n'a pas deja ete joue
	    			this.tabCoupsAdjacent.add(coordAvise);
    				System.out.print(" AVISE :(" + coordAvise.ligne + ", " + coordAvise.colonne + ") - ");
				}
	    	}
    	}
    	System.out.println();
    	
    }
}
