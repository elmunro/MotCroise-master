package pobj.motx.tme2;

import java.util.HashSet;

public class EnsembleLettre {
	HashSet<Character> lettres = new HashSet<Character>();

	public EnsembleLettre() {
	}

	public EnsembleLettre(String s) {
		for (char c : s.toCharArray()) {
			lettres.add(c);
		}
	}

	public void add(char c) {
		lettres.add(c);
	}

	public int size() {
		return lettres.size();
	}

	public EnsembleLettre intersect(EnsembleLettre B) {
		String s ="";
		for(Character c : B.lettres) {
			if(this.lettres.contains(c))
				s+=(char)c;
		}
		return new EnsembleLettre(s);
		/*EnsembleLettre copie = new EnsembleLettre(lettres.toString());
		copie.lettres.retainAll(B.lettres);
		return copie;*/
	}

	public boolean contains(char c) {
		return lettres.contains((Character)c);
	}

}
