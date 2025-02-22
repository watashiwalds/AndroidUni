package com.example.firstlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RecieveIntent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recieve_intent);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent parent = getIntent();
        TextView txShowMsg = findViewById(R.id.txShowMsg);
        TextView txShowNum = findViewById(R.id.txShowNum);
        Integer num1 = parent.getIntExtra("msg", 0);
        Integer num2 = parent.getIntExtra("num", 0);
        txShowMsg.setText("Your number1: " + num1);
        txShowNum.setText("Your number2: " + num2);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                it.putExtra("num1", Integer.valueOf(num1));
                it.putExtra("num2", Integer.valueOf(num2));
                setResult(RESULT_OK, it);
                finish();
//                Intent it = new Intent(RecieveIntent.this, IntentPipe.class);
//                startActivity(it);
            }
        });
    }
}