package com.example.firstlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntentPipe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intent_pipe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(IntentPipe.this, RecieveIntent.class);
                EditText etMsg = findViewById(R.id.etMessage);
                EditText etNum = findViewById(R.id.etNumber);
                it.putExtra("msg", Integer.parseInt(etMsg.getText().toString()));
                it.putExtra("num", Integer.parseInt(etNum.getText().toString()));
                startActivityForResult(it, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            TextView tvResult = findViewById(R.id.tvResult);
            Integer result = data.getIntExtra("num1",0) + data.getIntExtra("num2", 0);
            tvResult.setText(result);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}