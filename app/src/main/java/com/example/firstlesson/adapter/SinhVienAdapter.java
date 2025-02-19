package com.example.firstlesson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firstlesson.R;
import com.example.firstlesson.data.SinhVien;

import java.util.List;

public class SinhVienAdapter extends ArrayAdapter<SinhVien> {
    public SinhVienAdapter(@NonNull Context context, int resource, int textViewResourceId) {
        super(context, resource, textViewResourceId);
    }

    public SinhVienAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_listview_sinhvien, null);
        }
        SinhVien sv = getItem(position);
        if (sv != null) {
            TextView name = v.findViewById(R.id.tvName);
            TextView yob = v.findViewById(R.id.tvYob);
            name.setText(sv.name);
            yob.setText(String.valueOf(sv.yob));
        }
        return v;
    }
}
