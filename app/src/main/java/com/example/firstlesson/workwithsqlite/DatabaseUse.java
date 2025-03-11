package com.example.firstlesson.workwithsqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstlesson.R;

import kotlin.Pair;

public class DatabaseUse extends AppCompatActivity {

    EditText etID, etName, etYOB;
    Button btnInsert, btnDisplay, btnUpdate, btnDelete, btnFetch;
    SQLiteHelper dbhelper = new SQLiteHelper(this);
    TextView tvDisplay;

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

        btnInsert.setOnClickListener(v -> {
            int k = 0;
            if (!etYOB.getText().toString().isBlank()) k = Integer.parseInt(etYOB.getText().toString());
            long resultInsert = dbhelper.insertToSQL(
                    etName.getText().toString(),
                    k
            );
            if (resultInsert == -1) {
                Toast.makeText(DatabaseUse.this, "ERROR", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(DatabaseUse.this, "Success", Toast.LENGTH_SHORT).show();
            }
        });

        btnDisplay.setOnClickListener(v -> {
            Cursor cr = dbhelper.displayAll();
            StringBuilder nv = new StringBuilder();
            for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()) {
                nv.append(cr.getString(cr.getColumnIndexOrThrow(SQLiteHelper.ID)));
                nv.append("-");
                nv.append(cr.getString(cr.getColumnIndexOrThrow(SQLiteHelper.NAME)));
                nv.append("-");
                nv.append(cr.getString(cr.getColumnIndexOrThrow(SQLiteHelper.YOB)));
                nv.append("\n");
            }
            tvDisplay.setText(nv);
        });

        btnUpdate.setOnClickListener(v -> {
            int id = 0;
            if (!etID.getText().toString().isBlank()) id = Integer.parseInt(etID.getText().toString());
            int yob = 0;
            if (!etYOB.getText().toString().isBlank()) yob = Integer.parseInt(etYOB.getText().toString());
            if (dbhelper.updateToSQL(id, etName.getText().toString(), yob) == 0) {
                Toast.makeText(this, "not updating anything", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "update something", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(v -> {
            int id = -1;
            if (!etID.getText().toString().isBlank()) id = Integer.parseInt(etID.getText().toString());
            if (dbhelper.deleteFromSQL(id) == 0) {
                Toast.makeText(this, "nothing deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "deleted id" + id, Toast.LENGTH_SHORT).show();
            }
        });

        btnFetch.setOnClickListener(v -> {
            int id = -1;
            if (!etID.getText().toString().isBlank()) id = Integer.parseInt(etID.getText().toString());
            Pair<String, Integer> ft = dbhelper.fetchFromID(id);
            if (ft != null) {
                etName.setText(ft.component1());
                etYOB.setText(ft.component2().toString());
            } else {
                Toast.makeText(this, "cant find", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initScreenObj() {
        etID = findViewById(R.id.etIdentification);
        etName = findViewById(R.id.etName);
        etYOB = findViewById(R.id.etYOB);
        btnInsert = findViewById(R.id.btnInsert);
        btnDisplay = findViewById(R.id.btnDisplay);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        tvDisplay = findViewById(R.id.tvListSQL);
        btnFetch = findViewById(R.id.btnFetch);
    }
}