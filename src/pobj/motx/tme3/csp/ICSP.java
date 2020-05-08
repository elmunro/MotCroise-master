package pobj.motx.tme3.csp;

import java.util.List;

public interface ICSP {
	
	/**
	 * Acceder aux variables du problème
	 **/
	List<IVariable> getVars();
	
	/**
	 * Tester si le problème est encore satisfiable
	 **/
	boolean isConsistent();
	
	/**
	 * Affecte une des variables du problème
	 **/
	ICSP assign(IVariable vi, String val);

	ICSP assign_new(IVariable vi, String val);

	
}
