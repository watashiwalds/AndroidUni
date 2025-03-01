package com.example.firstlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstlesson.data.SinhVien;

public class IntentPipe extends AppCompatActivity {
    EditText etSo1;
    EditText etSo2;
    Button btnSend;

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

        etSo1 = findViewById(R.id.etSo1);
        etSo2 = findViewById(R.id.etSo2);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xuly();
            }
        });
    }

    private void xuly() {
        Integer s1 = Integer.parseInt(etSo1.getText().toString());
        Integer s2 = Integer.parseInt(etSo2.getText().toString());
        Intent it = new Intent(this, RecieveIntent.class);
        startActivityForResult(it, 99);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Toast.makeText(this, "help", Toast.LENGTH_LONG);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == 33) {
            int t = data.getIntExtra("tong", 0);
            Toast.makeText(this, Integer.toString(t), Toast.LENGTH_LONG);
        }
    }
}