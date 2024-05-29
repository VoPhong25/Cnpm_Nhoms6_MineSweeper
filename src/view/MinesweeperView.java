//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import controller.MinesweeperController;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MinesweeperView extends JFrame {
    private JButton startButton;
    private JButton pauseButton; // tam dung tro choi
    public MinesweeperView() {
            this.setTitle("Minesweeper");
            this.setDefaultCloseOperation(3);
            this.setSize(400, 200);
            this.setResizable(false);
            this.setLocationRelativeTo((Component)null);
            JPanel mainPanel = new JPanel(new FlowLayout());
            this.startButton = new JButton("Start Game");
            this.pauseButton = new JButton("Pause");
            mainPanel.add(this.startButton);
            mainPanel.add(this.pauseButton);
            this.add(mainPanel);

        this.startButton.addActionListener(new StartGameListener());
        this.pauseButton.setVisible(false);
        this.setVisible(true);
        MinesweeperSelectLevel selectLevel = new MinesweeperSelectLevel();
        selectLevel.setParentView(MinesweeperView.this); // Thiết lập MinesweeperView
    }

    private class StartGameListener implements ActionListener {
        private StartGameListener() {
        }

        public void actionPerformed(ActionEvent e) {
            MinesweeperView.this.setVisible(false);
            new MinesweeperSelectLevel();

        }
    }


    public void setPauseButtonVisible(boolean visible) {
        pauseButton.setVisible(visible);
    }






}
