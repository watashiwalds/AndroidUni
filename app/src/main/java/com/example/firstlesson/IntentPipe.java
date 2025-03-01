package com.example.firstlesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
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

    ActivityResultLauncher<Intent> arl =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult o) {
                            if (o.getResultCode() == 33) {
                                Intent it = o.getData();
                                int t = it.getIntExtra("tong", 0);
                                Toast.makeText(IntentPipe.this, String.valueOf(t), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

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
        it.putExtra("so1", s1);
        it.putExtra("so2", s2);
        arl.launch(it);
    }
}