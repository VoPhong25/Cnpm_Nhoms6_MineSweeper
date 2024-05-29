//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import controller.MinesweeperController;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import model.GameBoard;
import model.GameLevel;

public class MinesweeperSelectLevel extends JFrame {
    private MinesweeperView parentView; // Thêm trường này
    private JComboBox<GameLevel> levelComboBox;
    private JButton startButton;

    public MinesweeperSelectLevel() {
        this.setTitle("Select Level");
        this.setDefaultCloseOperation(3);
        this.setSize(400, 200);
        this.setResizable(false);
        this.setLocationRelativeTo((Component)null);
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel controlPanel = new JPanel(new FlowLayout());
        this.levelComboBox = new JComboBox(GameLevel.values());
        controlPanel.add(this.levelComboBox);
        this.startButton = new JButton("Start");
        controlPanel.add(this.startButton);
        mainPanel.add(controlPanel, "Center");
        this.add(mainPanel);
        this.setVisible(true);
        this.startButton.addActionListener(new StartGameListener());
    }

    public void setParentView(MinesweeperView parentView) {
        this.parentView = parentView;
    }
    public GameLevel getSelectedLevel() {
        return (GameLevel)this.levelComboBox.getSelectedItem();
    }

    private class StartGameListener implements ActionListener {
        private StartGameListener() {
        }

        public void actionPerformed(ActionEvent e) {

            GameLevel selectedLevel = MinesweeperSelectLevel.this.getSelectedLevel();
            GameBoard gameBoard = new GameBoard(selectedLevel.getRows(), selectedLevel.getCols(), selectedLevel.getMines());

            MineswepergamePanel gamePanel = new MineswepergamePanel(gameBoard );

            if (parentView != null) {
                parentView.setPauseButtonVisible(true); // Hiển thị nút tạm dừng sau khi bắt đầu trò chơi
            }
            MinesweeperSelectLevel.this.setVisible(false);
            new MinesweeperController(gamePanel, gameBoard, MinesweeperSelectLevel.this);

        }



    }
}
