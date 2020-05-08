package pobj.motx.tme3.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pobj.motx.tme1.Grille;
import pobj.motx.tme1.GrilleLoader;
import pobj.motx.tme1.GrillePlaces;
import pobj.motx.tme2.Dictionnaire;
import pobj.motx.tme2.GrillePotentiel;
import pobj.motx.tme3.csp.*;

public class GrilleSolverTestMedium {
	@Test
	public void testMediumStrat_First_Std() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		// System.out.println(gp);
		assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
		CSPSolver solver = new CSPSolver( );

		solver.setStrat(new StratFirst(), new ChoixStandard());
		//solver.setStrat(new StratMin());
		
		long timestamp = System.currentTimeMillis();
		ICSP solution = solver.solve(problem);
		
		assertEquals(0, solution.getVars().size() );
		System.out.println("Solution test medium\n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}
	
	@Test
	public void testMediumStrat_Min_Random() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		// System.out.println(gp);
		assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
		CSPSolver solver = new CSPSolver( );

		solver.setStrat(new StratMin(), new ChoixRandom());
		
		long timestamp = System.currentTimeMillis();
		ICSP solution = solver.solve(problem);
		
		assertEquals(0, solution.getVars().size() );
		System.out.println("Solution test medium\n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}
	
	@Test
	public void testMediumStrat_Min_Freq() {
		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
		Grille gr = GrilleLoader.loadGrille("data/medium.grl");

		System.out.println(gr);

		GrillePlaces grille = new GrillePlaces(gr);
		GrillePotentiel gp = new GrillePotentiel(grille, gut);
		
		// System.out.println(gp);
		assertTrue(! gp.isDead());

		ICSP problem = new MotX(gp);
		CSPSolver solver = new CSPSolver( );

		solver.setStrat(new StratMin(), new ChoixFrequence());
		
		long timestamp = System.currentTimeMillis();
		ICSP solution = solver.solve(problem);
		
		assertEquals(0, solution.getVars().size() );
		System.out.println("Solution test medium\n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
	}
	
	
//	@Test
//	public void testLargeStrat_Min_Std() {
//		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
//		Grille gr = GrilleLoader.loadGrille("data/large.grl");
//
//		System.out.println(gr);
//
//		GrillePlaces grille = new GrillePlaces(gr);
//		GrillePotentiel gp = new GrillePotentiel(grille, gut);
//		
//		// System.out.println(gp);
//		assertTrue(! gp.isDead());
//
//		ICSP problem = new MotX(gp);
//		CSPSolver solver = new CSPSolver();
//
//		solver.setStrat(new StratMin(), new ChoixStandard());
//		
//		long timestamp = System.currentTimeMillis();
//		ICSP solution = solver.solve(problem);
//		
//		assertEquals(0, solution.getVars().size() );
//
//		System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
//	}
//	
	
//	@Test
//	public void testLargeStrat_Min_Random() {
//		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
//		Grille gr = GrilleLoader.loadGrille("data/large.grl");
//
//		System.out.println(gr);
//
//		GrillePlaces grille = new GrillePlaces(gr);
//		GrillePotentiel gp = new GrillePotentiel(grille, gut);
//		
//		// System.out.println(gp);
//		assertTrue(! gp.isDead());
//
//		ICSP problem = new MotX(gp);
//		CSPSolver solver = new CSPSolver();
//		
//		solver.setStrat(new StratMin(), new ChoixRandom());
//		
//		long timestamp = System.currentTimeMillis();
//		ICSP solution = solver.solve(problem);
//		
//		assertEquals(0, solution.getVars().size() );
//
//		System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
//	}
//	
//	
//	@Test
//	public void testHardStrat_Min_Random() {
//		Dictionnaire gut = Dictionnaire.loadDictionnaire("data/frgut.txt");
//		Grille gr = GrilleLoader.loadGrille("data/hard.grl");
//
//		System.out.println(gr);
//
//		GrillePlaces grille = new GrillePlaces(gr);
//		GrillePotentiel gp = new GrillePotentiel(grille, gut);
//		
//		// System.out.println(gp);
//		assertTrue(! gp.isDead());
//
//		ICSP problem = new MotX(gp);
//		CSPSolver solver = new CSPSolver();
//
//		solver.setStrat(new StratMin(), new ChoixRandom());
//		
//		long timestamp = System.currentTimeMillis();
//		ICSP solution = solver.solve(problem);
//		
//		assertEquals(0, solution.getVars().size() );
//
//		System.out.println("Solution \n" + solution + " \nCalculée en "+ (System.currentTimeMillis() - timestamp) +" ms " );
//	}

}
