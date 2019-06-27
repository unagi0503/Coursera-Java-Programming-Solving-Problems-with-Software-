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
	Assignment week 3: 1-1
	
	Write a method named tester that will create your CSVParser and call each of the methods below in parts 2, 3, 4, and 5. You would start your code with:
	
	FileResource fr = new FileResource();
	CSVParser parser = fr.getCSVParser();
	
	Each time you want to use the parser with another method, you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
	
	parser = fr.getCSVParser();
	*/
	
	public void tester (){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		String result = countryInfo(parser, "Japan");
		System.out.println(result);
	}  
	
	
	
	
	/*
	Assignment week 3: 1-2
	
	Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String. This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country. The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports, followed by “: “, followed by the countries export value. For example, using the file exports_small.csv and the country Germany, the program returns the string:
	
		Germany: motor vehicles, machinery, chemicals: $1,547,000,000,000
	
	*/
	
	public String countryInfo (CSVParser parser, String country) {
		
		String info = "NOT FOUND";
		String exports;
		String value;
		
		for (CSVRecord record : parser) {
		
			String countryName = record.get("Country");
			
			if (countryName.equals(country)) {
				
				exports = record.get("Exports");
				value = record.get("Value (dollars)");
				
				info = countryName + ": " + exports + ": " + value + ": ";
				
				
				return info;
			} 			
		}
		
		return info;
		
	}
}
