package main;

import javax.swing.SwingUtilities;

import viewer.ProgramStateHandeler;

/**
 * The main class for the program.
 *
 * @author anton
 */
public class Main {
    public static void main(final String[] args) {

        SwingUtilities.invokeLater(() -> new ProgramStateHandeler("level1"));
    }
}

//TODO: libGDX instead of swing?