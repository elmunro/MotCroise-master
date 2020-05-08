package pobj.motx.tme2;

import pobj.motx.tme1.GrillePlaces;

public interface IContrainte {

	/**
	 * Agit en modifiant la grille passée en argument, et rend le nombre total de
	 * mots filtrés par son action (donc potentiellement 0 si elle n’a aucun effet)
	 * 
	 * @param grille la grille
	 * @return Nombre total de mots filtrés par son action
	 */
	int reduce(GrillePotentiel grille);

	boolean has(int index);

	boolean filled(GrillePlaces grilleActuelle);
}
