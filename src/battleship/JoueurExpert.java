package battleship;

import java.util.ArrayList;
import java.util.Vector;

public class JoueurExpert extends JoueurIntermediaire{

    private Vector<Coord> tabCoups;
    private ArrayList<Coord> tabCoupsAdjacent;
    private Coord coord = new Coord(-1,5);
    private Coord coord2;
    private int diagonale = 0;
    private boolean premierTir = true;

    public JoueurExpert(){
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
		 * Stratégie : On divise le tableau en quatres quadrants, puis on parcours en diagonnale
		 * 	a travers les 4 quadrants, une fois qu'on a passer a travers les 4 quadrants, on tir
		 * 	sur des cases aleatoires.
		 *                  
		 * La méthode a été mise dans UtilitaireCollection pour réutilisation 
         */
    	
    	// Verifier la coordonne du tir, si elle se situe a la fin du quadrant,
    	// 	on se deplace au quadrant suivant.
    	if(coord.ligne == 4 && coord.colonne == 0 && diagonale==0) {
    		diagonale++;
    		coord.ligne++;
    		System.out.println("New diagonale1");
    	} else if(coord.ligne == 9 && coord.colonne == 4  && diagonale==1) {
    		diagonale++;
    		coord.colonne++;
    		premierTir = true;
    		System.out.println("New diagonale2");
    	} else if(coord.ligne == 5 && coord.colonne == 9  && diagonale==2) {
    		diagonale++;
    		coord.ligne--;
    		premierTir = true;
    		System.out.println("New diagonale3");
    	} else if(coord.ligne == 0 && coord.colonne == 5  && diagonale==3) {
    		diagonale++;
    		premierTir = true;
    		System.out.println("New diagonale4");
    	} else {
    		
    	}


        // Si le tableau de coups adjacent n'est pas vide
        if (!this.tabCoupsAdjacent.isEmpty()) {
            // Le prochain coup serait un parmis le tableau
            coord2 = this.tabCoupsAdjacent.remove(0);
            System.out.println("TIR AVI sur coord: (" + coord2.ligne + ", " + coord2.colonne + ")");
        } else {
        	// Determiner quel est la prochaine coordonne selon le quadrant
        	switch (diagonale) {
	        	case 0:
	                coord.ligne++;
	                coord.colonne--;
	        		break;
	        	case 1:
	        		if (premierTir) {
	        			premierTir = false;
	        		} else {
		        		coord.ligne++;
		                coord.colonne++;
	                }
	        		break;
	        	case 2:
	        		if (premierTir) {
	        			premierTir = false;
	        		} else {
		        		coord.ligne--;
		                coord.colonne++;
	        		}
	        		break;
	        	case 3:
	        		if (premierTir) {
	        			premierTir = false;
	        		} else {
		        		coord.ligne--;
		                coord.colonne--;
	        		}
	        		break;
        		default:
        			coord = UtilitaireCollection.obtenirCoupPasDejaJouer(this.tabCoups);
            		break;
        	}
       
            coord2 =null;
            System.out.println("TIR NEW sur coord: (" + coord.ligne + ", " + coord.colonne + ")");
        }
        Coord coord3 = new Coord(-1,-1);

        if(coord2!=null){
            this.tabCoups.add(coord2);
            coord3 = coord2;
        }else{
            this.tabCoups.add(coord);
            coord3 = coord;
        }

        setTabCoups(this.tabCoups);
        setTabCoupsAdjacent(this.getTabCoupsAdjacent());

        System.out.println(coord3 + "COORD 3");
        return coord3;
    }

}
