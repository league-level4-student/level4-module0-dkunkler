package _02_Pixel_Art;

import java.awt.Color;
import java.io.Serializable;

public class GridData implements Serializable{


private static final long serialVersionUID = 1L;
public int windowWidth;
public int windowHeight;
public int pixelWidth;
public int pixelHeight;
public int rows;
public int cols;

//1. Create a 2D array of pixels. Do not initialize it yet.
public Pixel pixels[][];

public Color color;

public GridData() {
	
}

}