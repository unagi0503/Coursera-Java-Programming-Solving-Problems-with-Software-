/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
	public void printNames () {
		FileResource fr = new FileResource();
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			if (numBorn <= 100) {
				System.out.println("Name " + rec.get(0) +
						   " Gender " + rec.get(1) +
						   " Num Born " + rec.get(2));
			}
		}
	}

	public void totalBirths (FileResource fr) {
		int totalBirths = 0;
		int totalBoys = 0;
		int totalGirls = 0;
		int totalGirlsNames = 0;
		int totalBoysNames = 0;
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			int numBorn = Integer.parseInt(rec.get(2));
			totalBirths += numBorn;
			if (rec.get(1).equals("M")) {
				totalBoys += numBorn;
				totalBoysNames += 1;
			}
			else {
				totalGirls += numBorn;
				totalGirlsNames += 1;
			}
		}
		System.out.println("total births = " + totalBirths);
		System.out.println("female girls = " + totalGirls);
		System.out.println("male boys = " + totalBoys);
		System.out.println("female girls names = " + totalGirlsNames);
		System.out.println("male boys names = " + totalBoysNames);
	}

	public void testTotalBirths () {
		//FileResource fr = new FileResource();
		FileResource fr = new FileResource();
		totalBirths(fr);
	}
	
	public int getRank (int year, String name, String gender) {
		
		int Rank = 0;
		String dataName = "yob" + year;
		FileResource fr = new FileResource("us_babynames/us_babynames_by_year/" + dataName);
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					
				}
			}
		}
		
		return 0;
	}
}






