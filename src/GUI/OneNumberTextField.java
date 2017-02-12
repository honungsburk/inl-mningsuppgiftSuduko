package GUI;

import javafx.scene.control.TextField;

/**
 *  Textfield where the user only can input a number between 1-9
 */
public class OneNumberTextField extends TextField {

    @Override
    public void replaceText(int start, int end, String text) {
        if (matches(text)) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String text) {
        if (matches(text)) {
            super.replaceSelection(text);
        }
    }

    private boolean matches(String text) {
        return text.isEmpty() || (getText().length() < 1) && text.matches("[1-9]") ;
    }

}