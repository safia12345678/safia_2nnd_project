package tictactoe_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] cells = new JButton[3][3]; 
    private JLabel statusLabel = new JLabel("X's turn"); 
    private char currentPlayer = 'X';
    JFrame frame = new JFrame();
    
    public TicTacToeGUI() {
        super("Tic Tac Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col] = new JButton();
                cells[row][col].addActionListener(this);
                cells[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
                gridPanel.add(cells[row][col]);
            }
        }

        add(gridPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton cell = (JButton) e.getSource();

        if (cell.getText().equals("")) {
            cell.setText(Character.toString(currentPlayer));

            if (checkForWinner()) {
                JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                gameOver();
            } else if (checkForDraw()) {
                JOptionPane.showMessageDialog(this, "Draw!");
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText(currentPlayer + "'s turn");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid move!");
        }
    }

    private boolean checkForWinner() {
        for (int row = 0; row < 3; row++) {
            if (cells[row][0].getText().equals(cells[row][1].getText()) &&
                cells[row][1].getText().equals(cells[row][2].getText()) &&
                !cells[row][0].getText().equals("")) {
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (cells[0][col].getText().equals(cells[1][col].getText()) &&
                cells[1][col].getText().equals(cells[2][col].getText()) &&
                !cells[0][col].getText().equals("")) {
                return true;
            }
        }

        if (cells[0][0].getText().equals(cells[1][1].getText()) &&
            cells[1][1].getText().equals(cells[2][2].getText()) &&
            !cells[0][0].getText().equals("")) {
            return true;
        }
        if (cells[0][2].getText().equals(cells[1][1].getText()) &&
            cells[1][1].getText().equals(cells[2][0].getText()) &&
            !cells[0][2].getText().equals("")) {
            return true;
        }

        return false;
    }

    private boolean checkForDraw() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (cells[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                cells[row][col].setText("");
            }
        }
        currentPlayer = 'X';
        statusLabel.setText(currentPlayer + "'s turn");
    }
    public void gameOver(){
       
        Object[] option={"Restart","Exit"};
        int n=JOptionPane.showOptionDialog(frame, "Game Over!","Game Over",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,option,option[0]);
        if(n==0){
            frame.dispose();
            resetGame();
        }
        else{
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}


