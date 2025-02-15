package com.example.firstlesson;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Show the Action Bar (if hidden)
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    public void openBMI(View v) {
        Intent it = new Intent(this, BMICalculator.class);
        startActivity(it);
    }

    public void openListView(View v) {
        Intent it = new Intent(this, ListViewDemo.class);
        startActivity(it);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        TextView changethis = findViewById(R.id.tvChangeColor);
        if (item.getItemId() == R.id.makeRed) {
            changethis.setBackgroundColor(Color.RED);
        }
        if (item.getItemId() == R.id.makeGreen) {
            changethis.setBackgroundColor(Color.GREEN);
        }
        if (item.getItemId() == R.id.makeBlue) {
            changethis.setBackgroundColor(Color.BLUE);
        }
        return super.onOptionsItemSelected(item);
    }
}