
import edu.duke.*;
import java.io.*;

/***
	Discussion Forum Question: 
	
	"Batch Grayscale code not creating a gray version of the image file"
	
	url : https://www.coursera.org/learn/java-programming/discussions/weeks/4/threads/F6IoZK7ZEemqAAp4wpQIeA
	
***/


/***
	
	Assignment 1: Batch Grayscale

 You have learned how to convert an image to grayscale, and how to select and process several images to convert them to grayscale and display them. You also learned how to copy an image and save it with a different filename. Now put this all together in one program that batch processes several images, and creates and saves new images (with new filenames) that are grayscale versions of each image.

 More specifically,

 --- Your program should let the user select multiple image files

 --- For each image, create a new image that is a grayscale version of the original image

 --- For each image, save the grayscale image in a new file with the same filename as the original image, but with the word “gray-” in front of the filename. For example, if the original file was named lion.png, the new image would be a grayscale image and be named gray-lion.png.

 Hint: Start with the Batch Grayscale program that processes many images, and add in code to save those files with new names.

***/
	
	
public class BatchGrayScale {
	
	public ImageResource makeGray(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute inPixel's red + inPixel's blue + inPixel's green
			//divide that sum by 3 (call it average)
			int average = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen())/3;
			//set pixel's red to average
			pixel.setRed(average);
			//set pixel's green to average
			pixel.setGreen(average);
			//set pixel's blue to average
			pixel.setBlue(average);
		}
		//outImage is your answer
		return outImage;
	}



	public void selectConvertAndSave () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			
			ImageResource inImage = new ImageResource(f);
			String inImageName = inImage.getFileName();
						
			ImageResource gray = makeGray(inImage);
			String grayName = "images/" + "gray-" + inImageName;
						
			gray.setFileName(grayName);
			
			gray.draw();
			gray.save();
			
		}
	}
}
