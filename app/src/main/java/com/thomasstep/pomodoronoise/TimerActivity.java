package com.thomasstep.pomodoronoise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    protected Integer selectedNoise = R.raw.brown;
    protected Integer focusTime = 25;
    protected Integer breakTime = 5;
    protected Integer longBreakTime = 20;
    protected Boolean limitRounds = false;
    protected Integer desiredRounds = 4;

    protected MediaPlayer audioPlayer;
    protected CountDownTimer focusCountdownTimer;
    protected CountDownTimer breakCountdownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        selectedNoise = intent.getIntExtra(PomodoroNoise.SELECTED_NOISE, R.raw.brown);
        focusTime = intent.getIntExtra(PomodoroNoise.FOCUS_TIME, 25);
        breakTime = intent.getIntExtra(PomodoroNoise.BREAK_TIME, 5);
        longBreakTime = intent.getIntExtra(PomodoroNoise.LONG_BREAK_TIME, 20);
        limitRounds = intent.getBooleanExtra(PomodoroNoise.LIMIT_ROUNDS, false);
        desiredRounds = intent.getIntExtra(PomodoroNoise.DESIRED_ROUNDS, 4);

        // Capture the layout's TextView and set the string as its text
        TextView countdownText = findViewById(R.id.countdownText);
        countdownText.setText(focusTime.toString());

        audioPlayer = MediaPlayer.create(this, selectedNoise);

        focusCountdownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secsUntilFinish = (int) millisUntilFinished / 1000;
                int minutes = secsUntilFinish / 60 % 60;
                int seconds = secsUntilFinish % 60;
                String secondString = String.valueOf(seconds);
                if (seconds < 10) {
                    secondString = "0" + seconds;
                }
                countdownText.setText(minutes + ":" + secondString + " left of focusing");
            }

            @Override
            public void onFinish() {
                pauseNoise();
                breakCountdownTimer.start();
            }
        };

        breakCountdownTimer = new CountDownTimer(20000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int secsUntilFinish = (int) millisUntilFinished / 1000;
                int minutes = secsUntilFinish / 60 % 60;
                int seconds = secsUntilFinish % 60;
                String secondString = String.valueOf(seconds);
                if (seconds < 10) {
                    secondString = "0" + seconds;
                }
                countdownText.setText(minutes + ":" + secondString + " left of the break");
            }

            @Override
            public void onFinish() {
                startNoise();
                focusCountdownTimer.start();
            }
        };

        startPomodoroNoise();
    }

    /** onDestroy() is also called when finish() is called (see onStopButtonClicked()) */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        audioPlayer.stop();
    }

    /** Called when the user clicks the stop button */
    public void onStopButtonClicked(View view) {
        finish();
    }

    protected void startNoise() {
        audioPlayer.start();
    }

    protected void pauseNoise() {
        audioPlayer.pause();
    }

    protected void startPomodoroNoise() {
        startNoise();
        focusCountdownTimer.start();
    }
}