
/**
 * 여기에 Part1 클래스 설명을 작성하십시오.
 * 
 * @author (작성자 이름) 
 * @version (버전번호나 날짜)
 */
 import edu.duke.*;
 import java.text.DecimalFormat;
 
public class Part1 {
	
	
	static String startCodon = "ATG";
	static String taa = "TAA";
	static String tag = "TAG";
	static String tga = "TGA";
	
	
	/*
	Part 3
	
	Write the void method processGenes that has one parameter sr, which is a StorageResource of strings. This method processes all the strings in sr to find out information about them. Specifically, it should:

	print all the Strings in sr that are longer than 9 characters
	print the number of Strings in sr that are longer than 9 characters
	print the Strings in sr whose C-G-ratio is higher than 0.35
	print the number of strings in sr whose C-G-ratio is higher than 0.35
	print the length of the longest gene in sr
	
	
	Write a method testProcessGenes. This method will call your processGenes method on different test cases. Think of five DNA strings to use as test cases. These should include: one DNA string that has some genes longer than 9 characters, one DNA string that has no genes longer than 9 characters, one DNA string that has some genes whose C-G-ratio is higher than 0.35, and one DNA string that has some genes whose C-G-ratio is lower than 0.35. Write code in testProcessGenes to call processGenes five times with StorageResources made from each of your five DNA string test cases.
	
	*/
	
	
	public void testProcessGenes () {
		
		StorageResource geneList = new StorageResource();
		
		FileResource fr = new FileResource("brca1line.fa");
		
		String dna = fr.asString();
		dna = dna.toUpperCase();
		
		geneList = getAllGenes(dna);
		
		System.out.println("The total number of Genes: " + geneList.size()); // print the total number of genes.
		
		// print all the genes.		
		for (String gene : geneList.data()){
			System.out.println(gene);
		}
		
		// print all the genes
		printAllGenes(dna);
		
		
		processGenes(geneList);
		
		/*
		// one DNA string that has some genes longer than 9 characters,
		
		String dna01 = "CTGATGCCCAAATAGCTATGCTACCCTAA"; // ATGCCCAAATAG, ATGCTACCCTAA
		geneList = getAllGenes(dna01);
		for (String gene : geneList.data()){
			System.out.println(gene);
		}
		processGenes(geneList);
		
		
		// one DNA string that has no genes longer than 9 characters
		String dna02 = "CTGATGTAGCTATGCTATAA"; // ATGTAG, ATGCTATAA
		geneList = getAllGenes(dna02);
		for (String gene : geneList.data()){
			System.out.println(gene);
		}
		processGenes(geneList);
		
		
		// one DNA string that has some genes whose C-G-ratio is higher than 0.35
		String dna03 = "ATGATCTAATTTATGCGGGCCCGGTGAAGA"; // "ATGATCTAA", "ATGCGGGCCCGGTGA"
		geneList = getAllGenes(dna03);
		processGenes(geneList);
		
		
		// one DNA string that has some genes whose C-G-ratio is lower than 0.35
		String dna04 = "ATGATCTAATTTATGCGGGCCCGGTGAAGA"; // "ATGATCTAA", "ATGCGGGCCCGGTGA"
		geneList = getAllGenes(dna04);
		processGenes(geneList);
		
		
		String dna05 = "ATGTTTACACCCGGTAAGG"; // "ATGATCTAA", "ATGCGGGCCCGGTGA"
		geneList = getAllGenes(dna05);
		processGenes(geneList);
		*/
	} 
	
	
	public void processGenes (StorageResource sr) {
		
		int count9 = 0;
		int countCG = 0;
		int longest = 0;
		
		
		for (String item : sr.data()) {
			
			if (item.length() > 9) {
				
				count9 += 1;
				System.out.println("Longer than 9: " + item);	
			}
			
			if (cgRatio(item) > 0.35) {
				
				countCG += 1;
				System.out.println("CG Ratio with being higher than 0.35: " + item);
			}
			
			if (item.length() >= longest) {
				longest = item.length();
			}
		}
		
		System.out.println("The number of Strings longer than 9 is: " + count9);
		System.out.println("The number of Strings with CG Ratio being higher than 0.35: " + count9);
		System.out.println("The Length of the Longest Gene in sr: " + longest);
		System.out.println("");
		System.out.println("");
	}
	
	
	// Part 2
	//
	// /Write the method cgRatio that has one String parameter dna, and returns the ratio of C’s and G’s in dna as a fraction of the entire strand of DNA. For example if the String were “ATGCCATAG,” then cgRatio would return 4/9 or .4444444.
	
	public void testCgRatio(){
		
		String dna = "ATGCCATAG"; // 0.444...
		
		DecimalFormat three = new DecimalFormat("#0.000"); 
		
		System.out.println("cg ratio on DNA String: " + dna);
		System.out.println("cg ratio is: " + three.format(cgRatio(dna)));
		
	}
	
	
	public double cgRatio (String dna) {
		
		double totalCount = dna.length();
		double cgCount = 0;
		double ratio;
		
		for (char ch : dna.toCharArray()) {
			if (ch == 'C' || ch == 'G') {
				cgCount += 1;
			}
		}
		
		return cgCount / totalCount;
	}
	
	
	public void testPrintAllGenes (){
		
		String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA"; 
		// 2 Genes: "ATGATCTAA", "ATGCTGCAACGGTGA"
		
		printAllGenes(dna);
		
	}
	
	
	/*
	
	Part 1
	
	This assignment is to write the code from the lesson to use a StorageResource to store the genes you find instead of printing them out. This will help you see if you really understood how to put the code together, and might identify a part that you did not fully understand. If you get stuck, then you can go back and watch the coding videos that go with this lesson again.
	
	1. Create a new Java project named StringsThirdAssignments. You can put all the classes for this programming exercise in this project.

	2. Create a new Java Class named Part1. Copy and paste the code from your Part1 class in your StringsSecondAssignments project into this class.

	3. Make a copy of the printAllGenes method called getAllGenes. Instead of printing the genes found, this method should create and return a StorageResource containing the genes found. Remember to import the edu.duke libraries otherwise you will get an error message cannot find the class StorageResource.

	4. Make sure you test your getAllGenes method.
	
	*/
	
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
        Part1 aladin = new Part1 ();
		
		// aladin.testPrintAllGenes();
		// aladin.testCgRatio();
		aladin.testProcessGenes();
	}	 

}
