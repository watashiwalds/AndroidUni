package com.example.firstlesson.funcdemo

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.firstlesson.R
import com.example.firstlesson.data.SinhVien

class FoundSinhvien : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_found_sinhvien)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val dssv = listOf(
            SinhVien("Hoang Tran Hung", 2024),
            SinhVien("Nong Ba Lam", 2001),
            SinhVien("Nguyen Trong Ha", 2014),
            SinhVien("Tran Ba Dung", 2011),
            SinhVien("Nguyen Ngoc Trinh", 2009)
        )

        val searchstring = intent.getStringExtra("search")
        val tvSearchResult = findViewById<TextView>(R.id.tvSearchResult)
        val results = dssv.filter { it.name.contains(searchstring ?: "!") }
        var printRes = "Found ${results.size} results." + if (results.isNotEmpty()) " Get first one" else ""
        for (k in results) printRes += "\n${k.name}, ${k.yob}"
        tvSearchResult.setText(printRes)
    }
}