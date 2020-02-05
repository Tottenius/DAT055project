package viewer;

import javax.swing.JFrame;

public class GameWindowTemp extends JFrame{
	
	public GameWindowTemp(){
		this.add(new GamePanel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
