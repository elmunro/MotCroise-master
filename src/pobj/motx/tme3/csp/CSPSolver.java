package pobj.motx.tme3.csp;

import java.util.List;

public class CSPSolver {
	private IChoixVar stratVar;
	private IChoixValeur stratValue;
	static boolean backtracking = false;

	public ICSP solve(ICSP problem) {
		System.out.println("Solve : \n" + problem);

		// Condition terminale : succès
		if (problem.getVars().isEmpty()) {
			System.out.println("Problème résolu.");
			return problem;
		}

		// condition terminale : échec sur cette branche
		if (!problem.isConsistent()) {
			System.out.println("Problème invalide.");
			return problem;
		} else {
			System.out.println("Problème valide.");
		}

		// On est garantis que ! getVars().isEmpty(), testé au dessus
		IVariable vi = stratVar.chooseVar(problem);

		List<String> domain = stratValue.orderValues((ICSP) problem, vi);

		// TODO fonction reevaluate qui plutot que refaire tte la
		// grille va réviser le domaine actif et y rajoter les variables supprimés par
		// le passage de dictionnaire potentiel dans assign
		ICSP next = null;
		// On est garantis que toute variable a un domaine non nul
		for (String val : domain) {
			System.out.println("Fixe var :" + vi + " à " + val);
			if (backtracking) {
				backtracking = false;
				next = problem.assign_new(vi, val);
			}
			else {
				next = problem.assign(vi, val);
			}
			next = solve(next);
			if (next.isConsistent()) {
				return next;
			} else {
				System.out.println("Essai valeur suivante.");
			}
		}
		backtracking = true;
		System.out.println("Backtrack sur variable " + vi);
		return next;
	}

	public void setStrat(IChoixVar varStrat, IChoixValeur valStrat) {
		this.stratVar = varStrat;
		this.stratValue = valStrat;
	}

}
