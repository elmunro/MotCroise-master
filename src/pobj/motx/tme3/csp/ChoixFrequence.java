package pobj.motx.tme3.csp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ChoixFrequence implements IChoixValeur {
	@Override
	public List<String> orderValues(ICSP problem, IVariable v) {
		Collections.sort(v.getDomain(), ScrabbleComparer.Instance);
		return v.getDomain();
	}


	static class ScrabbleComparer implements Comparator<String>{
		private char FrenchScoreTable[] = { 1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 10, 1, 2, 1, 1, 3, 8, 1, 1, 1, 1, 4, 10, 10, 10, 10 };
		
		public static final ScrabbleComparer Instance = new ScrabbleComparer();
		
		@Override
		public int compare(String arg0, String arg1) {
			return Integer.compare(scrabbleScore(arg0), scrabbleScore(arg1));
		}
		
		
		int scrabbleScore(String mot) {
			int score = 0;
			for (Character c : mot.toCharArray()) {
				score += FrenchScoreTable[c - 'a'];
			}
			return score;
		}
	}
	
//	static class OccurenceComparer implements Comparator<String>{		
//		public static final ScrabbleComparer Instance = new ScrabbleComparer();
//		
//		@Override
//		public int compare(String arg0, String arg1) {
//			return Integer.compare(occurences(arg0), occurences(arg1));
//		}
//		
//		
//		int occurences(String mot) {
//			int occ = 0;
//			for (Character c : mot.toCharArray()) {
//				//TODO Implémenter
//			}
//			return occ;
//		}
//	}
}
