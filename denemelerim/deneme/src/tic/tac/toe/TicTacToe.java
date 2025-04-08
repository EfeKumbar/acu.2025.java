package tic.tac.toe;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame("TicTacToe");
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;

    TicTacToe() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textfield.setBackground(Color.WHITE);
        textfield.setForeground(Color.GREEN);
        textfield.setFont(new Font("Serif", Font.BOLD, 120));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Tic-tac-toe");
        textfield.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800, 100);

        button_panel.setLayout(new GridLayout(3, 3));
        button_panel.setBackground(Color.BLACK);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textfield);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    if (player1_turn) {
                        buttons[i].setForeground(new Color(255, 0, 0)); // X için kırmızı
                        buttons[i].setText("X");
                        player1_turn = false;
                        textfield.setText("O turn"); // O'nun sırası
                    } else {
                        buttons[i].setForeground(new Color(0, 0, 255)); // O için mavi
                        buttons[i].setText("O");
                        player1_turn = true;
                        textfield.setText("X turn"); // X'in sırası
                    }
                }
            }
        }
        check(); // Her hamleden sonra kazananı kontrol et
    }

    public void firstTurn() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (random.nextInt(2) == 0) {
            player1_turn = true;
            textfield.setText("X turn");
        } else {
            player1_turn = false;
            textfield.setText("O turn");
        }
    }

    public void check() {
        // Kazanma kontrolü
        for (int i = 0; i < 3; i++) {
            // Satır kontrolü
            if (buttons[i * 3].getText().equals(buttons[i * 3 + 1].getText()) && buttons[i * 3].getText().equals(buttons[i * 3 + 2].getText()) && !buttons[i * 3].getText().equals("")) {
                if (buttons[i * 3].getText().equals("X")) {
                    xWins(i * 3, i * 3 + 1, i * 3 + 2);
                } else {
                    oWins(i * 3, i * 3 + 1, i * 3 + 2);
                }
            }
            // Sütun kontrolü
            if (buttons[i].getText().equals(buttons[i + 3].getText()) && buttons[i].getText().equals(buttons[i + 6].getText()) && !buttons[i].getText().equals("")) {
                if (buttons[i].getText().equals("X")) {
                    xWins(i, i + 3, i + 6);
                } else {
                    oWins(i, i + 3, i + 6);
                }
            }
        }
        // Çapraz kontrol
        if (buttons[0].getText().equals(buttons[4].getText()) && buttons[0].getText().equals(buttons[8].getText()) && !buttons[0].getText().equals("")) {
            if (buttons[0].getText().equals("X")) {
                xWins(0, 4, 8);
            } else {
                oWins(0, 4, 8);
            }
        }
        if (buttons[2].getText().equals(buttons[4].getText()) && buttons[2].getText().equals(buttons[6].getText()) && !buttons[2].getText().equals("")) {
            if (buttons[2].getText().equals("X")) {
                xWins(2, 4, 6);
            } else {
                oWins(2, 4, 6);
            }
        }
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        textfield.setText("X Wins");
        disableButtons();
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.RED);
        buttons[b].setBackground(Color.RED);
        buttons[c].setBackground(Color.RED);
        textfield.setText("O Wins");
        disableButtons();
    }

    public void disableButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i].setEnabled(false);
        }
    }
}
