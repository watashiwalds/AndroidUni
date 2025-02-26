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

import com.example.firstlesson.data.SinhVien;

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
        SinhVien sv = (SinhVien) parent.getSerializableExtra("sinhvien");
        assert sv != null;
        txShowMsg.setText("Ho ten: " + sv.getHoten() + ", nam sinh: " + sv.getNamsinh().toString());

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent();
                setResult(RESULT_OK, it);
                finish();
//                Intent it = new Intent(RecieveIntent.this, IntentPipe.class);
//                startActivity(it);
            }
        });
    }
}