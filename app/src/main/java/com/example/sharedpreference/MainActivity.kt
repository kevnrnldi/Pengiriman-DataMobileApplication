package com.example.sharedpreference

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var textFullName: TextView
    private lateinit var textUmur: TextView
    private lateinit var btnEdit: Button
    private lateinit var btnRemove: Button

    private var sharePref: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharePref =  applicationContext.getSharedPreferences("my-app", Context.MODE_PRIVATE)

         btnEdit = findViewById(R.id.btnEdit)
         textFullName =  findViewById(R.id.full_Name)
         textUmur= findViewById(R.id.umur)
        btnRemove = findViewById(R.id.btnRemove)

        btnEdit.setOnClickListener{
            val intent = Intent(applicationContext, EditorActivity::class.java)
            startActivity(intent)
        }


        btnRemove.setOnClickListener{
            val hapus = sharePref?.edit()
            hapus?.remove("full_Name")
            hapus?.remove("age")
            hapus?.apply()

        onResume()
        }


    }

    override fun onResume() {
        super.onResume()

        val fullName = sharePref?.getString("full_Name","-")
        val umur = sharePref?.getInt("age",0)

        textFullName.text = fullName
        textUmur.text = umur.toString()
    }
}