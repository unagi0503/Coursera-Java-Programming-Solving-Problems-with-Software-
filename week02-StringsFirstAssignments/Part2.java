
/**
	* 여기에 Part2 클래스 설명을 작성하십시오.
	* 
	* @author (작성자 이름) 
	* @version (버전번호나 날짜)
*/

public class Part2 {
	
	static String startCodon = "ATG";
	static String stopCodon = "TAA";
	
	public String findSimpleGene (String dna, String start, String stop) {
		
		String startSign = start;
		String stopSign = stop;
		
		if (Character.isLowerCase(dna.charAt(0)) == true) {
			startSign = start.toLowerCase();
			stopSign = stop.toLowerCase();
			dna = dna.toLowerCase();
		}
		
		int startIndex = dna.indexOf(startSign);
		int stopIndex = dna.indexOf(stopSign, startIndex + 1);
		
		if (startIndex == -1 || stopIndex == -1) {
			return "";
		} 
		if ((stopIndex - startIndex) % 3 == 0) {
			return dna.substring(startIndex, stopIndex + 3);
		}
		return "";
	}
	
	public void testSimpleGene () {
		//String dna1 = "AGGCAGTTTAAGTCGAGACA";
		String dna1 = "AAATGCCCTAACTAGATTAAGAAACC";
		String dna2 = "ATAGGGCTCGCAATACACAG";
		String dna3 = "TTTACCGCATCTTGCCCTAA";
		String dna4 = "cTGaTGAAACTGTGATAAACC";
		//String dna4 = "ATGAAATAA";
		String dna5 = "CTATGAGAACTGTGATAAACC";
		
		String result;
		
		System.out.println(dna1);
		result = findSimpleGene(dna1, startCodon, stopCodon);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna2);
		result = findSimpleGene(dna2, startCodon, stopCodon);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		
		System.out.println(dna3);
		result = findSimpleGene(dna3, startCodon, stopCodon);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna4);
		result = findSimpleGene(dna4, startCodon, stopCodon);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna5);
		result = findSimpleGene(dna5, startCodon, stopCodon);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
	}
	
	public static void main (String[] args) {
        Part2 findGene = new Part2 ();
		findGene.testSimpleGene ();
	}
}
