package battleship;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Solution du tp1A21 inf111
 *
 * Regroupe tous les SP pour g�rer l'objet Navire
 *
 * @author Mohamed-Amine Djelloud
 * @author Ankit Patel
 * @version Copyright A2021
 *
 */

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
	ArrayList<Coord> coordNavire;
	int direction;
	
	
	/*  --------------------------
	 *  Constructeur par param�tre
	 */
	public Navire(String nom, Coord debut, Coord fin, Color couleur) throws Exception {
		
		int ligne = fin.ligne - debut.ligne;
		int colonne = fin.colonne - debut.colonne;
		coordNavire = new ArrayList<>();
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
					throw new Exception("Coordonn�es NORD_SUD invalide");
				} else {
					// Position Est_Ouest invalide
					if (colonne > 1 & ligne != 0) {
						throw new Exception("Coordonn�es EST_OUEST invalide");
					// Tout est correct, aucune Exception
					} else {
						// Initialiser les attributs
						this.nom = nom;
						this.couleur = couleur;
						this.debut = debut;
						this.fin = fin;
						if (ligne >= 1) {
							//NORD_SUD
							this.longeur = ligne +1;
							if (debut.ligne > fin.ligne) {
								this.direction = 0;
								for (int i = 0; i < longeur; i++) {
									Coord coordonne = new Coord(debut.ligne-i, debut.colonne);
									coordNavire.add(coordonne);
								}
							}
							else {
								this.direction = 1;
								for (int i = 0; i < longeur; i++) {
									Coord coordonne = new Coord(debut.ligne+i, debut.colonne);
									coordNavire.add(coordonne);
								}
							}
						} else {
							//EST_OUEST

							this.longeur = colonne +1;
							if (debut.colonne > fin.colonne) {
								this.direction = 3;
								for (int i = 0; i < longeur; i++) {
									Coord coordonne = new Coord(debut.ligne, debut.colonne-i);
									coordNavire.add(coordonne);
								}
							}
							else {
								this.direction = 2;
								for (int i = 0; i < longeur; i++) {
									Coord coordonne = new Coord(debut.ligne, debut.colonne+i);
									coordNavire.add(coordonne);
								}
							}
						}
					}
				}				
			}			
		}				
	}
	
	
	/*  ------------------
	 *  M�THODES PUBLIQUES
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
	 * Retourne TRUE si le bateau a d�j� �t� touch� au tir pass� en param�tre
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
	 * Retourne TRUE si le bateau actuel est touch� par le tir pass� en param�tre
	 * Retourne FALSE si ce n'est pas le cas 
	 */
	public boolean tirAtouche(Coord tir) {
		boolean tirTouche = false;
		
		// S'assurer que le bateau n'est pas d�j� coul�
		if (!this.estCoule()) {
			// S'assurer que le bateau n'a pas d�j� �t� touch� � cet endroit 
			if (!this.dejaRecuTir(tir)) {
				// �valuer si le coup � toucher le bateau
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
	 * Retourne TRUE si le bateau pass� en param�tre se situe sur la m�me case que le bateau actuel
	 * Retourne FALSE si ce n'est pas le cas  
	 */
	public boolean chevauche(Navire navire) {
		boolean chevauche = false;

		for (int i = 0; i < this.coordNavire.size(); i++) {
			for(int k = 0; k < navire.coordNavire.size(); k++) {
				if (this.coordNavire.get(i).equals(navire.coordNavire.get(k))) {
					chevauche = true;
				}
			}
		}
		
		return chevauche;
	}
	
	
	/*  ---------------
	 *  M�THODES PRIV�S
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
