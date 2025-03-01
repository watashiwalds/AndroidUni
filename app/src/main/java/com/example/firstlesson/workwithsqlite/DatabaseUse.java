package com.example.firstlesson.workwithsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstlesson.R;

public class DatabaseUse extends AppCompatActivity {

    EditText etID, etName, etYOB;
    Button btnAdd;
    SQLiteHelper dbhelper = new SQLiteHelper(this);

    @Override
    protected void onStart() {
        super.onStart();
        dbhelper.onOpen(dbhelper.getDB());
    }

    @Override
    protected void onStop() {
        super.onStop();
        dbhelper.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_database_use);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initScreenObj();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long resultInsert = dbhelper.insertToSQL(
                        etName.getText().toString(),
                        Integer.parseInt(etYOB.getText().toString())
                );
                if (resultInsert == -1) {
                    Toast.makeText(DatabaseUse.this, "ERROR", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(DatabaseUse.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initScreenObj() {
        etID = findViewById(R.id.etIdentification);
        etName = findViewById(R.id.etName);
        etYOB = findViewById(R.id.etYOB);
        btnAdd = findViewById(R.id.btnAdd);
    }
}