

/**
 * Week 03 Assignment - Parsing Weather Data 
 * a program to find the coldest day of the year and other interesting facts about the temperature and humidity in a day.
 *
 */
 
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class weatherData {
	
	
	/*** 04 Write the method [lowestHumidityInManyFiles] that has no parameters. This method returns a CSVRecord that has the lowest humidity over all the files. If there is a tie, then return the first such record that was found. You should also write a void method named [testLowestHumidityInManyFiles()] to test this method and to print the lowest humidity AND the time the lowest humidity occurred. Be sure to test this method on two files so you can check if it is working correctly. If you run this program and select the files for January 19, 2014 and January 20, 2014, you should get
	
	1  Lowest Humidity was 24 at 2014-01-20 19:51:00
	
	***/
	
	
	public CSVRecord lowestHumidityInManyFiles() {
		
		CSVRecord lowestHumiditySoFar = null;
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			CSVRecord currentRecord = lowestHumidityInFile(fr.getCSVParser());
			lowestHumiditySoFar = getLowestHumidityOfTwo(currentRecord, lowestHumiditySoFar);
		}
		
		return lowestHumiditySoFar; 
	}
	
	
	public void testLowestHumidityInManyFiles() {
		CSVRecord csv = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + csv.get("Humidity") +
				   " at " + csv.get("DateUTC"));
	}
	
	
	
	
	
	
	/*** 03 Write a method named [lowestHumidityInFile] that has one parameter, a CSVParser named parser. This method returns the CSVRecord that has the lowest humidity. If there is a tie, then return the first such record that was found.

	Note that sometimes there is not a number in the Humidity column but instead there is the string “N/A”. This only happens very rarely. You should check to make sure the value you get is not “N/A” before converting it to a number.

	Also note that the header for the time is either TimeEST or TimeEDT, depending on the time of year. You will instead use the DateUTC field at the right end of the data file to get both the date and time of a temperature reading.

	You should also write a void method named [testLowestHumidityInFile()] to test this method that starts with these lines:
	
	1  FileResource fr = new FileResource();
	2  CSVParser parser = fr.getCSVParser();
	3  CSVRecord csv = lowestHumidityInFile(parser);
	
	
	and then prints the lowest humidity AND the time the lowest humidity occurred. For example, for the file weather-2014-01-20.csv, the output should be:
	
	1  Lowest Humidity was 24 at 2014-01-20 19:51:00
	
	***/
	
	/* String Comparison 
			
			In Java, string equals() method compares the two given strings based on the data/content of the string. If all the contents of both the strings are same then it returns true. If all characters do not match, then it returns false. 
			
			Syntax:

			str1.equals(str2);
			
			URL: https://beginnersbook.com/2013/12/java-string-compareto-method-example/
	*/				
	
	
	public CSVRecord getLowestHumidityOfTwo(CSVRecord currentRecord, CSVRecord lowestHumiditySoFar) {
		
		if (currentRecord.get("Humidity").equals("N/A")) {
			return lowestHumiditySoFar;
			
		} else {
			
			if (lowestHumiditySoFar == null) {
				lowestHumiditySoFar = currentRecord;
			
			} else {
				int currentHumidity = Integer.parseInt(currentRecord.get("Humidity"));
				int lowestHumidity = Integer.parseInt(lowestHumiditySoFar.get("Humidity"));
				
				if (currentHumidity < lowestHumidity) {
					lowestHumiditySoFar = currentRecord;
				}
			}
		}
		
		return lowestHumiditySoFar;
	}
	
	
	public CSVRecord lowestHumidityInFile (CSVParser parser) {
		
		CSVRecord lowestHumiditySoFar = null;
		for (CSVRecord currentRecord : parser) {
			lowestHumiditySoFar = getLowestHumidityOfTwo(currentRecord, lowestHumiditySoFar);
		}
		return lowestHumiditySoFar;
	}
	
	
	public void testLowestHumidityInFile () {
		
		FileResource fr = new FileResource("data/2014/weather-2014-01-20.csv");
	    CSVParser parser = fr.getCSVParser();
	    CSVRecord csv = lowestHumidityInFile(parser);
		
		System.out.println("Lowest Humidity was " + csv.get("Humidity") +
				   " at " + csv.get("DateUTC"));
	}
	

	
	
	
	
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
	