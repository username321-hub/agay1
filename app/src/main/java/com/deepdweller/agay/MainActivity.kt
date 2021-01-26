package com.deepdweller.agay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.Button
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.lline

class
MainActivity : AppCompatActivity() {
    lateinit var add:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView : MyCanvasView = findViewById(R.id.myView)

        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        add=findViewById(R.id.add_button)
        fun lvlup(gline:FloatArray, lline:FloatArray){
            gline[1]+=0.05f
            lline[3]+=0.05f
        }
        add.setOnClickListener{
            if (gline[1]<1.9f){
                lvlup(gline, lline)
                myCanvasView.invalidate()
            }
        }
    }
}