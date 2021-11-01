package battleship;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Solution du tp1A21 inf111
 *
 * Regroupe tous les SP pour g�rer l'objet Flotte
 *
 * @author Mohamed-Amine Djelloud
 * @author Ankit Patel
 * @version Copyright A2021
 *
 */
public class Flotte {

    /*  ---------
     *  Attributs
     */
    private  ArrayList<Navire> lstNavire = new ArrayList<>();

    /*  --------------------------
     *  Constructeur par defaut
     */
    public Flotte(){

    }
    /*  --------------------------
     *  Constructeur par param�tre
     */
    public Flotte(ArrayList<Navire> lstNavire){
        this.lstNavire = lstNavire;
    }


    /*
    * Retourne true si le tir a touché un des navires
    * */
    public boolean leTirTouche(Coord tir){

        boolean booCoup = false;
        for (Navire navire : lstNavire) {
            if(navire.fin.colonne == navire.debut.colonne && tir.colonne == navire.debut.colonne){
                if(tir.ligne >= navire.debut.ligne && tir.ligne <= navire.fin.ligne){
                    booCoup = true;
                }
            }

            if(navire.fin.ligne == navire.debut.ligne && tir.ligne == navire.debut.ligne){
                if(tir.colonne >= navire.debut.colonne && tir.colonne <= navire.fin.colonne){
                    booCoup = true;
                }
            }
        }
        return booCoup;
    }

    /*
     * Retourne true si le jeu a terminer
     * */
    public boolean jeuTermine(){
        boolean jeuTerminer = true;

        for (Navire navire : lstNavire) {
            if(!navire.estCoule()){
                jeuTerminer =false;
            }
        }

        return jeuTerminer;
    }

    /*
     * Retourne un tableau statique contenant les navires
     * */
    public Navire[] getTabNavires(){
        Navire[] tabNavires = new Navire[lstNavire.size()];
        return lstNavire.toArray(tabNavires);
    }

    /*
     * retourne si le tir a touché un des navires
     * */
    public boolean dejaRecuCoup(Coord tir){
        boolean booDejaToucher = false;
        for (Navire navire : lstNavire) {
              if(navire.dejaRecuTir(tir)){
                    booDejaToucher = true;
           }
        }
        return booDejaToucher;
    }
    /*
     *   ajoute un navire seulement si
    les coordonnées du navire sont valides.
     * */

    private  int ajouterNavire(Navire navire) throws Exception{
        for (Navire nav : lstNavire) {
            if(nav.chevauche(navire)){
                throw new Exception("NAVIRE_DEJA_SUR_PLACE");
            }
        }
        lstNavire.add(navire);

        return 1;
    }

    /*
*    retourne un navire dont les coordonnées ont été générées
            aléatoirement
* */
    private  Navire obtenirNavireAleatoire(String nom, int longueur, Color couleur) {

        Random rand = new Random();
        boolean booException =false;
        Navire navire = null;

        do {
            int debutcolonne = rand.nextInt(Constantes.TAILLE);
            int debutligne = rand.nextInt(Constantes.TAILLE);
            int fincolonne = debutcolonne;
            int finligne = debutligne;

            // 0 = NORD---- 1 = SUD ---- 2 = EST ----- 3 = OUEST
            int direction = rand.nextInt(3);

            switch (direction) {
                //NORD
                case 0:
                    finligne = debutligne - (longueur-1);
                    break;
                    //SUD
                case 1:
                    finligne = debutligne + (longueur-1);
                    break;
                    //EST
                case 2:
                    fincolonne = debutcolonne + (longueur-1);
                    break;
                    //OUEST
                case 3:
                    fincolonne = debutcolonne - (longueur-1);
                    break;
            }


                if(finligne < 10 && fincolonne <10) {
                    Coord debutCoord = new Coord(debutligne, debutcolonne);
                    Coord finCoord = new Coord(finligne, fincolonne);

                    try {
                        navire = new Navire(nom, debutCoord, finCoord, couleur);
                        booException = false;

                    } catch (Exception e) {
                        booException = true;
                    }
                }else{
                    booException = true;
                }
        }while (booException);


       return navire;

    }

    /*
    * ajoute un à un
        les navires dans la flotte
        * */
   private  void genererPosNavireAleaInsererDsGrille() throws Exception {
        boolean booAjouter = false;
        while (!booAjouter){
            try {
                ajouterNavire(obtenirNavireAleatoire(Constantes.SOUS_MARIN,3,Color.red));
                booAjouter = true;
            }catch (Exception e){
                booAjouter =false;
            }
        }
       booAjouter = false;
       while (!booAjouter){
           try {
               ajouterNavire(obtenirNavireAleatoire(Constantes.PORTE_AVION,5,Color.BLUE));
               booAjouter = true;
           }catch (Exception e){
               booAjouter =false;
           }
       }
       booAjouter = false;
       while (!booAjouter){
           try {
               ajouterNavire(obtenirNavireAleatoire(Constantes.CROISEUR,4,Color.GREEN));
               booAjouter = true;
           }catch (Exception e){
               booAjouter =false;
           }
       }
       booAjouter = false;
       while (!booAjouter){
           try {
               ajouterNavire(obtenirNavireAleatoire(Constantes.DESTROYER,3,Color.YELLOW));
               booAjouter = true;
           }catch (Exception e){
               booAjouter =false;
           }
       }
       booAjouter = false;
       while (!booAjouter){
           try {
               ajouterNavire(obtenirNavireAleatoire(Constantes.CUIRASSE,2,Color.BLACK));
               booAjouter = true;
           }catch (Exception e){
               booAjouter =false;
           }
       }
   }

   /* crée une flotte, génère la position des
        navires aléatoire  et la retourne.
        */
    public static Flotte obtenirFlotteAleatoire() throws Exception {

        Flotte flotte = new Flotte();
        flotte.genererPosNavireAleaInsererDsGrille();
        flotte = new Flotte(flotte.lstNavire);

        return flotte;
    }
}
