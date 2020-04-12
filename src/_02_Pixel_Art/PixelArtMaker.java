package _02_Pixel_Art;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class PixelArtMaker implements MouseListener, ActionListener{
	private JFrame window;
	private GridInputPanel gip;
	private GridPanel gp;
	ColorSelectionPanel csp;
	JButton saveButton;
	
	public void start() {
		gip = new GridInputPanel(this);	
		window = new JFrame("Pixel Art");
		window.setLayout(new FlowLayout());
		window.setResizable(false);
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		
		window.add(gip);
		window.add(saveButton);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}

	public void submitGridData(int w, int h, int r, int c, boolean load) {
		//might just need to check for the existence of the file
		if(load) {
//			gp = new GridPanel(); //overloaded GridPanel constructor will load data from save file
			GridData gd = GridPanel.load();
			gp = new GridPanel(gd);
			
			
		}
		else {
			gp = new GridPanel(w, h, r, c);
		}
		csp = new ColorSelectionPanel();
		window.remove(gip);
		window.add(gp);
		window.add(csp);
		gp.repaint();
		gp.addMouseListener(this);
		window.pack();
	}
	
	public static void main(String[] args) {
		new PixelArtMaker().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		gp.setColor(csp.getSelectedColor());
		System.out.println(csp.getSelectedColor());
		gp.clickPixel(e.getX(), e.getY());
		gp.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// call save
		GridData gd = new GridData();
		gd.windowWidth =gp.windowWidth;
		gd.windowHeight = gp.windowHeight;
		gd.rows = gp.rows;
		gd.cols = gp.cols;
		
		gd.pixelWidth = gp.pixelWidth;
		gd.pixelHeight = gp.pixelHeight;
		
		
		//2. Initialize the pixel array using the rows and cols variables.
		gd.pixels = new Pixel[gp.cols][gp.rows];
		for(int row = 0; row<gp.rows; row++)
		{
			for(int col = 0; col<gp.cols; col++)
			{
				gd.pixels[col][row] = gp.pixels[col][row];
			}
		}
		
		gp.save(gd);
		
	}	
}
