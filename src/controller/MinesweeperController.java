package controller;

import model.GameLevel;
import model.GameBoard;
import view.MinesweeperSelectLevel;
import view.MinesweeperGamePanel;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MinesweeperController {
    private MinesweeperGamePanel gamePanel;
    private GameBoard gameBoard;
    private boolean gameOver;
    private MinesweeperSelectLevel view;

    public MinesweeperController(MinesweeperGamePanel gamePanel, GameBoard gameBoard, MinesweeperSelectLevel view) {
        this.gamePanel = gamePanel;
        this.gameBoard = gameBoard;
        this.view = view; // Lưu trữ đối tượng MinesweeperView
        this.gameOver = false;
        initializeGamePanel();
        showGameWindow(); // Hiển thị cửa sổ trò chơi sau khi khởi tạo controller
    }

    private void showGameWindow() {
        JFrame frame = new JFrame("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(gamePanel); // Thêm bảng trò chơi vào content pane của JFrame
        frame.pack();
        frame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        frame.setVisible(true); // Hiển thị cửa sổ
    }

    private void initializeGamePanel() {
        JButton[][] buttons = gamePanel.getButtons();
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[0].length; j++) {
                JButton button = buttons[i][j];
                int row = i;
                int col = j;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!gameOver) {
                            if (SwingUtilities.isLeftMouseButton(e)) {
                                revealCell(row, col);
                            } else if (SwingUtilities.isRightMouseButton(e)) {
                                flagCell(button);
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                    }
                });
            }
        }
    }

    private void revealCell(int row, int col) {
        if (gameBoard.isMine(row, col)) {
            // Xử lý khi người chơi nhấp vào ô mìn
            gamePanel.getButtons()[row][col].setText("M");
            JOptionPane.showMessageDialog(null, "Game over! You clicked on a mine.");
            // Hiển thị lại toàn bộ các ô mìn
            revealAllMines();
            gameOver = true;
        } else {
            int count = gameBoard.getCount(row, col);
            if (count == 0) {
                // Xử lý khi người chơi nhấp vào ô trống
                gamePanel.getButtons()[row][col].setEnabled(false);
                expandEmptyCells(row, col);
            } else {
                // Xử lý khi người chơi nhấp vào ô có số
                gamePanel.getButtons()[row][col].setText(String.valueOf(count));
            }
        }
    }

    private void flagCell(JButton button) {
        // Xử lý cắm cờ ở đây
        if (!button.getText().equals("F")) {
            button.setText("F");
        } else {
            button.setText("");
        }
    }

    private void expandEmptyCells(int row, int col) {
        // Xử lý để mở rộng hiển thị các ô trống kề cạnh ô đó
        for (int r = Math.max(0, row - 1); r <= Math.min(gameBoard.getRows() - 1, row + 1); r++) {
            for (int c = Math.max(0, col - 1); c <= Math.min(gameBoard.getCols() - 1, col + 1); c++) {
                if (gameBoard.getCount(r, c) == 0 && gamePanel.getButtons()[r][c].isEnabled()) {
                    gamePanel.getButtons()[r][c].setEnabled(false);
                    expandEmptyCells(r, c);
                } else if (gameBoard.getCount(r, c) != 0) {
                    gamePanel.getButtons()[r][c].setText(String.valueOf(gameBoard.getCount(r, c)));
                }
            }
        }
    }

    private void revealAllMines() {
        // Hiển thị lại toàn bộ các ô mìn trên bảng trò chơi
        for (int i = 0; i < gameBoard.getRows(); i++) {
            for (int j = 0; j < gameBoard.getCols(); j++) {
                if (gameBoard.isMine(i, j)) {
                    gamePanel.getButtons()[i][j].setText("M");
                }
            }
        }
    }

    // Phương thức để hiển thị cửa sổ chọn cấp độ
    private void showLevelSelectionDialog(GameLevel selectedLevel) {
        String message = "You selected " + selectedLevel;
        JOptionPane.showMessageDialog(view, message);

        // Sau khi người dùng chọn cấp độ và bắt đầu trò chơi, bạn có thể tiến hành các bước tiếp theo ở đây.
        // Ví dụ: Khởi tạo trò chơi với cấp độ tương ứng và hiển thị trò chơi.
        // Tạo GameBoard dựa trên cấp độ đã chọn
        GameBoard gameBoard = new GameBoard(selectedLevel.getRows(), selectedLevel.getCols(), selectedLevel.getMines());
        // Tạo panel trò chơi dựa trên GameBoard
        MinesweeperGamePanel gamePanel = new MinesweeperGamePanel(gameBoard);
        // Khởi tạo controller cho trò chơi
        MinesweeperController gameController = new MinesweeperController(gamePanel, gameBoard, view);
    }
}