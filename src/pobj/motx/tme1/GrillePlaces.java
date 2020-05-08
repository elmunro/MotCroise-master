package pobj.motx.tme1;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe exporte une grille, et trouve l'emplacement de tous ses mots
 */
public class GrillePlaces {
	/** liste des Emplacements trouvés */
	private List<Emplacement> liste = new ArrayList<>();
	/** Le nombre de mots horizontaux */
	private int nbH;
	/** Une référence vers la grille passée en paramètre */
	private Grille grille;

	/**
	 * Construit une liste des emplacements à partir d'une Grille donnée
	 * 
	 * @param grille
	 *            La Grille
	 */
	public GrillePlaces(Grille grille) {
		this.grille = grille;
		for (int i = 0; i < grille.nbLig(); i++) {
			cherchePlaces(getLig(i));
		}
		nbH = liste.size();
		for (int i = 0; i < grille.nbCol(); i++) {
			cherchePlaces(getCol(i));
		}
	}

	/**
	 * Getter
	 * 
	 * @return La liste des emplacements
	 */
	public List<Emplacement> getPlaces() {
		return liste;
	}

	/*
	 * Getter pour le nombre de mots de la grille
	 */
	public int size() {
		return liste.size();
	}

	/**
	 * Getter
	 * 
	 * @return nb de mots horizontaux trouvés
	 */
	public int getNbHorizontal() {
		return nbH;
	}

	/**
	 * Get la liste des Case d'une ligne
	 * 
	 * @param lig
	 *            numero de ligne à getter
	 * @return Liste des Case de la ligne
	 */
	private List<Case> getLig(int lig) {
		ArrayList<Case> ligne = new ArrayList<>();
		for (int i = 0; i < grille.nbCol(); i++) {
			ligne.add(grille.getCase(lig, i));
		}
		return ligne;
	}

	/**
	 * Get la liste des Case d'une colonne
	 * 
	 * @param col
	 * @return Liste des Case de la colonne
	 */
	private List<Case> getCol(int col) {
		ArrayList<Case> colonne = new ArrayList<>();
		for (int i = 0; i < grille.nbLig(); i++) {
			colonne.add(grille.getCase(i, col));
		}
		return colonne;
	}

	/**
	 * Cherche tout les mots d'une liste de Case
	 * 
	 * @param cases
	 *            une liste de Case
	 */
	private void cherchePlaces(List<Case> cases) {
		ArrayList<Case> buffer = new ArrayList<>();
		for (int i = 0; i < cases.size(); i++) {
			if (!cases.get(i).isPleine()) {
				buffer.add(cases.get(i));
				i++;
				while (i < cases.size() && !cases.get(i).isPleine()) {
					buffer.add(cases.get(i));
					i++;
				}
				if (buffer.size() > 1)
					liste.add(new Emplacement(buffer));
			}
			buffer = new ArrayList<>();
		}
	}

	/**
	 * Rends une nouvelle grille où les cases constituant l’emplacement de mot
	 * d’indice m (dans la liste des emplacements de mots de la grille telle que
	 * retournée par getPlaces()) ont pour contenu les lettres de soluce
	 * 
	 * @param m
	 *            indice du mot dans les emplacements de la grille
	 * @param soluce
	 *            solution possible du mot à l'emplacement m
	 * @return une nouvelle grille avec la solution au mot d'emplacement m
	 */
	public GrillePlaces fixer(int m, String soluce) {
		Grille copie = grille.copy();
		Emplacement emp = liste.get(m);
		boolean directionH = emp.getDirH();
		int startCol = emp.getStart().getCol();
		int startLig = emp.getStart().getLig();
		for (int i = 0; i < emp.size(); i++) {
			if (directionH)
				copie.getCase(startLig, startCol + i).setChar(soluce.charAt(i));
			else
				copie.getCase(startLig + i, startCol).setChar(soluce.charAt(i));
		}
		return new GrillePlaces(copie);
	}

	@Override
	public String toString() {
		return grille.toString();
	}
}
