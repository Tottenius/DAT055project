package viewer;

import javax.swing.JOptionPane;
/**
 * A class that used for bringing up a JOptionPanel where a profile name can be entered in and created. 
 * @author Anton
 *
 */
public class Profile {

	String name = "You didnt choose a profile name";

	public Profile() {

		startDialogWindow();

	}

	private void startDialogWindow() {

		int g = -1;

		while (g < 0) {

			String input = JOptionPane.showInputDialog("Please enter your username: ");

			if (input == null) {
				input = "canceled";
			}

			if (input.length() > 1) {

				g++;
				this.name = input;

			}
		}
	}
/**
 * Give the profile name that was created.
 * @return Profile name
 */
	public String returnProfileName() {
		return this.name;
	}

}
