package Sudoku;

import GUI.OneNumberTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Frank Hampus Weslien on 2017-02-14.
 */
public class keyPressedTextField implements KeyListener {
    OneNumberTextField textField;
    int x;
    int y;
    String finalStyle;
    Sudoku sudoku;

    public keyPressedTextField(OneNumberTextField textField, int x, int y, String finalStyle, Sudoku sudoku) {
        this.textField = textField;
        this.x = x;
        this.y = y;
        this.finalStyle = finalStyle;
        this.sudoku = sudoku;

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int value;
        try {
            value = Integer.parseInt(textField.getText());
        } catch (NumberFormatException err) {
            //Tom ruta lästes, sätt in noll.
            value = 0;
        }
        if(sudoku.userIndexedInputValid(x, y, value)) {
            sudoku.set(x, y, value);
            System.out.println(sudoku.get(x, y));
            textField.setStyle(finalStyle);
        } else{
            textField.setStyle("-fx-control-inner-background: #FF0000; -fx-background-radius: 0");
        }
    }
    }
}
