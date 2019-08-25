

/**
 * Week 03 Assignment - Parsing Weather Data 
 * a program to find the coldest day of the year and other interesting facts about the temperature and humidity in a day.
 *
 */
 
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherData {
	
	
	/*** 02 fileWithColdestTemperature
	
	Write the method fileWithColdestTemperature that has no parameters. This method should return a string that is the name of the file from selected files that has the coldest temperature. You should also write a void method named testFileWithColdestTemperature() to test this method. Note that after determining the filename, you could call the method coldestHourInFile to determine the coldest temperature on that day. 
	
	***/
	
	
	
	public String getFileName (File file) {
	 
	    //  get the file name of  [FileResource fr] within the for loop from  [fileWithColdestTemperature] function.
		//  source: (SoF) Getting file path in java - markovuksanovic, 2010 @ stack overflow
		//  the crucial part is to use [getAbsoultePath] function.
		
	    String filename = file.getAbsolutePath();
		filename = filename.substring(filename.length() - 22, filename.length());
	    return filename;
    }
	
	
	
	public String fileWithColdestTemperature () {
	
		String coldestDayFileName = "";
		CSVRecord coldestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
					
			CSVRecord currentRecord = coldestHourInFile(fr.getCSVParser());
			
			if (currentRecord == getSmallestOfTwo(currentRecord, coldestSoFar)) {
				coldestDayFileName = getFileName(f); 
			}
				
		}
		
		return coldestDayFileName;
	}
	
	
	
	public void testFileWithColdestTemperature() {
		
		String coldestDayFileName = fileWithColdestTemperature();
		System.out.println("Coldest day was in file " + coldestDayFileName);
		
		String coldestDayFileNameYear = coldestDayFileName.substring(8,12);
		String coldestDayFileNamePath = "data/" + coldestDayFileNameYear + "/" + coldestDayFileName; 
		FileResource fr = new FileResource(coldestDayFileNamePath);
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		
		System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were:");
		
		for (CSVRecord currentRecord : fr.getCSVParser()) {
			String dateAndTime = currentRecord.get("DateUTC");
			String temperature = currentRecord.get("TemperatureF");
			System.out.println(dateAndTime + ": " + temperature);
		}
	}
	
	
	
	
	
	/*** 01 Coldest Hour in a day
	
	Write a method named coldestHourInFile that has one parameter, a CSVParser named parser. This method returns the CSVRecord with the coldest temperature in the file and thus all the information about the coldest temperature, such as the hour of the coldest temperature. You should also write a void method named testColdestHourInFile() to test this method and print out information about that coldest temperature, such as the time of its occurrence.

	NOTE: Sometimes there was not a valid reading at a specific hour, so the temperature field says -9999. You should ignore these bogus temperature values when calculating the lowest temperature.
	
	***/
	
	public CSVRecord getSmallestOfTwo(CSVRecord currentRecord, CSVRecord smallestSoFar) {
		
		if (smallestSoFar == null) {
			smallestSoFar = currentRecord;
		} 
		
		else {
			double currentTemp = Double.parseDouble(currentRecord.get("TemperatureF"));
			double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
			
			if (currentTemp < smallestTemp) {
				smallestSoFar = currentRecord;
			}
		}
		return smallestSoFar;
	}
	
		
	public CSVRecord coldestHourInFile (CSVParser parser) {
	
		CSVRecord coldestSoFar = null;
		for (CSVRecord currentRecord : parser) {
			coldestSoFar = getSmallestOfTwo(currentRecord, coldestSoFar);
		}
		return coldestSoFar;
	}
	
	
	public void testColdestHourInFile () {
	
		FileResource fr = new FileResource("data/2014/weather-2014-01-04.csv");
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("coldest temperature was " + coldest.get("TemperatureF") +
				   " at " + coldest.get("TimeEST"));
	
	}
}	