package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/***
 * Classe représentant l'emplacement d'un espace blanc ou mot.
 * 
 * @see Grille
 */
public class Emplacement {
	/** Liste de {@code Case} contenant les lettres */
	private List<Case> lettres = new ArrayList<>();
	// mots lus de haut -> bas et gauche -> droite

	/***
	 * Construit l'emplacement d'un mot à partir de ses lettres
	 * 
	 * @param lettres
	 *            liste de Cases à fournir
	 */
	public Emplacement(List<Case> lettres) {
		this.lettres = lettres;
	}

	/**
	 * Methode qui sert a savoir si l'emplcaement est horizontal ou vertical
	 * 
	 * @return true si c'est horizontal
	 */
	public boolean getDirH() {
		if (lettres.size() < 2)
			return false;
		return lettres.get(0).getCol() < lettres.get(1).getCol();
	}
	
	
	/**
	 * Vérifie si l'emplcaement as au moins une case vide
	 */
	public boolean hasCaseVide() {
		for(Case c : lettres) {
			if(c.isVide())
				return true;
		}
		return false;
	}

	public Case getStart() {
		return lettres.get(0);
	}

	/***
	 * Taille du mot
	 * 
	 * @return la taille du mot
	 */
	public int size() {
		return lettres.size();
	}

	/***
	 * Custom toString
	 * 
	 * @return String contenant le mot
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Case c : lettres) {
			sb.append(c.getChar());
		}
		return sb.toString();
	}

	/**
	 * Get la case d'index index
	 * @param index
	 * @return Case
	 */
	public Case getCase(int index) {
		return lettres.get(index);
	}
}
