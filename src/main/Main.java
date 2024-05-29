package main;

import view.MinesweeperView;

import javax.swing.*;

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