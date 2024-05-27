package main;

import javax.swing.SwingUtilities;
import view.MinesweeperView;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                (new MinesweeperView()).setVisible(true);
            }
        });
    }
}