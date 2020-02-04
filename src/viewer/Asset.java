package viewer;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.*;

public class Asset extends JLabel {
	
	private final static int size = 20;
	private int x;
	private int y;
	
	public int getSize1() {
		return size;
	}
	
    public Asset(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBounds(x, y, getSize1(), getSize1());   
    }
    

	
}
