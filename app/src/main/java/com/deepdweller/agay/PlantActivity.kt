package com.deepdweller.agay

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.deepdweller.agay.data.here

class PlantActivity : AppCompatActivity() {
    lateinit var back:Button
    lateinit var intent2:Intent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plant)
        back = findViewById(R.id.button11)
        intent2 = Intent(this, MainActivity::class.java)
        back.setOnClickListener {
            startActivity(intent2)
            here=1
        }
    }
}