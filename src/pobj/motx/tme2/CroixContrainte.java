package pobj.motx.tme2;

import pobj.motx.tme1.GrillePlaces;

public class CroixContrainte implements IContrainte {
	/*
	 * Indices m-emplcement, c-case
	 */
	private int m1, c1, m2, c2;

	public CroixContrainte(int m1, int c1, int m2, int c2) {
		this.c1 = c1;
		this.m1 = m1;
		this.c2 = c2;
		this.m2 = m2;
	}

	@Override
	public int reduce(GrillePotentiel grille) {
		int somme = 0;
		
		EnsembleLettre e1 = grille.getMotsPot().get(m1).ensembleAt(c1);
		EnsembleLettre e2 = grille.getMotsPot().get(m2).ensembleAt(c2);
		EnsembleLettre res = e1.intersect(e2);
		
		if(res.size() < e1.size())
			somme += grille.getMotsPot().get(m1).filtreParLettre(res, c1);
		if(res.size() < e2.size())
			somme += grille.getMotsPot().get(m2).filtreParLettre(res, c2);
		return somme;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this)
			return true;
		if(!(o instanceof CroixContrainte))
			return false;
		CroixContrainte cc = (CroixContrainte) o;
		return (cc.m1 == this.m1 && cc.m2 == this.m2 
				&& cc.c1 == this.c1 && cc.c2 == this.c2) 
				|| (cc.m1 == this.m2 && cc.m2 == this.m1 
				&& cc.c1 == this.c2 && cc.c2 == this.c1);
	}

	@Override
	public boolean has(int index) {
		return index == m1 || index == m2;
	}

	@Override
	public boolean filled(GrillePlaces grilleActuelle) {
		return !(grilleActuelle.getPlaces().get(m1).getCase(c1).isVide());
	}



}
