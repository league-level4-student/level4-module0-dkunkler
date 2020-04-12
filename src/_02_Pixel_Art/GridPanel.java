package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JPanel;


public class GridPanel extends JPanel implements Serializable{
	private static final String DATA_FILE = "src/saveGrid.dat";
	
	private static final long serialVersionUID = 1L;
	public int windowWidth;
	public int windowHeight;
	public int pixelWidth;
	public int pixelHeight;
	public int rows;
	public int cols;
	
	//1. Create a 2D array of pixels. Do not initialize it yet.
	Pixel pixels[][];
	
	private Color color;
	
	public GridPanel(GridData gd) {
		this.windowWidth = gd.windowWidth;
		this.windowHeight = gd.windowHeight;
		this.rows = gd.rows;
		this.cols = gd.cols;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[cols][rows];
		for(int row = 0; row<rows; row++)
		{
			for(int col = 0; col<cols; col++)
			{
				pixels[col][row] = gd.pixels[col][row];
			}
		}
		
		//3. Iterate through the array and initialize each element to a new pixel.
		
		
	}
	
	
	
	
	public GridPanel(int w, int h, int r, int c) {
		this.windowWidth = w;
		this.windowHeight = h;
		this.rows = r;
		this.cols = c;
		
		this.pixelWidth = windowWidth / cols;
		this.pixelHeight = windowHeight / rows;
		
		color = Color.BLACK;
		
		setPreferredSize(new Dimension(windowWidth, windowHeight));
		
		//2. Initialize the pixel array using the rows and cols variables.
		pixels = new Pixel[cols][rows];
		for(int row = 0; row<rows; row++)
		{
			for(int col = 0; col<cols; col++)
			{
				pixels[col][row] = new Pixel(col,row);
			}
		}
		
		//3. Iterate through the array and initialize each element to a new pixel.
		
		
	}
	
	public void setColor(Color c) {
		color = c;
	}
	
	public void clickPixel(int mouseX, int mouseY) {
		//5. Use the mouseX and mouseY variables to change the color
		//   of the pixel that was clicked. *HINT* Use the pixel's dimensions.
		pixels[mouseX/pixelWidth][mouseY/pixelHeight].color = color;
		
	}
	
	public void save(GridData data) {
		try (FileOutputStream fos = new FileOutputStream(new File(DATA_FILE)); 
				ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			System.out.println("Saving file");
			oos.writeObject(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static GridData load() {
		try (FileInputStream fis = new FileInputStream(new File(DATA_FILE)); 
				ObjectInputStream ois = new ObjectInputStream(fis)) {
			System.out.println("loading file");
			return (GridData) ois.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// This can occur if the object we read from the file is not
			// an instance of any recognized class
			e.printStackTrace();
			return null;
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		//4. Iterate through the array.
		//   For every pixel in the list, fill in a rectangle using the pixel's color.
		//   Then, use drawRect to add a grid pattern to your display.
		for(int row = 0; row < rows; row++) 
		{
			for(int col = 0; col < cols; col++)
			{
				g.setColor(pixels[col][row].color);
				g.fillRect(col*pixelWidth,row*pixelHeight,pixelWidth,pixelHeight);
				g.setColor(Color.BLACK);
				g.drawRect(col*pixelWidth,row*pixelHeight,pixelWidth,pixelHeight);
			}
		}
		
	}
}
