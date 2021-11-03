package battleship;

import java.util.ArrayList;
import java.util.Vector;

public class Joueur {
    private ArrayList<Coord> tabCoupsAdjacent;
    private Vector<Coord> tabCoups;

    public void setTabCoupsAdjacent(ArrayList<Coord> tabCoupsAdjacent) {
        this.tabCoupsAdjacent = tabCoupsAdjacent;
    }

    public void setTabCoups(Vector<Coord> tabCoups) {
        this.tabCoups = tabCoups;
    }

    public ArrayList<Coord> getTabCoupsAdjacent() {
        return tabCoupsAdjacent;
    }

    public Vector<Coord> getTabCoups() {
        return tabCoups;
    }

    Joueur()  {
            tabCoupsAdjacent = new ArrayList<Coord>();
            tabCoups = new Vector<Coord>();
        }

    public void aviseTouche(Coord c){
        System.out.println("TOUCHE bateau a:(" + c.ligne + ", " + c.colonne + ")");

        Coord coordAvise = null;

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

            if (coordAvise != null) {
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
