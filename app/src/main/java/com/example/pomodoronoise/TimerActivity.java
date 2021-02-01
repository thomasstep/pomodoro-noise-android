package com.example.pomodoronoise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TimerActivity extends AppCompatActivity {
    protected String selectedNoise = "Brown";
    protected Integer focusTime = 25;
    protected Integer breakTime = 5;
    protected Integer longBreakTime = 20;
    protected Boolean limitRounds = false;
    protected Integer desiredRounds = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        // Get the Intent that started this activity and extract the extras
        Intent intent = getIntent();
        selectedNoise = intent.getStringExtra(PomodoroNoise.SELECTED_NOISE);
        focusTime = intent.getIntExtra(PomodoroNoise.FOCUS_TIME, 25);
        breakTime = intent.getIntExtra(PomodoroNoise.BREAK_TIME, 5);
        longBreakTime = intent.getIntExtra(PomodoroNoise.LONG_BREAK_TIME, 20);
        limitRounds = intent.getBooleanExtra(PomodoroNoise.LIMIT_ROUNDS, false);
        desiredRounds = intent.getIntExtra(PomodoroNoise.DESIRED_ROUNDS, 4);

        // Capture the layout's TextView and set the string as its text
        TextView countdownText = findViewById(R.id.countdownText);
        countdownText.setText(selectedNoise);
    }
}