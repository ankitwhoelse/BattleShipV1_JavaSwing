package battleship;

import java.awt.Color;
import java.util.ArrayList;

public class Navire {

	/*  ---------
	 *  Attributs 
	 */
	String nom;
	int longeur;
	Color couleur;
	Coord debut;
	Coord fin;
	ArrayList<Coord> coupTouche;
	
	
	/*  --------------------------
	 *  Constructeur par paramètre
	 */
	public Navire(String nom, Coord debut, Coord fin, Color couleur) throws Exception {
		
		int ligne = fin.ligne - debut.ligne;
		int colonne = fin.colonne - debut.colonne;
		
		// Ligne invalide
		if (ligne+1 > Constantes.TAILLE || debut.ligne > fin.ligne ) {
			throw new Exception("Ligne invalide");
		} else {
			// Colonne invalide
			if (colonne+1 > Constantes.TAILLE || debut.colonne > fin.colonne ) {
				throw new Exception("Colonne invalide");
			} else {
				// Position Nord_Sud invalide
				if (ligne > 1 && colonne != 0) {
					throw new Exception("Coordonnées NORD_SUD invalide");
				} else {
					// Position Est_Ouest invalide
					if (colonne > 1 & colonne != 0) {
						throw new Exception("Coordonnées EST_OUEST invalide");
					// Tout est correct, aucune Exception
					} else {
						// Initialiser les attributs
						this.nom = nom;
						this.couleur = couleur;
						this.debut = debut;
						this.fin = fin;
						if (ligne > 1) {
							this.longeur = ligne;
						} else {
							this.longeur = colonne;
						}
					}
				}				
			}			
		}				
	}
	
	
	/*  ------------------
	 *  MÉTHODES PUBLIQUES
	 */ 
	
	/*
	 *  Retourne TRUE si le bateau a autant de coups que sa longeur, sinon
	 *  Retourne FALSE si ce n'est pas le cas 
	 */
	public boolean estCoule() {
		boolean estCoule = false;
		
		if (coupTouche.size() == longeur) {
			estCoule = true;
		}
		
		return estCoule;
	}
	
	/*
	 * Retourne TRUE si le bateau a déjâ été touché au tir passé en paramètre
	 * Retourne FALSE si ce n'est pas le cas 
	 */
	public boolean dejaRecuTir(Coord tir) {
		boolean dejaRecu = false;
		
		for(int i = 0; i < coupTouche.size(); i++) {
			if (coupTouche.get(i).equals(tir)) {
				dejaRecu = true;
			}
		}
		
		return dejaRecu;
	}
	
	/*
	 * Retourne TRUE si le bateau actuel est touché par le tir passé en paramètre
	 * Retourne FALSE si ce n'est pas le cas 
	 */
	public boolean tirAtouche(Coord tir) {
		boolean tirTouche = false;
		
		// S'assurer que le bateau n'est pas déjâ coulé
		if (!this.estCoule()) {
			// S'assurer que le bateau n'a pas déjâ été touché à cet endroit 
			if (!this.dejaRecuTir(tir)) {
				// Évaluer si le coup à toucher le bateau
				if (this.positionTouche(tir)) {
					// Ajouter le tir a la collection
					coupTouche.add(tir);
					tirTouche = true;
				}
			}
		}	
		
		return tirTouche;
	}

	/*
	 * Retourne TRUE si le bateau passé en paramètre se situe sur la même case que le bateau actuel
	 * Retourne FALSE si ce n'est pas le cas  
	 */
	public boolean chevauche(Navire navire) {
		boolean chevauche = false;		
		
		if (navire.debut.ligne >= this.debut.ligne && navire.fin.ligne <= this.fin.ligne) {
			if (navire.debut.colonne >= this.debut.colonne && navire.fin.colonne <= this.fin.colonne) { 
				chevauche = true;
			}
		}
		
		return chevauche;
	}
	
	
	/*  ---------------
	 *  MÉTHODES PRIVÉS
	 */
	
	/*
	 * Retourne TRUE si le tir peut toucher le bateau courant
	 * Retourne FALSE si ce n'est pas le cas  
	 */
	private boolean positionTouche(Coord tir) {
		boolean tirAtoucher = false;
		
		if (tir.ligne <= this.debut.ligne && tir.ligne >= this.fin.ligne) {
			if (tir.colonne <= this.debut.colonne && tir.colonne >= this.fin.colonne) {
				tirAtoucher = true;
			}
		}
		
		return tirAtoucher;
	}
	
}
