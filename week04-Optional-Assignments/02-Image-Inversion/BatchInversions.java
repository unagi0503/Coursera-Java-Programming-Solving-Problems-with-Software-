
import edu.duke.*;
import java.io.*;

/***
	Assignment 2: Image Inversion
	
 Write a program to create new images that are photographic negatives (or inverted images) of selected images and save these new images with filenames that are related to the original images, such as adding “inverted-” in front of the old filename. In inverting an image, a pixel’s red, blue, and green components are modified to be the exact opposite within the 0 to 255 range. That is, if a pixel’s red, blue, and green values are (34, 198, 240), then that same pixel in the inverted image would have the red, blue and green values of (221, 57, 15). Note that 255 - 34 is 221, 255 - 198 is 57, and 255 - 240 is 15.
***/

public class BatchInversions {
    
	public ImageResource makeInversion(ImageResource inImage) {
		
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		
		for (Pixel pixel: outImage.pixels()) {
			
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			
		    int invertedRed   = 255 - inPixel.getRed();
			int invertedGreen = 255 - inPixel.getGreen();
			int invertedBlue  = 255 - inPixel.getBlue();
					
			pixel.setRed(invertedRed);
			pixel.setGreen(invertedGreen);
			pixel.setBlue(invertedBlue);
		}
		
		return outImage;
	}
	
	
	public void selectAndConvert () {
		
		DirectoryResource dr = new DirectoryResource();
		
		for (File f : dr.selectedFiles()) {
			
			ImageResource inImage = new ImageResource(f);
			String inImageName = inImage.getFileName();
						
			ImageResource invertedImage = makeInversion(inImage);
			String invertedImgName = "images/" + "inverted-" + inImageName;
						
			invertedImage.setFileName(invertedImgName);
			
			invertedImage.draw();
			invertedImage.save();
			
		}
	}
}
