/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

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
		String fileName = "us_babynames/us_babynames_by_year/" + "yob" + year + ".csv";
		//String fileName = "us_babynames/us_babynames_test/" + "yob" + year + "short.csv";
		
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
		
		int year = 1971;
		String name = "Frank";
		String gender = "M";
		
		int rank = getRank(year, name, gender);
		
		System.out.println("The Rank of " + name + " in " + year + " : " + rank);
	}
	
	
	
	public String getName (int year, int rank, String gender) {
		
		int counter = 0;
		String fileName = "us_babynames/us_babynames_by_year/" + "yob" + year + ".csv";
		//String fileName = "us_babynames/us_babynames_test/" + "yob" + year + "short.csv";
		
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
		
		int year = 1980;
		int rank = 350;
		String gender = "F";
		
		String name = getName(year, rank, gender);
		
		System.out.println("The Name of the " + rank + "th rank in " + year + " : " + name);
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
		
		String name = "Owen";
		int year = 1974;
		int newYear = 2014;
		String gender = "M";
		
		whatIsNameInYear(name, year, newYear, gender);
	}
	
	
	
	public int yearOfHighestRank (String name, String gender) {
		
		// getRank(year, name, gender);
		
		String filename;
		
		int rankSoFar = -1;
		int currentRank;
		
		int yearSoFar = -1;
		int currentYear;
				
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			
			filename = f.getName();
			currentYear = Integer.parseInt(filename.substring(3,7));
			currentRank = getRank(currentYear, name, gender);
			
			if (-1 != currentRank) {
				
				if (-1 == rankSoFar) {
					rankSoFar = currentRank;
					yearSoFar = currentYear;
				} else {
					if (currentRank < rankSoFar) {
						rankSoFar = currentRank;
						yearSoFar = currentYear;
					}
				}
			}					
		}
		
		return yearSoFar;
	}
	
	
	
	public void testYearOfHighestRank() {
		
		String name = "Mich";
		String gender = "M";
		
		int highestYear = yearOfHighestRank(name, gender); 
		
		System.out.println("The Year of the Highest Rank for " + name + " is " + highestYear);
	}
	
	
	
	public double getAverageRank (String name, String gender) {
		
		String filename;
		
		double totalRank = 0.0;
		int currentRank;
		
		int totalYear = 0;
		int currentYear; 
		
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			
			filename = f.getName();
			currentYear = Integer.parseInt(filename.substring(3,7));
			currentRank = getRank(currentYear, name, gender);
			
			if (-1 != currentRank) {
				totalRank += currentRank;
				totalYear += 1;
			}
		}
		
		
		if (totalYear == 0) {
			return -1.0;
		} 
		
		return totalRank/totalYear;
	}
	
	
	
	public void testGetAverageRank () {
		
		String name = "Robert";
		String gender = "M";
		
		double averageRank = getAverageRank(name, gender);  // name Jacob and gender ‘M’ and selecting the three test files above results in returning 2.66.
		
		System.out.println("The Average Rank of " + name + " is " + String.format("%.2f", averageRank));
	}
	
	
	
	public int getTotalBirthsRankedHigher (int year, String name, String gender) {
		
		String fileName = "us_babynames/us_babynames_by_year/" + "yob" + year + ".csv";
		//String fileName = "us_babynames/us_babynames_test/" + "yob" + year + "short.csv";
		
		int rank = getRank(year, name, gender);
		System.out.println("rank: " + rank + " " + name);
		
		int totalBirths = 0;
		int count = 0;
		int currentRank;
		
		
		FileResource fr = new FileResource(fileName);
		
		if (rank == -1) {
			for (CSVRecord rec : fr.getCSVParser(false)) {
				if (rec.get(1).equals(gender)) {
					totalBirths += Integer.parseInt(rec.get(2));
				}
			}
			System.out.println("inside of rank == -1 clause");	
		}
		
		
		
		for (CSVRecord rec : fr.getCSVParser(false)) {
			if (rec.get(1).equals(gender)) {
				currentRank = getRank(year, rec.get(0), rec.get(1));
				//System.out.println("Inside for loop " + currentRank);
				if (currentRank < rank) {
					totalBirths += Integer.parseInt(rec.get(2));
				} else {
					return totalBirths;
				}
			}
			
		}
		
		return totalBirths;
	}
	
	
	
	public void testGetTotalBirthsRankedHigher () {
		
		int year = 1990;
		String name = "Drew";
		String gender = "M";
		
		int totalBirthsRankedHigher = getTotalBirthsRankedHigher(year, name, gender); 
		
		System.out.println(totalBirthsRankedHigher);
		
	}
	
}






