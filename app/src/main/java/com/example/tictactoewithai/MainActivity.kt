package com.example.tictactoewithai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        humanToHumanId.setOnClickListener {
            val intent = Intent(applicationContext, HumanToHuman::class.java)
            startActivity(intent)
        }
        humanToSystemId.setOnClickListener {

          val intent = Intent(applicationContext,HumanToSystem::class.java)
            startActivity(intent)

        }
    }
}