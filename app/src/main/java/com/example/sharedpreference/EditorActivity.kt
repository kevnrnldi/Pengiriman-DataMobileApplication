package com.example.sharedpreference

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditorActivity : AppCompatActivity() {
    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_editor)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val inputName: EditText = findViewById(R.id.full_Name)
        val inputAge: EditText = findViewById(R.id.umur)
        val Tombol: Button = findViewById(R.id.btnData)

        val sharePref = applicationContext.getSharedPreferences("my-app", Context.MODE_PRIVATE)
        val editPref = sharePref?.edit()

        //simpan data
        Tombol.setOnClickListener{
            if (inputName.length() > 0 && inputAge.length() > 0) {
                editPref?.putString("full_Name", inputName.text.toString())
                editPref?.putInt("age", inputAge.text.toString().toInt())
                editPref?.apply()


                finish()
            } else {
                Toast.makeText(applicationContext, "Isilah Semua Data", Toast.LENGTH_SHORT).show()
            }
        }

    }
}