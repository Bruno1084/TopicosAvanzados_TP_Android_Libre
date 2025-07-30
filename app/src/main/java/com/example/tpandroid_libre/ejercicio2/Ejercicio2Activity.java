package com.example.tpandroid_libre.ejercicio2;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.tpandroid_libre.R;

public class Ejercicio2Activity extends AppCompatActivity {
    EditText num1, num2;
    Spinner operationOption;
    Button btnCalculate;
    TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ejercicio2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        operationOption = findViewById(R.id.operationOption);
        btnCalculate = findViewById(R.id.btnCalculate);
        txtResult = findViewById(R.id.result);

        String[] operations = {"Suma", "Resta", "Multiplicación", "División", "Módulo"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, operations);
        operationOption.setAdapter(adapter);

        btnCalculate.setOnClickListener(v -> calculateResult());
    }

    private void calculateResult() {
        String num1Str = num1.getText().toString().trim();
        String num2Str = num2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            Toast.makeText(this, "Please enter 2 numbers to make an operation.", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            String operation = operationOption.getSelectedItem().toString();
            double result = 0.0;
            boolean valid = true;

            switch (operation) {
                case "Suma":
                    result = num1 + num2;
                    break;
                case "Resta":
                    result = num1 - num2;
                    break;
                case "Multiplicación":
                    result = num1 * num2;
                    break;
                case "División":
                    if (num2 == 0) {
                        Toast.makeText(this, "Error: Divided by 0", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else {
                        result = num1 / num2;
                    }
                    break;
                case "Módulo":
                    if (num2 == 0) {
                        Toast.makeText(this, "Error: Module by 0.", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else {
                        result = num1 % num2;
                    }
                    break;
            }

            if (valid) {
                txtResult.setText("Result: " + result);
            } else {
                txtResult.setText("Result: Error");
            }

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid. Enter two numbers", Toast.LENGTH_SHORT).show();
        }
    }
}