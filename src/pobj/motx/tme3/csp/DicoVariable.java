package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme2.*;

public class DicoVariable implements IVariable {
	/**
	 * Index de l'emplacaement du mot correspondant
	 */
	private int indexof_emp;
	private List<String> domain;
	private GrillePotentiel grille_pot;

	public DicoVariable(int index, GrillePotentiel grille) {
		indexof_emp = index;
		grille_pot = grille;
		domain = new ArrayList<>();
		Dictionnaire dico = grille_pot.getMotsPot().get(indexof_emp);
		for (int i = 0; i < dico.size(); i++)
			domain.add(dico.get(i));
	}
	
	/**
	 * Constructeur ou le dictionnaire à utiliser est spécifié
	 * @param index index de l'emplcaement / de la variable
	 * @param grille
	 * @param dict
	 */
	public DicoVariable(int index, GrillePotentiel grille, Dictionnaire dict) {
		indexof_emp = index;
		grille_pot = grille;
		domain = new ArrayList<>();
		Dictionnaire dico = dict;
		for (int i = 0; i < dico.size(); i++)
			domain.add(dico.get(i));
	}

	@Override
	public List<String> getDomain() {
		return domain;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String str : domain)
			sb.append(str + "\t");
		return sb.toString();
	}

	public int getIndex() {
		return indexof_emp;
	}

	@Override
	public List<IContrainte> getContraintesList() {
		return grille_pot.getEmpContraintes(indexof_emp);
	}

	@Override
	public IVariable reeval() {
		return new DicoVariable(this.indexof_emp, grille_pot, grille_pot.getDico());
	}

}
