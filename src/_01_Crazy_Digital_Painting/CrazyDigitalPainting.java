package _01_Crazy_Digital_Painting;

import java.awt.Color;

public class CrazyDigitalPainting {
	//1. Create two final static integers for the width and height of the display.
	final static int width = 720;
	final static int height = 480;
	
	//2. Create a 2D array of Color objects. You will need to import
	//java.awt.Color. Initialize the size of the array using the 
	//integers created in step 1.
	Color [][] colorArray = new Color[width][height];
	
	
	public CrazyDigitalPainting() {
		//3. Open the crazy_digital_painting.png file and look at the image.
		for(int y = 0; y<height; y++)
		{
			for(int x = 0; x<width; x++)
			{
				colorArray[x][y] = new Color(x % 256, (x / (y+1)) % 256, y % 256);
			}
			
		}
		//4. Iterate through the 2D array and initialize each Color object
		//   to a new color. The sample image was created using the following 
		//   pattern:
		//   colors[i][j] = new Color(i % 256, (i * j) % 256, j % 256);
		
		//5. Come up with your own pattern to make a cool crazy image.
		
		//6. Use the ColorArrayDisplayer class to call the displayColorsAsImage method 
		//   to show off your picture.
		ColorArrayDisplayer.displayColorsAsImage(colorArray);
	}
	
	public static void main(String[] args) {
		new CrazyDigitalPainting();
	}
}
