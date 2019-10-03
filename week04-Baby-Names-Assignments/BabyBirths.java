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
		
		int rank = -1;
		int counter = 0;
		//String fileName = "us_babynames/us_babynames_by_year/" + "yob" + year + ".csv";
		String fileName = "us_babynames/us_babynames_test/" + "yob" + year + "short.csv";
		
		FileResource fr = new FileResource(fileName);
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				if (rec.get(0).equals(name)) {
					rank = counter + 1;
					return rank;
				} else {
					counter += 1;
				}
			}
		}
		
		return rank;
	}
	
	
	
	public void testGetRank () {
		int year = 2012;
		String name = "Mason";
		String gender = "M";
		
		int rank = getRank(year, name, gender);
		
		System.out.println("The Rank of " + name + ": " + rank);
	}
	
	
	
	public String getName (int year, int rank, String gender) {
		
		int counter = 0;
		//String fileName = "us_babynames/us_babynames_by_year/" + "yob" + year + ".csv";
		String fileName = "us_babynames/us_babynames_test/" + "yob" + year + "short.csv";
		
		FileResource fr = new FileResource(fileName);
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				counter += 1;
				if (rank == counter) {
					return rec.get(0);
				}
			}
		}
		
		return "NO NAME";
	}
	
	
	
	public void testGetName () {
		int year = 2012;
		int rank = 4;
		String gender = "M";
		
		String name = getName(year, rank, gender);
		
		System.out.println("The Name of the " + rank + "th rank: " + name);
	}
	
	
	
	public void whatIsNameInYear (String name, int year, int newYear, String gender) {
		
		
		int rank = getRank(year, name, gender);
		String nameInNewYear = getName(newYear, rank, gender);
		String pronoun;
		if (gender == "F") {
			pronoun = "she";
		} else {
			pronoun = "he";
		}
	
		System.out.println(name + " born in " + year + " would be " + nameInNewYear + " if " + pronoun + " was born in " + newYear);
		
	}
	
	public void testWhatIsNameInYear () {
		whatIsNameInYear("Isabella", 2012, 2014, "F");
	}
}






