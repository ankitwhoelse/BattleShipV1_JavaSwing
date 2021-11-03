package battleship;

import java.util.ArrayList;
import java.util.Vector;

public class JoueurAvance extends JoueurIntermediaire{

    private Vector<Coord> tabCoups;
    private ArrayList<Coord> tabCoupsAdjacent;
    private Coord coord = new Coord(-1,-1);
    private Coord coord2;


    private boolean booDiagonaleDeux = false;
    private  boolean booTirAleatoire =false;

    public JoueurAvance(){
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



        // Si le tableau de coups adjacent n'est pas vide
        if (!this.tabCoupsAdjacent.isEmpty()) {
            // Le prochain coup serait un parmis le tableau
            coord2 = this.tabCoupsAdjacent.remove(0);
            System.out.println("TIR AVI sur coord: (" + coord2.ligne + ", " + coord2.colonne + ")");
        } else {
            // Le prochain coups est un coup aleatoire, jamais joue
            //      c = UtilitaireCollection.obtenirCoupPasDejaJouer(this.tabCoups);
            if(coord.colonne == 9 && coord.ligne == 9){
                booDiagonaleDeux =true;
                coord.ligne = -1;
                coord.colonne++;
            }
            if(coord.colonne == 0 && coord.ligne == 9){
                booTirAleatoire =true;

            }

            if(!booDiagonaleDeux && !booTirAleatoire){
                    coord.colonne++;
                    coord.ligne++;
            }else if(booDiagonaleDeux && !booTirAleatoire){
                coord.colonne--;
                coord.ligne++;
            }else if(booTirAleatoire)
            {
                coord = UtilitaireCollection.obtenirCoupPasDejaJouer(this.tabCoups);
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
