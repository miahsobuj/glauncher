package com.glauncher.launcher.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Fragment for the Calculator tool
 * Provides basic arithmetic operations
 */
public class CalculatorFragment extends BaseToolFragment {

    private TextView display;
    private String currentInput = "0";
    private String operator = "";
    private double firstOperand = 0;
    private boolean isNewInput = true;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_calculator;
    }

    @Override
    protected void initializeUI(View view) {
        display = view.findViewById(R.id.display);

        // Number buttons
        view.findViewById(R.id.btn_0).setOnClickListener(v -> appendNumber("0"));
        view.findViewById(R.id.btn_1).setOnClickListener(v -> appendNumber("1"));
        view.findViewById(R.id.btn_2).setOnClickListener(v -> appendNumber("2"));
        view.findViewById(R.id.btn_3).setOnClickListener(v -> appendNumber("3"));
        view.findViewById(R.id.btn_4).setOnClickListener(v -> appendNumber("4"));
        view.findViewById(R.id.btn_5).setOnClickListener(v -> appendNumber("5"));
        view.findViewById(R.id.btn_6).setOnClickListener(v -> appendNumber("6"));
        view.findViewById(R.id.btn_7).setOnClickListener(v -> appendNumber("7"));
        view.findViewById(R.id.btn_8).setOnClickListener(v -> appendNumber("8"));
        view.findViewById(R.id.btn_9).setOnClickListener(v -> appendNumber("9"));
        view.findViewById(R.id.btn_decimal).setOnClickListener(v -> appendDecimal());

        // Operation buttons
        view.findViewById(R.id.btn_add).setOnClickListener(v -> setOperator("+"));
        view.findViewById(R.id.btn_subtract).setOnClickListener(v -> setOperator("-"));
        view.findViewById(R.id.btn_multiply).setOnClickListener(v -> setOperator("*"));
        view.findViewById(R.id.btn_divide).setOnClickListener(v -> setOperator("/"));
        view.findViewById(R.id.btn_percent).setOnClickListener(v -> calculatePercentage());

        // Control buttons
        view.findViewById(R.id.btn_clear).setOnClickListener(v -> clear());
        view.findViewById(R.id.btn_backspace).setOnClickListener(v -> backspace());
        view.findViewById(R.id.btn_equals).setOnClickListener(v -> calculateResult());
    }

    private void appendNumber(String number) {
        if (isNewInput || currentInput.equals("0")) {
            currentInput = number;
            isNewInput = false;
        } else {
            currentInput += number;
        }
        display.setText(currentInput);
    }

    private void appendDecimal() {
        if (isNewInput) {
            currentInput = "0.";
            isNewInput = false;
        } else if (!currentInput.contains(".")) {
            currentInput += ".";
        }
        display.setText(currentInput);
    }

    private void setOperator(String op) {
        if (!isNewInput) {
            calculateResult();
        }
        operator = op;
        try {
            firstOperand = Double.parseDouble(currentInput);
        } catch (NumberFormatException e) {
            firstOperand = 0;
        }
        isNewInput = true;
    }

    private void calculateResult() {
        if (isNewInput || operator.isEmpty()) {
            return;
        }

        try {
            double secondOperand = Double.parseDouble(currentInput);
            double result = 0;

            switch (operator) {
                case "+":
                    result = firstOperand + secondOperand;
                    break;
                case "-":
                    result = firstOperand - secondOperand;
                    break;
                case "*":
                    result = firstOperand * secondOperand;
                    break;
                case "/":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        display.setText("Error");
                        return;
                    }
                    break;
            }

            // Format result to remove unnecessary decimal places
            if (result == Math.floor(result)) {
                currentInput = String.valueOf((long) result);
            } else {
                currentInput = String.valueOf(result);
            }

            display.setText(currentInput);
            operator = "";
            isNewInput = true;
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void calculatePercentage() {
        try {
            double value = Double.parseDouble(currentInput);
            value = value / 100;
            if (value == Math.floor(value)) {
                currentInput = String.valueOf((long) value);
            } else {
                currentInput = String.valueOf(value);
            }
            display.setText(currentInput);
        } catch (NumberFormatException e) {
            display.setText("Error");
        }
    }

    private void clear() {
        currentInput = "0";
        operator = "";
        firstOperand = 0;
        isNewInput = true;
        display.setText(currentInput);
    }

    private void backspace() {
        if (isNewInput || currentInput.length() <= 1) {
            currentInput = "0";
            isNewInput = true;
        } else {
            currentInput = currentInput.substring(0, currentInput.length() - 1);
        }
        display.setText(currentInput);
    }
}
