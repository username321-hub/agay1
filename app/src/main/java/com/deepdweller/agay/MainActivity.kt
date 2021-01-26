package com.deepdweller.agay

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.TextView
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.lline
import com.deepdweller.agay.data.rline

class
MainActivity : AppCompatActivity() {
    lateinit var add:Button
    lateinit var sbor:Button
    lateinit var counter:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView : MyCanvasView = findViewById(R.id.myView)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        add=findViewById(R.id.add_button)
        sbor = findViewById(R.id.sbor_button)
        counter = findViewById(R.id.textView)
        var counterint = 0
        fun lvlup(gline:FloatArray, lline:FloatArray, rline:FloatArray){
            rline[0]+=0.05f
            gline[1]+=0.05f
            lline[3]+=0.05f
        }
        add.setOnClickListener{
            counterint++
            counter.text = counterint.toString()+"/20"
            if (gline[1]<1.7f){
                lvlup(gline, lline, rline)
                myCanvasView.invalidate()
            }else{
                add.visibility = INVISIBLE
                rline[0]=0f
                gline[1]=1.6f
                lline[3]=1.6f
                myCanvasView.invalidate()
            }
            sbor.setOnClickListener {
                counterint=0
                counter.text = "0/20"
                rline[0]=0.8f
                gline[1]=0.8f
                lline[3]=0.8f
                add.visibility = VISIBLE
                myCanvasView.invalidate()
            }
        }
    }
}