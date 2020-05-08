package pobj.motx.tme3.csp;

import java.util.ArrayList;
import java.util.List;

import pobj.motx.tme1.Emplacement;
import pobj.motx.tme2.*;

public class MotX implements ICSP {
	private List<IVariable> variables = new ArrayList<>();
	private List<DicoVariable> dicosVar = new ArrayList<>();
	private GrillePotentiel grille_pot;

	public MotX(GrillePotentiel gp) {
		grille_pot = gp;
		List<Dictionnaire> motsPot = gp.getMotsPot();
		for (int i = 0; i < motsPot.size(); i++) {
			Emplacement emp = grille_pot.getEmp().get(i);
			if (emp.hasCaseVide()) {
				DicoVariable d = new DicoVariable(i, gp);
				dicosVar.add(d);
				variables.add((IVariable) d);
			}
		}
	}

	@Override
	public List<IVariable> getVars() {
		return variables;
	}

	@Override
	public boolean isConsistent() {
		for(DicoVariable dv : dicosVar) {
			if(dv.getDomain().size() == 0)
				return false;
		}
		return true;
	}

	@Override
	public ICSP assign(IVariable vi, String val){
		if(vi instanceof DicoVariable) {
			DicoVariable dv = (DicoVariable) vi;
			int index = dv.getIndex();
			return new MotX(grille_pot.fixer(index, val, grille_pot.getMotsPot()));
		}
		return null;
	}

	@Override
	public ICSP assign_new(IVariable vi, String val) {
		if(vi instanceof DicoVariable) {
			DicoVariable dv = (DicoVariable) vi;
			int index = dv.getIndex();
			return new MotX(grille_pot.fixer(index, val));
		}
		return null;
	}
	
	public GrillePotentiel getGrille_pot() {
		return grille_pot;
	}
}
