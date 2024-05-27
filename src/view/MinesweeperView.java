package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MinesweeperView extends JFrame {
    private JButton startButton;

    public MinesweeperView() {
        setTitle("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new FlowLayout());

        startButton = new JButton("Start Game");
        mainPanel.add(startButton);

        add(mainPanel);

        // Tạo một đối tượng của StartGameListener và gán nó vào nút "Start Game"
        startButton.addActionListener(new StartGameListener());

        // Hiển thị JFrame
        setVisible(true);
    }

    private class StartGameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Ẩn cửa sổ chính và mở cửa sổ chọn cấp độ
            setVisible(false);
            new MinesweeperSelectLevel();
        }
    }
}
