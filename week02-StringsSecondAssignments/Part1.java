
/**
 * 여기에 Part1 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part1 {
	
	static String startCodon = "ATG";
	static String taa = "TAA";
	static String tag = "TAG";
	static String tga = "TGA";
	
	
	//  Write the void method printAllGenes that has one String parameter dna, representing a string of DNA. In this method you should repeatedly find genes and print each one until there are no more genes. Hint: remember you learned a while(true) loop and break.
	
	public void printAllGenes (String dna) {
		
		int startIndex = dna.indexOf("ATG");
		
		while (true) {
			
			String geneStr = findGene(dna);
			
			if (geneStr.isEmpty()){
				break;
			} 
			System.out.println(geneStr);
			
			int geneHeadPos = dna.indexOf(geneStr, startIndex);
			startIndex = geneHeadPos + geneStr.length();
			dna = dna.substring(startIndex, dna.length());
			startIndex = 0;
			
		}
		
	}
	
	
	public void testprintAllGenes() {
				
		String dna01 = "CTGATGCCCAAATAGCTATGCTATAA"; 
		printAllGenes(dna01);
		System.out.println("");
		// expected result: "ATGCCCAAATAG", "ATGCTATAA"
		
		String dna02 = "CTGATGCCCAAATAGCTATGCTATAACC";
		printAllGenes(dna02);
		System.out.println("");
		// expected result: "ATGCCCAAATAG", "ATGCTATAA"
		
		String dna03 = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
		printAllGenes(dna03);
		System.out.println("");
		// expected result: "ATGATCTAA", "ATGCTGCAACGGTGA"
		
		String dna04 = "ACATGATCATAAGAAGATAATAGAGGGCCATGTAATT";
		printAllGenes(dna04);
		System.out.println("");
		// expected result: "ATGATCATAAGAAGATAA", "ATGTAA"
	}
	
	
	
	
	// Write the method findGene that has one String parameter dna, representing a string of DNA. In this method you should do the following:

	// Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string.
	
	// Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
	
	// Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
	
	// Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
	
	// Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
	
	
	public String findGene (String dna) {
		
		int startIndex = dna.indexOf("ATG");
		if (startIndex == -1) {
			return "";
		}
		
		int taaPos = findStopCodon(dna, startIndex, taa); // taaPos : Either length of dna Or the pos of "TAA"
		int tagPos = findStopCodon(dna, startIndex, tag); // taaPos : Either length of dna Or the pos of "TAA"
		int tgaPos = findStopCodon(dna, startIndex, tga); // taaPos : Either length of dna Or the pos of "TAA"
		
		int miniStopPos = Math.min(tgaPos, Math.min(taaPos, tagPos));
		
		if (miniStopPos != dna.length()) {
			return dna.substring(startIndex, miniStopPos + 3);
		}
		
		return "";
	}
	
	
	
	
	// Write the void method testFindGene that has no parameters. You should create five DNA strings. The strings should have specific test cases such as DNA with no “ATG”, DNA with “ATG” and one valid stop codon, DNA with “ATG” and multiple valid stop codons, DNA with “ATG” and no valid stop codons. Think carefully about what would be good examples to test. For each DNA string you should:
	
	// (1) Print the DNA string.
	// (2) Calculate the gene by sending this DNA string as an argument to findGene. If a gene exists following our algorithm above, then print the gene, otherwise print the empty string.
	
	public void testFindGene() {
	
		String dna01 = "CCCTTTTAA"; // No Start Codon "ATG"
		String dna02 = "ATGTTTACACCCGGG"; // No Stop Codon Whatsoever.
		String dna03 = "ATGTTTACACCCGGTAAGG"; // No Valid Stop Codon "TAA"
		String dna04 = "CTGATGCCTAACAAATGA"; // DAN with "ATG" Start Codon and One Valid Stop Codon "TGA". 
		String dna05 = "CTGATGCCCAAATAGTTTCTATAA"; // DNA with “ATG” and Multiple Vaild Stop Codons
		String dna06 = "AATGCTAACTAGCTGACTAAT";
		
		
		
		System.out.println("DAN String:" + dna01);
		String result01 = findGene(dna01);  // Either Empty String "" or Gene String 
		if (result01 != "") {
			System.out.println("Gene String: " + result01);
		} else {
			System.out.println(result01);
		}
		// expected result: ""
		
		
		System.out.println("DAN String:" + dna02);
		String result02 = findGene(dna02);  // Either Empty String "" or Gene String 
		if (result02 != "") {
			System.out.println("Gene String: " + result02);
		} else {
			System.out.println(result02);
		}
		// expected result: ""
		
		
		System.out.println("DAN String:" + dna03);
		String result03 = findGene(dna03);  // Either Empty String "" or Gene String 
		if (result03 != "") {
			System.out.println("Gene String: " + result03);
		} else {
			System.out.println(result03);
		}
		// expected result: ""
		
		
		System.out.println("DAN String:" + dna04);
		String result04 = findGene(dna04);  // Either Empty String "" or Gene String 
		if (result04 != "") {
			System.out.println("Gene String: " + result04);
		} else {
			System.out.println(result04);
		}
		// expected result: "ATGCCTAACAAATGA"
		
		
		System.out.println("DAN String:" + dna05);
		String result05 = findGene(dna05);  // Either Empty String "" or Gene String 
		if (result05 != "") {
			System.out.println("Gene String: " + result05);
		} else {
			System.out.println(result05);
		}
		// expected result: "ATGCCCAAATAG"
		
		
		System.out.println("DAN String:" + dna06);
		String result06 = findGene(dna06);  // Either Empty String "" or Gene String 
		if (result06 != "") {
			System.out.println("Gene String: " + result06);
		} else {
			System.out.println(result06);
		}
		
	}
	
	
	
	
	//  Write the method findStopCodon that has three parameters, a String parameter named dna, an integer parameter named startIndex that represents where the first occurrence of ATG occurs in dna, and a String parameter named stopCodon. 
	
	// This method returns the index of the first occurrence of stopCodon that appears past startIndex and is a multiple of 3 away from startIndex. If there is no such stopCodon, this method returns the length of the dna strand.
	
	public int findStopCodon (String dna, int startIndex, String stopCodon) {
		
		// Check if startIndex has a valid value
		if (startIndex == -1) {
			return -1;
		}
		
		
		// Return the index of stop codon.
		int stopCodonPos = dna.indexOf(stopCodon, startIndex + 3);
			
		while (stopCodonPos != -1) {
			
			int diff = stopCodonPos - startIndex;
			
			if (diff % 3 == 0) {
				return stopCodonPos;
			} else {
				stopCodonPos = dna.indexOf(stopCodon, stopCodonPos + 1);
			}
			
		}
		
		return dna.length(); 
	}
	
	
	
	
	//  Write the void method testFindStopCodon that calls the method findStopCodon with several examples and prints out the results to check if findStopCodon is working correctly. Think about what types of examples you should check. For example, you may want to check some strings of DNA that have genes and some that do not. What other examples should you check?
	
	public void testFindStopCodon () {
		
		// This method tests FindStopCodon methods on a few of DNA Strings.
		
		
		String dna01 = "CCCTTTTAA"; // No Start Codon "ATG"
		String dna02 = "ATGTTTACACCCGGG"; // No Stop Codon
		String dna09 = "ATGTTTACACCCGGTAAGG"; // No Stop Codon
		String dna03 = "CTGATGCCCAAATAA"; // Both of Start Codon and Stop Codon are contained. 
		
		String stopCodon = "TAA";
		
		int startIndex01 = dna01.indexOf("ATG");
		int startIndex02 = dna02.indexOf("ATG");
		int startIndex09 = dna09.indexOf("ATG");
		int startIndex03 = dna03.indexOf("ATG");
		
		int result01 = findStopCodon(dna01, startIndex01, taa);
		int result02 = findStopCodon(dna02, startIndex02, taa);
		int result09 = findStopCodon(dna09, startIndex09, taa);
		int result03 = findStopCodon(dna03, startIndex03, taa);
		
		
		if (result01 == -1) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna01);
			System.out.println("However, the DNA String Contains No Starting Codon, ATG");
			System.out.println("So we show -1");
			System.out.println("");
			
		} else if (result01 == dna01.length()) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna01);
			System.out.println("However, the DNA String Contains No Given Stop Codon " + stopCodon);
			System.out.println("So we show the length of the dna string " + dna01 + ": " + dna01.length());
			System.out.println(dna01.length());
			System.out.println("");
			
		} else {
			System.out.println("The Index of Stop Codon " + stopCodon + ": " + result01);
			System.out.println("");
		}
		
		
		if (result02 == -1) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna02);
			System.out.println("However, the DNA String Contains No Starting Codon, ATG");
			System.out.println("So we show -1");
			System.out.println("");
			
		} else if (result02 == dna02.length()) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna02);
			System.out.println("However, the DNA String Contains No Given Stop Codon " + stopCodon);
			System.out.println("So we show the length of the dna string " + dna02 + ": " + dna02.length());
			System.out.println(dna02.length());
			System.out.println("");
			
		} else {
			System.out.println("The Index of Stop Codon " + stopCodon + ": " + result02);
			System.out.println("");
		}
		
		
		if (result09 == -1) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna09);
			System.out.println("However, the DNA String Contains No Starting Codon, ATG");
			System.out.println("So we show -1");
			System.out.println("");
			
		} else if (result09 == dna09.length()) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna09);
			System.out.println("However, the DNA String Contains No Given Stop Codon " + stopCodon);
			System.out.println("So we show the length of the dna string " + dna09 + ": " + dna09.length());
			System.out.println(dna09.length());
			System.out.println("");
			
		} else {
			System.out.println("The Index of Stop Codon " + stopCodon + ": " + result09);
			System.out.println("");
		}
		
		
		
		if (result03 == -1) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna03);
			System.out.println("However, the DNA String Contains No Starting Codon, ATG");
			System.out.println("So we show -1");
			System.out.println("");
			
		} else if (result03 == dna03.length()) {
			
			System.out.println("We've tested the stop codon " + stopCodon + " on " + dna03);
			System.out.println("However, the DNA String Contains No Given Stop Codon " + stopCodon);
			System.out.println("So we show the length of the dna string " + dna03 + ": " + dna03.length());
			System.out.println(dna03.length());
			System.out.println("");
			
		} else {
			System.out.println("The Index of Stop Codon " + stopCodon + ": " + result03);
			System.out.println("");
		}
	
	} 
	
	
	
	
	public static void main (String[] args) {
        Part1 findGene = new Part1 ();
		//findGene.testFindStopCodon(); 
		findGene.testFindGene();
		//findGene.testprintAllGenes();
		
	}
}

