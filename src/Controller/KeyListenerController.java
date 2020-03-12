package Controller;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import Controller.Direction;

/**
 * Class that utilizes keylistener for handeling keyinputs.
 * 
 * @author Anton
 * @version 2020-03-13
 */
public class KeyListenerController extends KeyAdapter {

private static volatile boolean isKeyPressed = false;
//set base Direction for enum
	private static Direction direction = Direction.RIGHT;
	
	
	public static Direction getDirection() {
		return KeyListenerController.direction;
	}	
	
/**
 * A method to say whether a key is currently pressed or not.
 * @return The status of the key
 */
public static boolean isKeyPressed() {
	return KeyListenerController.isKeyPressed;
}
/**
* Sets isKeyPressed to true or false.
* @param isKeyPressed
*/
public static void setKeyPressed(final boolean isKeyPressed) {
	KeyListenerController.isKeyPressed = isKeyPressed;
}
	
/**
 * Give the direction. 
 *
 * @return The direction.
 */

		@Override
		public void keyPressed(final KeyEvent e) {

			final int input = e.getKeyCode();
			switch (input) {

			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:

				direction = Direction.UP;
				break;

			case KeyEvent.VK_DOWN:
			case KeyEvent.VK_S:

				direction = Direction.DOWN;
				break;

			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:

				direction = Direction.RIGHT;
				break;

			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:

				direction = Direction.LEFT;
				break;

			case KeyEvent.VK_R:

				// GameWindowTemp.setRestartState();
				break;

			default:
				break;
			}
			isKeyPressed = true;
		}
	}

