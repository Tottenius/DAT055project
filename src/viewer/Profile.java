package viewer;

import javax.swing.JOptionPane;

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
	// return standard name!

	public String returnProfileName() {
		return this.name;
	}

}
