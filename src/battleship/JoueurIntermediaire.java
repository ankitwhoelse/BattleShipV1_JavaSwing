package battleship;

import java.util.ArrayList;
import java.util.Vector;

public class JoueurIntermediaire {
    // On aurait pu prendre n'importe quelle collection.
    private Vector tabCoups;
    private ArrayList tabCoupsAdjacent;


    public JoueurIntermediaire(){
        tabCoups = new Vector();
        tabCoupsAdjacent = new ArrayList();
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
        Coord c = UtilitaireCollection.obtenirCoupPasDejaJouer(tabCoups);

        tabCoups.add(c);

        return c;

    }

    public void aviseTouche(Coord c){}
}
