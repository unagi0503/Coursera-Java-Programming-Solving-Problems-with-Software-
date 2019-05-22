
/**
 * 여기에 Part3 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
public class Part3 {
	
	static String startCodon = "ATG";
	static String taa = "TAA";
	static String tag = "TAG";
	static String tga = "TGA";
	
	
	
	public int countGenes (String dna) {
		
		int count = 0;
		int startIndex = dna.indexOf("ATG");
		
		while (true) {
			
			String geneStr = findGene(dna);
			
			if (geneStr.isEmpty()) {
				break;
			}
			
			count += 1;
			
			int geneHeadPos = dna.indexOf(geneStr, startIndex);
			startIndex = geneHeadPos + geneStr.length();
			dna = dna.substring(startIndex, dna.length());
			startIndex = 0;
			
		}
		
		return count;
	}
	
	
	public void testCountGenes () {
	
		String dna01 = "CCCTTTTAA"; // No Start Codon "ATG"
		String dna02 = "ATGTTTACACCCGGTAAGG"; // No Valid Stop Codon "TAA"
		String dna03 = "ATGATCTAATTTATGCTGCAACGGTGAAGA"; 
		// 2 Genes: "ATGATCTAA", "ATGCTGCAACGGTGA"
		
		int geneNum01 = countGenes(dna01); // 0
		int geneNum02 = countGenes(dna02); // 0
		int geneNum03 = countGenes(dna03); // 2
		
		System.out.println("The Number of Genes is " + geneNum01 + " in " + dna01);
		System.out.println("The Number of Genes is " + geneNum02 + " in " + dna02);
		System.out.println("The Number of Genes is " + geneNum03 + " in " + dna03);
		System.out.println("");
	}
	
	
	
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
	
	
	
	public static void main (String[] args) {
        Part3 searchGene = new Part3 ();
		//findGene.testFindStopCodon(); 
		//findGene.testFindGene();
		searchGene.testCountGenes();
		
	}
	
}
