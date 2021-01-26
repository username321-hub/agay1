package com.deepdweller.agay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var add:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myCanvasView = MyCanvasView(this)
        setContentView(myCanvasView)
        add=findViewById(R.id.add_button)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
    }
}