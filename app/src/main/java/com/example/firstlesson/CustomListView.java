package com.example.firstlesson;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstlesson.adapter.SinhVienAdapter;
import com.example.firstlesson.data.SinhVien;

import java.util.ArrayList;

public class CustomListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvSinhVien = findViewById(R.id.lvSinhVien);

        ArrayList<SinhVien> alSinhviens = new ArrayList<>();
        alSinhviens.add(new SinhVien("Nguyen Van A", 2004));
        alSinhviens.add(new SinhVien("Nguyen Van B", 2005));
        alSinhviens.add(new SinhVien("Nguyen Van C", 2006));
        alSinhviens.add(new SinhVien("Nguyen Van D", 2007));
        alSinhviens.add(new SinhVien("Nguyen Van E", 2008));
        alSinhviens.add(new SinhVien("Nguyen Van F", 2009));
        alSinhviens.add(new SinhVien("Nguyen Van G", 2010));
        alSinhviens.add(new SinhVien("Nguyen Van H", 2011));
        alSinhviens.add(new SinhVien("Nguyen Van I", 2012));
        alSinhviens.add(new SinhVien("Nguyen Van J", 2013));
        alSinhviens.add(new SinhVien("Nguyen Van K", 2014));
        alSinhviens.add(new SinhVien("Nguyen Van L", 2015));
        alSinhviens.add(new SinhVien("Nguyen Van M", 2016));
        alSinhviens.add(new SinhVien("Nguyen Van N", 2017));
        alSinhviens.add(new SinhVien("Nguyen Van O", 2018));
        alSinhviens.add(new SinhVien("Nguyen Van P", 2019));
        alSinhviens.add(new SinhVien("Nguyen Van Q", 2020));

        SinhVienAdapter apSinhvien = new SinhVienAdapter(
                this,
                R.layout.item_listview_sinhvien,
                alSinhviens
        );

        lvSinhVien.setAdapter(apSinhvien);
        apSinhvien.notifyDataSetChanged();
    }
}