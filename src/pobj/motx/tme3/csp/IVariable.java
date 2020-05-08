package pobj.motx.tme3.csp;

import java.util.List;

import pobj.motx.tme2.IContrainte;

public interface IVariable {
	/**
	 * Renvoie l'ensemble des valeurs que peut prendre le domaine d'IVariable, sous forme d'une liste de string 
	 */
	public List<String> getDomain();
	
	public List<IContrainte> getContraintesList();

	public IVariable reeval();
}
