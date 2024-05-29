//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.GameControlListener;
import model.GameBoard;

public class MineswepergamePanel extends JPanel {
    private final JButton[][] buttons;
        private GameControlListener controlListener;

    private JButton pauseButton;

    public MineswepergamePanel(GameBoard gameBoard) {
        this.controlListener = controlListener;


        int rows = gameBoard.getRows();
        int cols = gameBoard.getCols();

        // Sử dụng BorderLayout cho panel
        this.setLayout(new BorderLayout());

        // Tạo panel để chứa các ô trò chơi
        JPanel gamePanel = new JPanel(new GridLayout(rows, cols));

        this.buttons = new JButton[rows][cols];

        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < cols; ++j) {
                this.buttons[i][j] = new JButton();
                this.buttons[i][j].setPreferredSize(new Dimension(30, 30));

                gamePanel.add(this.buttons[i][j]);
            }
        }

        // Thêm panel chứa các ô trò chơi vào trung tâm của BorderLayout
        this.add(gamePanel, BorderLayout.CENTER);

        // Tạo một JPanel rỗng để tạo khoảng trống dưới cùng
        JPanel emptyPanel = new JPanel();
        this.add(emptyPanel, BorderLayout.SOUTH);

        // Tạo nút button và thêm vào phía dưới của BorderLayout
        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (controlListener != null) {
                    controlListener.pauseGame();
                }
            }
        });
        emptyPanel.add(pauseButton);


    }

    public void setControlListener(GameControlListener listener) {
        this.controlListener = listener;
    }

    public JButton[][] getButtons() {
        return this.buttons;
    }
}
