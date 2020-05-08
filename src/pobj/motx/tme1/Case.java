package pobj.motx.tme1;


/**
 * Une classe repésentant une Case de mot-croisé
 * 
 * @author munro
 *
 */
public class Case {
	/** Emplacement de la ligne, colonne */
	private int lig, col;
	/** Caractère de la case */
	private char c;

	/**
	 * Construit une case
	 * 
	 * @param lig
	 *            la position horizontale
	 * @param col
	 *            la position verticale
	 * @param c
	 *            le caractère
	 */
	public Case(int lig, int col, char c) {
		this.lig = lig;
		this.col = col;
		this.c = c;
	}

	/**
	 * Getter pour le numéro de ligne
	 * 
	 * @return le num de ligne de la case
	 */
	public int getLig() {
		return lig;
	}

	/**
	 * Getter pour le numéro de colonne
	 * 
	 * @return le num de colonne de la case
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Getter pour caractère
	 * 
	 * @return le caractère de la case
	 */
	public char getChar() {
		return c;
	}

	/**
	 * Setter pour caractère
	 */
	public void setChar(char c) {
		this.c = c;
	}

	/**
	 * Check si la case contient ' '
	 * 
	 * @return c == ' '
	 */
	public boolean isVide() {
		return c == ' ';
	}

	/**
	 * Check si la case contient '*'
	 * 
	 * @return c == '*'
	 */
	public boolean isPleine() {
		return c == '*';
	}
	
	public boolean equals(Object o) {
		if(!(o instanceof Case))
			return false;
		Case c = (Case) o;
		return this.lig == c.lig && this.col == c.col && this.c == c.c;
	}

}
