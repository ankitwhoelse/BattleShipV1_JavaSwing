package battleship;
import java.util.Vector;

/**
 * Joueur qui a d�j� jou�.  Sa strat�gie est un peu meilleur que la 
 * premi�re fois qu'il a  jou�.
 *
 * Il retient ses coups et ne joue pas deux fois le m�me.  Il ne fait rien de 
 * sp�cial s'il est avis� d'avoir touch� un navire.
 * 
 * @author Pierre B�lisle
 * @version Copyright A2021
 *
 */
public class JoueurDebutant {
	
	/*
	 * Strat�gie : 
	 */
	
	// On aurait pu prendre n'importe quelle collection.
	private Vector tabCoups;
	
	public JoueurDebutant(){
		tabCoups = new Vector();
	}

	/**
	 * 
	 * @return
	 */
	public Coord getTir(){
		
		/*
		 * Strat�gie : On obtient un coup pas d�j� jou� du module de collection
		 * et on l'ajoute � la collection.
		 */
		Coord c = UtilitaireCollection.obtenirCoupPasDejaJouer(tabCoups);  
				
		tabCoups.add(c);
		
		return c; 
		
	}
	
	public void aviseTouche(Coord c){}	
}
