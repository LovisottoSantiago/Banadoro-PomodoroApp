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

    @FXML 
    private Button pauseButton;

    @FXML 
    private Button restartButton;

    private Pomodoro pomo;


    @FXML
    public void onStartBtnClick() {
        try {
            int minutes = Integer.parseInt(minInput.getText());
            if (minutes > 0) {
                if (pomo != null) {
                    pomo = null; 
                }

                pomo = new Pomodoro(minutes, timeLeftCountdown, this::onPomodoroComplete);
                pomo.Start();
                startButton.setDisable(true); 
            }
        } catch (NumberFormatException e) {
            timeLeftCountdown.setText("Por favor ingrese un número válido.");
        }
    }

    
    @SuppressWarnings("exports")
    public Button getStartButton() {
        return startButton;
    }

    @SuppressWarnings("exports")
    public Button getPauseButton() {
        return pauseButton;
    }

    @SuppressWarnings("exports")
    public Button getRestartButton() {
        return restartButton;
    }

    private void onPomodoroComplete() {
        timeLeftCountdown.setText("00:00");
        startButton.setDisable(false); 
    }


}
