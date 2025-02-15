package com.example.firstlesson;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class BMICalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bmicalculator);

        EditText kg = findViewById(R.id.bmi_inpkg);
        EditText m = findViewById(R.id.bmi_inpm);
        Button cal = findViewById(R.id.bmi_btncal);
        TextView bminum = findViewById(R.id.bmi_tvbminum);
        TextView bmitext = findViewById(R.id.bmi_tvbmitext);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!kg.getText().toString().isBlank() && !m.getText().toString().isBlank()) {
                    double dkg = Double.parseDouble(kg.getText().toString());
                    double dm = Double.parseDouble(m.getText().toString());
                    double bmi = dkg / (dm * dm);

                    String bmicat = "";
                    if (0 < bmi && bmi <= 15) {
                        bmicat = "Very severely underweight";
                    } else if (15 < bmi && bmi <= 16) {
                        bmicat = "Severely underweight";
                    } else if (16 < bmi && bmi <= 18.5) {
                        bmicat = "Underweight";
                    } else if (18.5 < bmi && bmi <= 25) {
                        bmicat = "Healthy";
                    } else if (25 < bmi && bmi <= 30) {
                        bmicat = "Overweight";
                    } else if (30 < bmi && bmi <= 35) {
                        bmicat = "Obese I";
                    } else if (35 < bmi && bmi <= 40) {
                        bmicat = "Obese II";
                    } else if (40 < bmi) {
                        bmicat = "Obese III";
                    }

                    bminum.setText(String.valueOf((new DecimalFormat("#.#")).format(bmi)));
                    bmitext.setText(bmicat);
                }
            }
        });
    }




}