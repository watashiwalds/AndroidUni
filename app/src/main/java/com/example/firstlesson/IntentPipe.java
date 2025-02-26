package com.example.firstlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstlesson.data.SinhVien;

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

        EditText etHoten = findViewById(R.id.etHoten);
        EditText etNamsinh = findViewById(R.id.etNamsinh);

        Button btnSend = findViewById(R.id.btnSend);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ht = etHoten.getText().toString();
                Integer ns = Integer.parseInt(etNamsinh.getText().toString());
                SinhVien sv = new SinhVien(ht, ns);
                Intent it = new Intent(IntentPipe.this, RecieveIntent.class);
                it.putExtra("sinhvien", sv);
                startActivity(it);
            }
        });
    }
}