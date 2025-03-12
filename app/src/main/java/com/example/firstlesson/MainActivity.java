package com.example.firstlesson;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
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

        Intent it =  new Intent(this, ReadContentProvider.class);
        startActivity(it);

        Button btnStartLogin = findViewById(R.id.btnStartLogin);
        btnStartLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOpen_Login();
            }
        });
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

    private void dialogOpen_Login() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_loginform);
        EditText username = dialog.findViewById(R.id.login_etUsername);
        EditText password = dialog.findViewById(R.id.login_etPassword);
        Button submit = dialog.findViewById(R.id.login_btnSubmit);
        Button cancel = dialog.findViewById(R.id.login_btnCancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("admin")) {
                    Toast.makeText(MainActivity.this, "Login successed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Login failed. admin | admin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialog.show();
    }

    public void openBMI(View v) {
        Intent it = new Intent(this, BMICalculator.class);
        startActivity(it);
    }

    public void openListView(View v) {
        Intent it = new Intent(this, ListViewDemo.class);
        startActivity(it);
    }

    public void openGridView(View v) {
        Intent it = new Intent(this, GridViewTest.class);
        startActivity(it);
    }

    public void openCustomListView(View v) {
        Intent it = new Intent(this, CustomListView.class);
        startActivity(it);
    }
}