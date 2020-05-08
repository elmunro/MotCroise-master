package pobj.motx.tme2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Dictionnaire {

	// stockage des mots
	private List<String> mots = new ArrayList<>();

	private EnsembleLettre cache[];

	/**
	 * Ajoute un mot au Dictionnaire, en dernière position.
	 * 
	 * @param mot
	 *            à ajouter, il sera stocké en minuscules (lowerCase)
	 */
	public void add(String mot) {
		mots.add(mot.toLowerCase());
	}

	/**
	 * Taille du dictionnaire, c'est à dire nombre de mots qu'il contient.
	 * 
	 * @return la taille
	 */
	public int size() {
		return mots.size();
	}

	/**
	 * Accès au i-eme mot du dictionnaire.
	 * 
	 * @param i
	 *            l'index du mot recherché, compris entre 0 et size-1.
	 * @return le mot à cet index
	 */
	public String get(int i) {
		return mots.get(i);
	}

	/**
	 * Rend une copie de ce Dictionnaire.
	 * 
	 * @return une copie identique de ce Dictionnaire
	 */
	public Dictionnaire copy() {
		Dictionnaire copy = new Dictionnaire();
		copy.mots.addAll(mots);
		copy.setCache(getCache());
		return copy;
	}

	/**
	 * Retire les mots qui ne font pas exactement "len" caractères de long.
	 * Attention cette opération modifie le Dictionnaire, utiliser copy() avant de
	 * filtrer pour ne pas perdre d'information.
	 * 
	 * @param len
	 *            la longueur voulue
	 * @return le nombre de mots supprimés
	 */
	public int filtreLongueur(int len) {
		List<String> cible = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			if (mot.length() == len)
				cible.add(mot);
			else
				cpt++;
		}
		mots = cible;
		if (cpt > 0) {
			clearCache();
		}
		return cpt;
	}

	@Override
	public String toString() {
		if (size() == 1) {
			return mots.get(0);
		} else {
			return "Dico size =" + size();
		}
	}

	public static Dictionnaire loadDictionnaire(String path) {
		Dictionnaire dico = new Dictionnaire();
		try (Stream<String> stream = Files.lines(Paths.get(path))) {
			stream.forEach(dico::add);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dico;
	}

	/**
	 * Modifie le dictionnaire pour ne garder que les mots dont la ième lettre est
	 * égale au caractère de l’argument c. Attention cette opération modifie le
	 * Dictionnaire, utiliser copy() avant de filtrer pour ne pas perdre
	 * d'information.
	 * 
	 * @param expect
	 *            le char voulu
	 * @param index
	 *            l'index dans le mot du char voulu
	 * @return le nombre de mots supprimés
	 */
	public int filtreParLettre(char expect, int index) {
		List<String> res = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			if (mot.charAt(index) == expect) {
				res.add(mot);
			} else {
				cpt++;
			}
		}
		mots = res;

		if (cpt > 0) {
			clearCache();
		}
		return cpt;
	}

	public int filtreParLettre(EnsembleLettre pot, int index) {
		List<String> res = new ArrayList<>();
		int cpt = 0;
		for (String mot : mots) {
			char c = mot.charAt(index);
			if (pot.contains(c)) {
				res.add(mot);
			} else {
				cpt++;
			}
		}
		mots = res;
		if (cpt > 0) {
			clearCache();
		}
		return cpt;
	}

	public EnsembleLettre ensembleAt(int index) {
		if (mots.isEmpty()) {
			return new EnsembleLettre();
		}
		EnsembleLettre l = getCache()[index];
		if (l == null) {
			l = new EnsembleLettre();
			for (String mot : mots) {
				l.add(mot.charAt(index));
			}
			getCache()[index] = l;
		}
		return l;
	}

	private EnsembleLettre[] getCache() {
		if (cache == null) {
			int motSize = mots.get(0).length();
			cache = new EnsembleLettre[motSize];
		}
		return cache;
	}

	private void clearCache() {
		this.cache = null;
	}

	private void setCache(EnsembleLettre[] cache2) {
		this.cache = cache2;
	}

}
