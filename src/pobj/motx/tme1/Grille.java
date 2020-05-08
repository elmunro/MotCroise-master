package pobj.motx.tme1;

/**
 * Une grille de taille variable contenant des ' ', '*' et [A-Z]
 * 
 * @author munro
 *
 */
public class Grille {
	/**
	 * Une matrice de Case
	 * 
	 * @see Case
	 */
	private Case[][] m;

	/**
	 * Construit la grille et y met des espace blanc (== ' ')
	 * 
	 * @param hauteur
	 *            la hauteur de la grille
	 * @param largeur
	 *            la largeur de la grille
	 */
	public Grille(int hauteur, int largeur) {
		m = new Case[hauteur][largeur];
		for (int i = 0; i < m.length; i++) {
			// System.out.println(m.length +"\n"+m[0].length);
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = new Case(i, j, ' ');
			}
		}
	}

	/**
	 * Case accesseur
	 * 
	 * @param lig
	 *            ligne
	 * @param col
	 *            colonne
	 * @return une Case
	 */
	public Case getCase(int lig, int col) {
		return m[lig][col];
	}

	/**
	 * Accesseur du nb de lignes
	 */
	public int nbLig() {
		return m.length;
	}

	/**
	 * Accesseur du nb de colonnes
	 */
	public int nbCol() {
		return m[0].length;
	}

	/**
	 * Copieur de Grille, c'est une copie entière (!creuse)
	 * 
	 * @return une nouvelle grille avec de nouveau points
	 */
	public Grille copy() {
		Grille copy = new Grille(nbLig(), nbCol());
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				copy.m[i][j] = new Case(i, j, m[i][j].getChar());
			}
		}
		return copy;
	}

	/**
	 * Custom toString
	 * 
	 * @return un affichage formaté de la grille
	 */
	@Override
	public String toString() {
		/*
		 * String s = "\n"; for (int i = 0; i < m.length; i++) { for (int j = 0; j <
		 * m[i].length; i++) { s+= ' '+ m[i][j].getChar(); } s+="\n"; } return s;
		 */
		return GrilleLoader.serialize(this, false);
	}
}
