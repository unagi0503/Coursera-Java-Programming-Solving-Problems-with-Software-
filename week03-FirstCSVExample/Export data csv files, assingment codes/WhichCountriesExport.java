
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
	
	1. Write a method named tester that will create your CSVParser and 
	call each of the methods below in parts 2, 3, 4, and 5. You would start your code with:
	
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
	
	Each time you want to use the parser with another method, you will need to reset the parser by calling fr.getCSVParser() again to get a new parser.
	
		parser = fr.getCSVParser();
		
	*/
	
	public void tester (){
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();
		
		
		// call countryInfo method
		String result = countryInfo(parser, "Nauru");
		System.out.println(result);
		System.out.println("contry Info Done");
		System.out.println("");
		
		
		// call listExportersTwoProducts method
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "cotton", "flowers");
		System.out.println("two products Done");
		System.out.println("");
		
		
		//call numberOfExporters method
		parser = fr.getCSVParser();
		int count = numberOfExporters(parser, "cocoa");
		System.out.println(count);
		System.out.println("Number of exporters Done");
		System.out.println("");
		
		
		// call bigExporters method
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
		System.out.println("Big exporters Done");
		System.out.println("");
		
	}  
	
	
	
	
	
	/*
	Assignment week 3: 1-2
	
	2. Write a method named countryInfo that has two parameters, parser is a CSVParser and country is a String. This method returns a string of information about the country or returns “NOT FOUND” if there is no information about the country. The format of the string returned is the country, followed by “: “, followed by a list of the countries’ exports, followed by “: “, followed by the countries export value. For example, using the file exports_small.csv and the country Germany, the program returns the string:
	
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
	
	
	
	
	
	/* 
	Assignment week 3: 1-3
		
	3. Write a void method named listExportersTwoProducts that has three parameters, parser is a CSVParser, exportItem1 is a String and exportItem2 is a String. This method prints the names of all the countries that have both exportItem1 and exportItem2 as export items. For example, using the file exports_small.csv, this method called with the items “gold” and “diamonds” would print the countries 
	
		Namibia
		South Africa
		
	*/

	public void listExportersTwoProducts (CSVParser parser, String exportItem1, String exportItem2) {
	
		for (CSVRecord record : parser) {
			
			String export = record.get("Exports");
			String countryName = record.get("Country");
			
			if (export.contains(exportItem1) && export.contains(exportItem2)) {
				
				System.out.println(countryName);
				
			}				
		}		
	}
	
	
	
	
	
	/* 
	Assignment week 3: 1-4
	
	4. Write a method named numberOfExporters, which has two parameters, parser is a CSVParser, and exportItem is a String. This method returns the number of countries that export exportItem. For example, using the file exports_small.csv, this method called with the item “gold” would return 3. 
		
	*/
	
	public int numberOfExporters (CSVParser parser, String exportItem) {
		
		int numOfExportItem = 0;
		
		for (CSVRecord record : parser) {
			
			String export = record.get("Exports");
			
			if (export.contains(exportItem)) {
				
				numOfExportItem += 1;
				
			}
			
		}
		
		return numOfExportItem;
		
	}
	
	
	
	
	
	/*
	Assignment week 3: 1-5
	
	5. Write a void method named bigExporters that has two parameters, parser is a CSVParser, and amount is a String in the format of a dollar sign, followed by an integer number with a comma separator every three digits from the right. An example of such a string might be “$400,000,000”. This method prints the names of countries and their Value amount for all countries whose Value (dollars) string is longer than the amount string. You do not need to parse either string value as an integer, just compare the lengths of the strings. For example, if bigExporters is called with the file exports_small.csv and amount with the string $999,999,999, then this method would print eight countries and their export values shown here:
	
		Germany $1,547,000,000,000
		Macedonia $3,421,000,000
		Malawi $1,332,000,000
		Malaysia $231,300,000,000
		Namibia $4,597,000,000
		Peru $36,430,000,000
		South Africa $97,900,000,000
		United States $1,610,000,000,000
		
	*/
	
	public void bigExporters (CSVParser parser, String amount) {
		
		int amountLength = amount.length();
		
		for (CSVRecord record : parser) {
			
			int dollarLength = record.get("Value (dollars)").length();
			
			if (dollarLength > amountLength) {
				
				String countryName = record.get("Country");
				String exportValue = record.get("Value (dollars)");
				System.out.println(countryName + " " + exportValue);				
			}		
		}		
	}
}
