package com.example.pw4;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText display;
    private double operand1 = 0;
    private double operand2 = 0;
    private String currentOperator = "";
    private boolean operatorPressed = false;
    private boolean resultDisplayed = false;
    private double memory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        display = findViewById(R.id.edt1);

        setNumericOnClickListener();
        setOperatorOnClickListener();
        setMemoryOnClickListener();
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String buttonText = button.getText().toString();

            if (operatorPressed || resultDisplayed) {
                display.setText(buttonText);
                operatorPressed = false;
                resultDisplayed = false;
            } else {
                display.append(buttonText);
            }
        };

        findViewById(R.id.zeroBTN).setOnClickListener(listener);
        findViewById(R.id.oneBTN).setOnClickListener(listener);
        findViewById(R.id.twoBTN).setOnClickListener(listener);
        findViewById(R.id.threeBTN).setOnClickListener(listener);
        findViewById(R.id.fourBTN).setOnClickListener(listener);
        findViewById(R.id.fiveBTN).setOnClickListener(listener);
        findViewById(R.id.sixBTN).setOnClickListener(listener);
        findViewById(R.id.sevenBTN).setOnClickListener(listener);
        findViewById(R.id.eightBTN).setOnClickListener(listener);
        findViewById(R.id.nineBTN).setOnClickListener(listener);
        findViewById(R.id.dotBTN).setOnClickListener(listener);
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = v -> {
            Button button = (Button) v;
            String operator = button.getText().toString();

            operand1 = Double.parseDouble(display.getText().toString());
            currentOperator = operator;
            operatorPressed = true;
        };

        findViewById(R.id.addBTN).setOnClickListener(listener);
        findViewById(R.id.substractBTN).setOnClickListener(listener);
        findViewById(R.id.multiplyBTN).setOnClickListener(listener);
        findViewById(R.id.divideBTN).setOnClickListener(listener);

        findViewById(R.id.equalsBTN).setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                operand2 = Double.parseDouble(display.getText().toString());
                double result = 0;

                switch (currentOperator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            display.setText("Error");
                            return;
                        }
                        break;
                }

                display.setText(String.valueOf(result));
                operatorPressed = false;
                resultDisplayed = true;
            }
        });

        findViewById(R.id.cBTN).setOnClickListener(v -> {
            operand1 = 0;
            operand2 = 0;
            currentOperator = "";
            operatorPressed = false;
            resultDisplayed = false;
            display.setText("");
        });
    }

    private void setMemoryOnClickListener() {
        findViewById(R.id.buttonMS).setOnClickListener(v -> {
            String displayText = display.getText().toString();
            if (!displayText.isEmpty()) {
                memory = Double.parseDouble(displayText);
                Toast.makeText(MainActivity.this, "Memory Saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "No value to save", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.buttonMR).setOnClickListener(v -> {
            display.setText(String.valueOf(memory));
            operatorPressed = false;
            resultDisplayed = false;
            Toast.makeText(MainActivity.this, "Memory Read", Toast.LENGTH_SHORT).show();
        });

        findViewById(R.id.buttonMC).setOnClickListener(v -> {
            memory = 0;
            Toast.makeText(MainActivity.this, "Memory Cleared", Toast.LENGTH_SHORT).show();
        });
    }
}