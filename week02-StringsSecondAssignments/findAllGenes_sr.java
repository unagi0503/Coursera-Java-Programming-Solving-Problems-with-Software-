
/**
 * 여기에 findAllGenes_sr 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
import edu.duke.*;
 
public class findAllGenes_sr {
	
	
	static String startCodon = "ATG";
	static String taa = "TAA";
	static String tag = "TAG";
	static String tga = "TGA";
	
	
	public void testPrintAllGenes (){
		
		String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA"; 
		// 2 Genes: "ATGATCTAA", "ATGCTGCAACGGTGA"
		
		printAllGenes(dna);
		
	}
	
	
	public void printAllGenes (String dna){
		
		StorageResource geneList = getAllGenes(dna);
		
		for (String gene : geneList.data()) {
			System.out.println(gene);
		}
	}
	
	
	public StorageResource getAllGenes (String dna) {
		
		StorageResource geneList = new StorageResource();
		
		int startIndex = dna.indexOf("ATG");
		
		while (true) {
			
			String geneStr = findGene(dna);
			
			if (geneStr.isEmpty()){
				break;
			} 
			
			geneList.add(geneStr);
			
			int geneHeadPos = dna.indexOf(geneStr, startIndex);
			startIndex = geneHeadPos + geneStr.length();
			dna = dna.substring(startIndex, dna.length());
			startIndex = 0;
			
		}
		
		return geneList;
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
        findAllGenes_sr aladin = new findAllGenes_sr ();
		
		aladin.testPrintAllGenes();
		
	}
}
