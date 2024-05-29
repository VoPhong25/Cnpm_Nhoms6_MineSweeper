package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.MinesweeperController;
import model.GameBoard;
import model.GameLevel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MinesweeperSelectLevel extends JFrame {
    private JComboBox<GameLevel> levelComboBox;
    private JButton startButton;

    public MinesweeperSelectLevel() {
        setTitle("Select Level");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());

        levelComboBox = new JComboBox<>(GameLevel.values());
        controlPanel.add(levelComboBox);

        startButton = new JButton("Start");
        controlPanel.add(startButton);

        mainPanel.add(controlPanel, BorderLayout.CENTER);

        add(mainPanel);

        // Hiển thị JFrame
        setVisible(true);

        // Tạo một đối tượng của StartGameListener và gán nó vào nút "Start Game"
        startButton.addActionListener(new StartGameListener());
    }
    public GameLevel getSelectedLevel() {
        return (GameLevel) levelComboBox.getSelectedItem();
    }
    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            GameLevel selectedLevel = getSelectedLevel();
            GameBoard gameBoard = new GameBoard(selectedLevel.getRows(), selectedLevel.getCols(), selectedLevel.getMines());
            MinesweeperGamePanel gamePanel = new MinesweeperGamePanel(gameBoard);
            MinesweeperController gameController = new MinesweeperController(gamePanel, gameBoard, MinesweeperSelectLevel.this);
            setVisible(false);
//            showLevelSelectionDialog(selectedLevel);
        }
    }

//    public GameLevel getSelectedLevel() {
//        return (GameLevel) levelComboBox.getSelectedItem();
//    }

//    private void showLevelSelectionDialog(GameLevel selectedLevel) {
//        String message = "You selected " + selectedLevel;
//        JOptionPane.showMessageDialog(this, message);
//
//        // Sau khi người dùng chọn cấp độ và bắt đầu trò chơi, bạn có thể tiến hành các bước tiếp theo ở đây.
//        // Ví dụ: Khởi tạo trò chơi với cấp độ tương ứng và hiển thị trò chơi.
//        GameBoard gameBoard = new GameBoard(selectedLevel.getRows(), selectedLevel.getCols(), selectedLevel.getMines());
//        MinesweeperGamePanel gamePanel = new MinesweeperGamePanel(gameBoard);
//        MinesweeperController gameController = new MinesweeperController(gamePanel, gameBoard, this);
//
//        // Ẩn cửa sổ chọn cấp độ
//        setVisible(false);
//    }
}
