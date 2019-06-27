/**
 * Reads a chosen CSV file of country exports and prints each country that exports coffee.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
	public void listExporters(CSVParser parser, String exportOfInterest) {
		//for each row in the CSV File
		for (CSVRecord record : parser) {
			//Look at the "Exports" column
			String export = record.get("Exports");
			//Check if it contains exportOfInterest
			
		  //if (export.indexOf(exportOfInterest) != -1)
			if (export.contains(exportOfInterest)) {
				//If so, write down the "Country" from that row
				String country = record.get("Country");
				System.out.println(country);
			}
		}
	}

	public void whoExportsCoffee() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		listExporters(parser, "coffee");
	}
	
	
	
	
	/* 
	Assignment 01
	
	Write a method named tester that will create your CSVParser and call each of the methods below in parts 2, 3, 4, and 5. You would start your code with:
	
	FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser();
	
	Each time you want to use the parser with another method, you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
	
	parser = fr.getCSVParser();
	*/
	
	public void tester (){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
	}  
	
	
}
