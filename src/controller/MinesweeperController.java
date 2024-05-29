//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package controller;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

import model.GameBoard;
import view.MinesweeperSelectLevel;
import view.MineswepergamePanel;

public class MinesweeperController implements GameControlListener {
    private static MinesweeperController instance;
    private MineswepergamePanel gamePanel;
    private GameBoard gameBoard;
    private boolean gameOver;
    private MinesweeperSelectLevel view;
    private JFrame gameFrame;



    public MinesweeperController(MineswepergamePanel gamePanel, GameBoard gameBoard, MinesweeperSelectLevel view) {
        this.gamePanel = gamePanel;
        this.gameBoard = gameBoard;
        this.view = view;
        this.gameOver = false;
        this.initializeGamePanel();
        this.showGameWindow();
        this.gamePanel.setControlListener(this);


    }

    // Xử lý sự kiện khi nhấn nút Pause




    private void showGameWindow() {

        gameFrame = new JFrame("Minesweeper");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.getContentPane().add(this.gamePanel);
        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);
    }

    private void initializeGamePanel() {
        JButton[][] buttons = this.gamePanel.getButtons();
        // Xử lý sự kiện cho nút pauseButton


        for (int i = 0; i < buttons.length; ++i) {
            for (int j = 0; j < buttons[0].length; ++j) {
                final int row = i;
                final int col = j;
                final JButton button = buttons[row][col];
                button.addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent e) {
                        if (!MinesweeperController.this.gameOver) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                MinesweeperController.this.revealCell(row, col);
                            } else if (SwingUtilities.isRightMouseButton(e)) {
                                MinesweeperController.this.flagCell(button);
                            }
                        }

                    }

                    public void mousePressed(MouseEvent e) {
                    }

                    public void mouseReleased(MouseEvent e) {
                    }

                    public void mouseEntered(MouseEvent e) {
                    }

                    public void mouseExited(MouseEvent e) {
                    }
                });
            }
        }

    }

    private void revealCell(int row, int col) {
        if (this.gameBoard.isMine(row, col)) {
            this.gamePanel.getButtons()[row][col].setText("M");
            JOptionPane.showMessageDialog((Component)null, "Game over!");
            this.revealAllMines();
            this.gameOver = true;
        } else {
            int count = this.gameBoard.getCount(row, col);
            if (count == 0) {
                this.gamePanel.getButtons()[row][col].setEnabled(false);
                this.expandEmptyCells(row, col);
            } else {
                this.gamePanel.getButtons()[row][col].setText(String.valueOf(count));
            }
        }

    }

    private void flagCell(JButton button) {
        if (!button.getText().equals("F")) {
            button.setText("F");
        } else {
            button.setText("");
        }

    }

    private void expandEmptyCells(int row, int col) {
        for(int r = Math.max(0, row - 1); r <= Math.min(this.gameBoard.getRows() - 1, row + 1); ++r) {
            for(int c = Math.max(0, col - 1); c <= Math.min(this.gameBoard.getCols() - 1, col + 1); ++c) {
                if (this.gameBoard.getCount(r, c) == 0 && this.gamePanel.getButtons()[r][c].isEnabled()) {
                    this.gamePanel.getButtons()[r][c].setEnabled(false);
                    this.expandEmptyCells(r, c);
                } else if (this.gameBoard.getCount(r, c) != 0) {
                    this.gamePanel.getButtons()[r][c].setText(String.valueOf(this.gameBoard.getCount(r, c)));
                }
            }
        }

    }

    private void revealAllMines() {
        for(int i = 0; i < this.gameBoard.getRows(); ++i) {
            for(int j = 0; j < this.gameBoard.getCols(); ++j) {
                if (this.gameBoard.isMine(i, j)) {
                    this.gamePanel.getButtons()[i][j].setText("M");
                }
            }
        }

    }
    //---------- start tam dung tro choi
    public void pauseGame() {
        // Tạm dừng trò chơi
        // Hiển thị cửa sổ tùy chọn
        int choice = JOptionPane.showOptionDialog(null, "Game Paused", "Pause Menu",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                new String[]{"Resume", "Exit", "Restart"}, "Resume");

        // Xử lý lựa chọn của người chơi
        switch (choice) {
            case JOptionPane.YES_OPTION: // Resume
                // Tiếp tục trò chơi
                break;
            case JOptionPane.NO_OPTION: // Exit
                System.exit(0); // Thoát khỏi chương trình
                break;
            case JOptionPane.CANCEL_OPTION: // Restart
                // Bắt đầu lại trò chơi
                restartGame();
                break;
        }
    }

    private void restartGame() {

        gameFrame.dispose(); // Đóng cửa sổ hiện tại
        view.setVisible(true); // Hiển thị lại cửa sổ chọn level
    }
    //----------------end tam dung tro choi
}
