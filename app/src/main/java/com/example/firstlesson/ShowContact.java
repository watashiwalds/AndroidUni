package com.example.firstlesson;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ShowContact extends AppCompatActivity {

    Button btnBack;
    ListView lvContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBack = findViewById(R.id.btnContactBack);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        showContacts();
    }

    private void showContacts() {
        lvContact = findViewById(R.id.lvContact);
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        ArrayList<String> contacts = new ArrayList<>();
        Cursor cr = getContentResolver().query(uri, null, null, null);
        if (cr != null) {
            cr.moveToFirst();

            String s = "";
            while (!cr.isAfterLast()) {
                s = "";

                String colName = ContactsContract.Contacts._ID;
                s += cr.getString(cr.getColumnIndexOrThrow(colName)) + " - ";

                colName = ContactsContract.Contacts.DISPLAY_NAME;
                s += cr.getString(cr.getColumnIndexOrThrow(colName));

                contacts.add(s);
                cr.moveToNext();
            }
        }

        ArrayAdapter<String> adp = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                contacts
        );
        lvContact.setAdapter(adp);
    }
}