package poo;


import javafx.scene.input.KeyCode;




/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public interface KeyboardCtrl {
    void OnInput(KeyCode keyCode, boolean isPressed) throws InterruptedException;
}
