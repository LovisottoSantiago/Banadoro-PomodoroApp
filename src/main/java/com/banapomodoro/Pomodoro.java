package com.banapomodoro;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Pomodoro {
  private Timer timer;
  private int timeLeft;
  private final Text timeLeftCountdown;
  private final Runnable onComplete;
  private boolean isPaused = false;

  public Pomodoro(int min, Text timeLeftCountdown, Runnable onComplete) {
    this.timeLeft = min * 60;
    this.timeLeftCountdown = timeLeftCountdown;
    this.onComplete = onComplete;
  }

  public int getTime() {
    return timeLeft;
  }

  public void Start() {
    if (isPaused) {
      
      isPaused = false;
      return;
    }

    timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> {
          if (!isPaused) {
            if (timeLeft > 0) {
              int minutes = timeLeft / 60;
              int seconds = timeLeft % 60;

              timeLeftCountdown.setText((String.format("%02d:%02d", minutes, seconds)));
              timeLeft--;
            } else {
              timeLeftCountdown.setText("00:00");
              Stop();
              Alert();
              onComplete.run();
            }
          }
        });
      }
    }, 0, 1000);
  }

  public void Pause() {
    isPaused = true; 
  }

  public void Resume() {
    isPaused = false; 
  }

  public void Stop() {
    if (timer != null) {
      timer.cancel();
    }
    timer = null;
    isPaused = false;
  }

  public void Alert() {
    Stage newStage = new Stage();
    newStage.setTitle("DESCANSA TROLAZO!!!");
    newStage.initModality(Modality.APPLICATION_MODAL);

    StackPane layout = new StackPane();
    layout.setStyle("-fx-background-color: red;");

    ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/assets/img/bana.png")));

    imageView.setPreserveRatio(true);
    imageView.setCache(true);
    imageView.setFitHeight(479);
    imageView.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

    layout.getChildren().add(imageView);

    Scene newScene = new Scene(layout, 756, 479);
    newStage.setScene(newScene);

    newStage.show();
    newStage.toFront();
    newStage.requestFocus();
    newStage.setFullScreen(true);
    newStage.setAlwaysOnTop(true);

    // Sound
    try {
      File soundFile = new File("cdt-el-bananero.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
      Clip clip = AudioSystem.getClip();
      clip.open(audioStream);
      clip.start();

      newStage.setOnCloseRequest((WindowEvent we) -> {
        if (clip.isRunning()) {
          clip.stop();
          clip.close();
        }
      });

    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {

    }

  }

}
