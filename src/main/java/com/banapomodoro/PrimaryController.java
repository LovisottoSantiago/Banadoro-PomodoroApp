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
    private Text circleCounter;
    
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
            if (pomo != null && pomo.getTime() > 0) {             
                pomo.Start();
                startButton.setDisable(true);
                pauseButton.setDisable(false);
                return;
            }

            int minutes = Integer.parseInt(minInput.getText());
            if (minutes > 0) {
                if (pomo != null) {
                    pomo = null; 
                }
                pomo = new Pomodoro(minutes, timeLeftCountdown, this::onPomodoroComplete);
                pomo.Start();
                startButton.setDisable(true); 
                pauseButton.setDisable(false);
            }
        } catch (NumberFormatException e) {
            timeLeftCountdown.setText("Por favor ingrese un número válido.");
        }
    }

    
    @FXML
    public void onPauseBtnClick() {
        if (pomo != null) {
            pomo.Pause();
            pauseButton.setDisable(true); 
            startButton.setDisable(false); 
        }
    }

    @FXML
    public void onRestartBtnClick() {
        if (pomo != null) {
            pomo.Stop(); 
            pomo = null; 
        }
    
        timeLeftCountdown.setText("00:00"); 
        startButton.setDisable(false); 
        pauseButton.setDisable(true);
    }

    
    private void onPomodoroComplete() {
        timeLeftCountdown.setText("00:00");
        startButton.setDisable(false); 
        pauseButton.setDisable(true);
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
    
}
