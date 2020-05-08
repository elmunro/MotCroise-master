package pobj.motx.tme3.csp;

import java.util.Collections;
import java.util.List;

public class ChoixRandom implements IChoixValeur {


	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		List<String> list = v.getDomain();
		Collections.shuffle(list);
		return list;
	}

}
