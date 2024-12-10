package com.banapomodoro;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private TextField minInput;
    
    @FXML
    private Text timeLeftCountdown;
    
    @FXML 
    private Button startButton;

    private Pomodoro pomo;


    @FXML
    public void onStartBtnClick() {
        try {
            int minutes = Integer.parseInt(minInput.getText());
            if (minutes > 0) {
                pomo = new Pomodoro(minutes, timeLeftCountdown);
                pomo.Start();
            } else {
                timeLeftCountdown.setText("Por favor ingrese un tiempo válido.");
            }
        } catch (NumberFormatException e) {
            timeLeftCountdown.setText("Por favor ingrese un número válido.");
        }
    }

    
    @SuppressWarnings("exports")
    public Button getStartButton() {
        return startButton;
    }



}
