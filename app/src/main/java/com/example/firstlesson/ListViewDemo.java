package com.example.firstlesson;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListViewDemo extends AppCompatActivity {

    ArrayList<String> alSomething = new ArrayList<String>();
    ArrayAdapter<String> apSomething;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_view_demo);

//      var initialize
        ListView lvSomething = findViewById(R.id.lvSomething);
        EditText etItemContent = findViewById(R.id.etItemContent);
        Button btnNewItem = findViewById(R.id.btnNewItem);

        final int[] updatingIndex = new int[1];
        Button btnUpdateItem = findViewById(R.id.btnUpdateItem);
        Button btnDeleteItem = findViewById(R.id.btnDeleteItem);

//      ListView
        alSomething.add("Monday");
        alSomething.add("Tuesday");
        alSomething.add("Wednesday");
        alSomething.add("Thursday");
        alSomething.add("Friday");
        alSomething.add("Saturday");
        alSomething.add("Sunday");

        apSomething = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                alSomething
        );
        lvSomething.setAdapter(apSomething);

//      choosing item in the list - click
        lvSomething.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListViewDemo.this, alSomething.get(position), Toast.LENGTH_SHORT).show();
                updatingIndex[0] = position;
                etItemContent.setText(alSomething.get(position));
            }
        });
//      long hold to remove item
        lvSomething.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListViewItem_DeleteConfirm(position);
                return true;
            }
        });

//      add new item
        btnNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newitemContent = etItemContent.getText().toString();
                if (newitemContent.isBlank()) {}
                else if (alSomething.contains(newitemContent)) {
                    Toast.makeText(ListViewDemo.this, "Already appear in ListView", Toast.LENGTH_SHORT).show();
                }
                else {
                    alSomething.add(newitemContent);
                    apSomething.notifyDataSetChanged();
                }
                etItemContent.setText("");
            }
        });

//      update existing item
        btnUpdateItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String updateitemContent = etItemContent.getText().toString();
                if (updateitemContent.isBlank()) {
                    btnDeleteItem.callOnClick();
                }
                else if (updatingIndex[0] != -1) {
                    alSomething.set(updatingIndex[0], updateitemContent);
                    apSomething.notifyDataSetChanged();
                }
                etItemContent.setText("");
                updatingIndex[0] = -1;
            }
        });

//      delete existing item
        btnDeleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updatingIndex[0] != -1) {
                    alSomething.remove(updatingIndex[0]);
                    apSomething.notifyDataSetChanged();
                    etItemContent.setText("");
                    updatingIndex[0] = -1;
                }
            }
        });
    }

    private void ListViewItem_DeleteConfirm(int position) {
        AlertDialog.Builder alertDiag = new AlertDialog.Builder(this);
        alertDiag.setTitle("Warning: Delete item");
        alertDiag.setIcon(R.mipmap.ic_launcher);
        alertDiag.setMessage("This action can't be reverted. Are you sure?");
        alertDiag.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ListViewDemo.this, "Removed" + alSomething.get(position), Toast.LENGTH_SHORT).show();
                alSomething.remove(position);
                apSomething.notifyDataSetChanged();
            }
        });
        alertDiag.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDiag.show();
    }
}