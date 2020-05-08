package pobj.motx.tme2;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.*;

public class GrillePotentiel {
	/*
	 * La grille actuelle, partielement remplie
	 */
	private GrillePlaces grilleActuelle;
	/*
	 * Le dictionnaire de base passé au constructeur
	 */
	private Dictionnaire dico;
	/**
	 * Une liste de dictionnaire de mots possibles pour chaque emplacement de la
	 * grille
	 */
	private List<Dictionnaire> motsPot = new ArrayList<>();
	/*
	 * Liste des contraintes observée après filtrage (au croisements)
	 */
	private List<IContrainte> contraintes = new ArrayList<IContrainte>();
	

	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet, List<Dictionnaire> potentiel) {
		grilleActuelle = grille;
		dico = dicoComplet.copy();
		System.out.println(grilleActuelle.toString());
		List<Emplacement> places = grilleActuelle.getPlaces();
		// filtrage par taille
		for (int e = 0; e < places.size(); e++) {
			Emplacement emp = places.get(e);
			Dictionnaire filtered = potentiel.get(e);
			filtered.filtreLongueur(emp.size());
			// filtrage par lettres
			String lettres = emp.toString();
			for (int i = 0; i < lettres.length(); i++) {
				if (lettres.charAt(i) != ' ') {
					filtered.filtreParLettre(lettres.charAt(i), i);
				}
			}
			motsPot.add(filtered);
		}
		// Definition des contraintes
		int nbH = grilleActuelle.getNbHorizontal();
		for (int i = 0; i < nbH; i++) {
			Emplacement empH = grilleActuelle.getPlaces().get(i);
			for (int v = nbH; v < grilleActuelle.size(); v++) {
				Emplacement empV = grilleActuelle.getPlaces().get(v);
				for (int c1 = 0; c1 < empH.size(); c1++) {
					Case C1 = empH.getCase(c1);
					for (int c2 = 0; c2 < empV.size(); c2++) {
						Case C2 = empV.getCase(c2);
						if (C1.equals(C2) && C1.isVide()) {
							CroixContrainte cc = new CroixContrainte(i, c1, v, c2);
							if (!(contraintes.contains(cc)))
								contraintes.add(cc);
						}
					}
				}
			}
		}
		propage();
	}

	public GrillePotentiel(GrillePlaces grille, Dictionnaire dicoComplet) {
		grilleActuelle = grille;
		dico = dicoComplet.copy();
		System.out.println(grilleActuelle.toString());

		// filtrage par taille
		for (Emplacement emp : grilleActuelle.getPlaces()) {
			Dictionnaire filtered = dicoComplet.copy();
			filtered.filtreLongueur(emp.size());
			// filtrage par lettres
			String lettres = emp.toString();
			for (int i = 0; i < lettres.length(); i++) {
				if (lettres.charAt(i) != ' ') {
					filtered.filtreParLettre(lettres.charAt(i), i);
				}
			}
			motsPot.add(filtered);
		}
		// Definition des contraintes
		int nbH = grilleActuelle.getNbHorizontal();
		for (int i = 0; i < nbH; i++) {
			Emplacement empH = grilleActuelle.getPlaces().get(i);
			for (int v = nbH; v < grilleActuelle.size(); v++) {
				Emplacement empV = grilleActuelle.getPlaces().get(v);
				for (int c1 = 0; c1 < empH.size(); c1++) {
					Case C1 = empH.getCase(c1);
					for (int c2 = 0; c2 < empV.size(); c2++) {
						Case C2 = empV.getCase(c2);
						if (C1.equals(C2) && C1.isVide()) {
							CroixContrainte cc = new CroixContrainte(i, c1, v, c2);
							if (!(contraintes.contains(cc)))
								contraintes.add(cc);
						}
					}
				}
			}
		}
		propage();
	}

	/**
	 * Propagation des contraintes
	 * 
	 * @return true si on atteint la stabilité
	 */
	private boolean propage() {
		int somme = 0;
		int i = 0;
		while (true) {
			i = 0;
			while (i < contraintes.size()) {
				somme += contraintes.get(i).reduce(this);
				i++;
			}
			if (somme == 0) {
				return true;
			} else {
				if (isDead())
					return false;
				else
					somme = 0;
			}
		}
	}

	public boolean isDead() {
		for (Dictionnaire dic : motsPot) {
			if (dic.size() == 0)
				return true;
		}
		return false;
	}

	public ArrayList<IContrainte> getEmpContraintes(int index){
		ArrayList<IContrainte> list = new ArrayList<>();
		for(IContrainte cc : contraintes) {
			if(cc.has(index) && cc.filled(grilleActuelle))
				list.add(cc);
		}
		return list;
	}
	
	public List<Dictionnaire> getMotsPot() {
		return motsPot;
	}

	/**
	 * 
	 * @return la liste de contraintes
	 */
	public List<IContrainte> getContraintes() {
		return contraintes;
	}

	public List<Emplacement> getEmp() {
		return grilleActuelle.getPlaces();
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
	public GrillePotentiel fixer(int m, String soluce) {
		return new GrillePotentiel(grilleActuelle.fixer(m, soluce), dico);
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
	 * @param potentiel
	 * 				Liste de dicos potentiels (pour ne pas avoir à refiltrer le dictionnaire complet à la construction
	 * @return une nouvelle grille avec la solution au mot d'emplacement m
	 */
	public GrillePotentiel fixer(int m, String soluce, List<Dictionnaire> potentiels) {
		return new GrillePotentiel(grilleActuelle.fixer(m, soluce), dico, potentiels);
	}
	
	public Dictionnaire getDico() {
		return dico;
	}

}
