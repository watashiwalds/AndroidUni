package com.example.firstlesson;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowMessage extends AppCompatActivity {

    ListView lvMessage;
    ArrayList<String> arl;
    ArrayAdapter<String> adp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_message);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvMessage = findViewById(R.id.lvMessage);

        readMessage();
    }

    private void readMessage() {
        Uri uri = Uri.parse("content://sms");
        Cursor cr = getContentResolver().query(uri, null, null, null, null);

        arl = new ArrayList<String>();
        cr.moveToFirst();
        while (!cr.isAfterLast()) {
            String phoneNum = cr.getString(cr.getColumnIndexOrThrow("address"));
            String date = cr.getString(cr.getColumnIndexOrThrow("date"));
            String content = cr.getString(cr.getColumnIndexOrThrow("body"));
            arl.add(phoneNum + "\n" + date + "\n" + content);
            cr.moveToNext();
        }

        adp = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                arl
        );
        lvMessage.setAdapter(adp);
    }
}