package com.example.pomodoronoise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class PomodoroNoise extends AppCompatActivity {
    public static final String SELECTED_NOISE = "com.thomasstep.pomodoronoise.SELECTED_NOISE";
    public static final String FOCUS_TIME = "com.thomasstep.pomodoronoise.FOCUS_TIME";
    public static final String BREAK_TIME = "com.thomasstep.pomodoronoise.BREAK_TIME";
    public static final String LONG_BREAK_TIME = "com.thomasstep.pomodoronoise.LONG_BREAK_TIME";
    public static final String LIMIT_ROUNDS = "com.thomasstep.pomodoronoise.LIMIT_ROUNDS";
    public static final String DESIRED_ROUNDS = "com.thomasstep.pomodoronoise.DESIRED_ROUNDS";

    protected String selectedNoise = "Brown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_noise);
        RadioButton brownNoiseRadioButton = ((RadioButton) this.findViewById(R.id.brownNoise));
        brownNoiseRadioButton.setChecked(true);

        EditText limitRoundsInput = ((EditText) this.findViewById(R.id.limitRoundsInput));
        Switch limitRoundsSwitch = ((Switch) this.findViewById(R.id.limitRoundsSwitch));
        limitRoundsSwitch.setChecked(true);
        limitRoundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    limitRoundsInput.setVisibility(View.VISIBLE);
                } else {
                    limitRoundsInput.setVisibility(View.GONE);
                }
            }
        });
    }

    /** Called when the user clicks a new radio button for sounds */
    public void onNoiseRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.pinkNoise:
                if (checked)
                    selectedNoise = "Pink";
                    break;
            case R.id.brownNoise:
                if (checked)
                    selectedNoise = "Brown";
                    break;
            case R.id.whiteNoise:
                if (checked)
                    selectedNoise = "White";
                    break;
        }
    }

    /** Called when the user taps the Start button */
    public void startTimer(View view) {
        Intent intent = new Intent(this, TimerActivity.class);

        // Gather inputs
        EditText focusTimeInput = ((EditText) this.findViewById(R.id.focusTimeInput));
        EditText breakTimeInput = ((EditText) this.findViewById(R.id.breakTimeInput));
        EditText longBreakTimeInput = ((EditText) this.findViewById(R.id.longBreakTimeInput));
        Switch limitRoundsSwitch = ((Switch) this.findViewById(R.id.limitRoundsSwitch));
        EditText desiredRoundsInput = ((EditText) this.findViewById(R.id.limitRoundsInput));

        // Convert inputs to integers
        Integer focusTime = Integer.parseInt(focusTimeInput.getText().toString());
        Integer breakTime = Integer.parseInt(breakTimeInput.getText().toString());
        Integer longBreakTime = Integer.parseInt(longBreakTimeInput.getText().toString());
        Boolean limitRounds = limitRoundsSwitch.isChecked();
        Integer desiredRounds = Integer.parseInt(desiredRoundsInput.getText().toString());

        // Put values to time activity
        intent.putExtra(SELECTED_NOISE, selectedNoise);
        intent.putExtra(FOCUS_TIME, focusTime);
        intent.putExtra(BREAK_TIME, breakTime);
        intent.putExtra(LONG_BREAK_TIME, longBreakTime);
        intent.putExtra(LIMIT_ROUNDS, limitRounds);
        intent.putExtra(DESIRED_ROUNDS, desiredRounds);

        // Start activity
        startActivity(intent);
    }

}