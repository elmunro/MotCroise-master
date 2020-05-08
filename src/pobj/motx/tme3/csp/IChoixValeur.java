package pobj.motx.tme3.csp;

import java.util.List;

public interface IChoixValeur {
	/**
	 * Ordonne les valeur en fonction de la stratégie
	 * @return liste des valeurs ordonnées
	 */
	List<String> orderValues(ICSP problem, IVariable v);
}
