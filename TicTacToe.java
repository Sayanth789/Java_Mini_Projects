// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;


// public class TicTacToe extends JFrame implements ActionListener {
//     private JButton[] buttons = new JButton[9];
//     private boolean xTurn = true;  // true =  x's turn , false = 0's turn.
    
//     public TicTacToe() {
//         super("Tic Tac Toe");

//         setLayout(new GridLayout(3, 3));
//         setSize(400, 400);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // create 9 buttons.
//         for (int i = 0; i < 9; i++)  {
//             buttons[i] = new JButton();
//             buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
//             buttons[i].addActionListener(this);
//             add(buttons[i]);
//         }
//         setVisible(true);
//     }
//     @Override

//     public void actionPerformed(ActionEvent e) {
//         JButton clicked = (JButton)e.getSource();
//         if (!clicked.getText().equals("")) {
//             return;  // already clicked.
//         }
//         clicked.setText(xTurn ? "x" : "o");
//         xTurn = !xTurn;

//         String winner = checkWinner();
//         if (winner != null) {
//             JOptionPane.showMessageDialog(this, winner + "wins!");
//             resetBoard();
//         } else if (isBoardFull()) {
//             JOptionPane.showMessageDialog(this, "It's a draw");
//             resetBoard();
//         }
//     }
//     private String checkWinner() {
//         // check rows.
//         for (int i  = 0; i < 9; i += 3) {
//             if (!buttons[i].getText().equals("") &&
//             buttons[i].getText().equals(buttons[i+1].getText()) &&
//             buttons[i+1].getText().equals(buttons[i+2].getText())) {
//                 return buttons[i].getText();
//             }
//         }

//         // check columns 
//         for (int i = 0; i < 3; i++) {
//             if (!buttons[i].getText().equals("") &&
//                 buttons[i].getText().equals(buttons[i+3].getText()) &&
//                 buttons[i+3].getText().equals(buttons[i+6].getText())) {
//                     return buttons[i].getText();
//                 }
//         }
//         // check for diagonals.
//         if (!buttons[0].getText().equals("") &&
//             buttons[0].getText().equals(buttons[4].getText()) && 
//             buttons[4].getText().equals(buttons[8].getText())) {
//             return buttons[0].getText();
//         }
        
//         if (!buttons[2].getText().equals("") &&
//             buttons[2].getText().equals(buttons[4].getText()) &&
//             buttons[4].getText().equals(buttons[6].getText())) {
//             return buttons[2].getText(); 
//         }
//         // No winner.
//         return null;
//     }
//     private boolean isBoardFull() {
//         for (JButton b : buttons) {  //For each JButton b in the buttons array, do something with b.”
//             if (b.getText().equals("")) {
//                 return false;
//             }    
//         }
//         xTurn = true;
//     }
//     private void resetBoard() {
//         for (JButton b : buttons) {
//             b.setText("");
//         }
//         xTurn = true;
//     }
//     public static void main(String[] args) {
//         SwingUtilities.invokeLater(() -> new TicTacToe());
//     }
// }

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private boolean xTurn = true;  // true = X's turn, false = O's turn.

    public TicTacToe() {
        super("Tic Tac Toe");

        setLayout(new GridLayout(3, 3));
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create 9 buttons.
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) {
            return;  // Already clicked.
        }
        clicked.setText(xTurn ? "X" : "O");
        xTurn = !xTurn;

        String winner = checkWinner();
        if (winner != null) {
            JOptionPane.showMessageDialog(this, winner + " wins!");
            resetBoard();
        } else if (isBoardFull()) {
            JOptionPane.showMessageDialog(this, "It's a draw!");
            resetBoard();
        }
    }

    private String checkWinner() {
        // Check rows.
        for (int i = 0; i < 9; i += 3) {
            if (!buttons[i].getText().equals("") &&
                buttons[i].getText().equals(buttons[i + 1].getText()) &&
                buttons[i + 1].getText().equals(buttons[i + 2].getText())) {
                return buttons[i].getText();
            }
        }

        // Check columns.
        for (int i = 0; i < 3; i++) {
            if (!buttons[i].getText().equals("") &&
                buttons[i].getText().equals(buttons[i + 3].getText()) &&
                buttons[i + 3].getText().equals(buttons[i + 6].getText())) {
                return buttons[i].getText();
            }
        }

        // Check diagonals.
        if (!buttons[0].getText().equals("") &&
            buttons[0].getText().equals(buttons[4].getText()) &&
            buttons[4].getText().equals(buttons[8].getText())) {
            return buttons[0].getText();
        }

        if (!buttons[2].getText().equals("") &&
            buttons[2].getText().equals(buttons[4].getText()) &&
            buttons[4].getText().equals(buttons[6].getText())) {
            return buttons[2].getText();
        }

        // No winner.
        return null;
    }

    private boolean isBoardFull() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void resetBoard() {
        for (JButton b : buttons) {
            b.setText("");
        }
        xTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }
}
