package battleship;

public class UtilitaireFonctions {
	
    public static int nbAlea(int min, int max){
  	    /*
  	     * Strat�gie, on utilise le g�n�rateur de Java qui retourne une valeur
  	     * r�elle entre 0 et 1[  ensuite, on le ram�ne dans l'intervalle
  	     * min..max et on la transforme en entier.
  	     */

    	return (int) (Math.random() * (max - min + 1) + min); 
  	}
    
   //************************
   // POSITION ALEATOIRE
   //************************
   /*
    * Retourne un nombre entre 0 + longueurNavier -1 et 
    * Constantes.TAILLE - longueurNavire
    */
   public static Coord coordAleatoire(int longueurNavire){
  	 
  	 return new Coord(
  			 nbAlea(longueurNavire - 1, Constantes.TAILLE - longueurNavire), 
  			 nbAlea(longueurNavire - 1, Constantes.TAILLE - longueurNavire));
   }
   

   //************************
   // COORDONNEE ALEATOIRE
   //************************
   /*
    * Retourne une coordonn�e dans la grille entre 0 et  Constantes.TAILLE - 1
    */
   public static Coord coordAleatoire(){
  	 
  	 return new Coord(
  			 nbAlea(0, Constantes.TAILLE - 1), 
  			 nbAlea(0, Constantes.TAILLE - 1));
   }
}
