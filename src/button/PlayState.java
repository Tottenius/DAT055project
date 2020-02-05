package button;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class PlayState implements ActionListener {
	private Button button1;
	private Button button2;


	//constructor
	public PlayState() {
		button1 = new Button(this, "Start", 300, 100 ,100, 40);
		button1 = new Button(this, "Exit", 300, 200 ,100, 40);

	}

	//game logic
	public void update( ) {
		
	}

	//game rendering (framställning)
	public void render (Graphics2D g) {
		button1.render(g);
		button2.render(g);	
	}

	//mouse input handling
	public void mousePressed (MouseEvent e) {
		button1.mousePresssed(e);
		button2.mousePresssed(e);	
	}

	public void mouseReleased (MouseEvent e) {
		button1.mouseReleased(e);
		button2.mouseReleased(e);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button1) {
			System.out.println("Wlecome");
		}
		
		if (e.getSource() == button2) {
			System.exit(0);
		}

	}
	
}
