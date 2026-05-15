package app;

import gui.OrganizerGUI;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            OrganizerGUI gui =
                    new OrganizerGUI();

            gui.setVisible(true);
        });
    }
}