package view;

import model.GameBoard;

import javax.swing.*;
import java.awt.*;

public class MinesweeperGamePanel extends JPanel {
    private final JButton[][] buttons;

    public MinesweeperGamePanel(GameBoard gameBoard) {
        int rows = gameBoard.getRows();
        int cols = gameBoard.getCols();

        setLayout(new GridLayout(rows, cols));
        buttons = new JButton[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setPreferredSize(new Dimension(30, 30));
                add(buttons[i][j]);
            }
        }
    }

    public JButton[][] getButtons() {
        return buttons;
    }
}
