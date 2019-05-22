
/**
	* 여기에 Part1 클래스 설명을 작성하십시오.
	* 
	* @author (작성자 이름) 
	* @version (버전번호나 날짜)
*/

public class Part1 {
	
	public String findSimpleGene (String dna) {
		int startIndex = dna.indexOf("ATG");
		int stopIndex = dna.indexOf("TAA", startIndex + 1);
		
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
		String dna4 = "CTGATGAAACTGTGATAAACC";
		//String dna4 = "ATGAAATAA";
		String dna5 = "CTATGAGAACTGTGATAAACC";
		
		String result;
		
		System.out.println(dna1);
		result = findSimpleGene(dna1);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna2);
		result = findSimpleGene(dna2);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		
		System.out.println(dna3);
		result = findSimpleGene(dna3);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna4);
		result = findSimpleGene(dna4);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
		System.out.println(dna5);
		result = findSimpleGene(dna5);
		
		if (result == "") {
			System.out.println("");
		} else {
			System.out.println("Gene is " + result);
		}
		
	}
	
	public static void main (String[] args) {
        Part1 findGene = new Part1 ();
		findGene.testSimpleGene ();
	}
}




