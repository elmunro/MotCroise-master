package pobj.motx.tme3.csp;


public class StratContrainte implements IChoixVar {

	@Override
	public IVariable chooseVar(ICSP problem) {
		int max_index = 0;
		int max_size = 0;
		for(int i= 0; i<problem.getVars().size();i++) {
			int size = problem.getVars().get(i).getContraintesList().size();
			if(size > max_size)
				max_index = i;
		}
		return problem.getVars().get(max_index);
	}

}
