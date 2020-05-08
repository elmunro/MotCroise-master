package pobj.motx.tme3.csp;

public class StratMin implements IChoixVar {

	@Override
	public IVariable chooseVar(ICSP problem) {
		// TODO : test if plus performant avec size et indice
		/*
		 * int minDomainSize = problem.getVars().get(0).getDomain().size();
		 * 
		 * int index = 0 ------------------
		 */
		IVariable minVar = problem.getVars().get(0);
		for (IVariable ivar : problem.getVars()) {
			if (ivar.getDomain().size() != 0) {
				minVar = ivar;
				break;
			}
		}
		for (IVariable ivar : problem.getVars()) {
			if (ivar.getDomain().size() != 0 && ivar.getDomain().size() < minVar.getDomain().size())
				minVar = ivar;
		}
		return minVar;
	}

}
