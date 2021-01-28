package com.deepdweller.agay

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.here
import com.deepdweller.agay.data.lline
import com.deepdweller.agay.data.rline
import java.lang.Thread.sleep

class
MainActivity : AppCompatActivity() {
    lateinit var add:Button
    lateinit var sbor:Button
    lateinit var counter:TextView
    lateinit var rostok1:ImageView
    lateinit var rostok2:ImageView
    lateinit var rostok3:ImageView
    lateinit var rostok4:ImageView
    lateinit var here2:Button
    lateinit var here3:Button
    lateinit var here4:Button
    lateinit var here5:Button
    lateinit var here6:Button
    lateinit var here7:Button
    lateinit var here8:Button
    lateinit var here9:Button
    lateinit var here10:Button
    var intent1: Intent? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView : MyCanvasView = findViewById(R.id.myView)
        here2 = findViewById(R.id.button2)
        here3 = findViewById(R.id.button3)
        here4 = findViewById(R.id.button4)
        here5 = findViewById(R.id.button5)
        here6 = findViewById(R.id.button6)
        here7 = findViewById(R.id.button7)
        here8 = findViewById(R.id.button8)
        here9 = findViewById(R.id.button9)
        here10 = findViewById(R.id.button10)
        if (here==1){
            here=0
            here2.visibility = VISIBLE
            here3.visibility = VISIBLE
            here4.visibility = VISIBLE
            here5.visibility = VISIBLE
            here6.visibility = VISIBLE
            here7.visibility = VISIBLE
            here8.visibility = VISIBLE
            here9.visibility = VISIBLE
            here10.visibility = VISIBLE
        }
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        add=findViewById(R.id.add_button)
        sbor = findViewById(R.id.sbor_button)
        counter = findViewById(R.id.textView)
        here2 = findViewById(R.id.button2)
        here3 = findViewById(R.id.button3)
        here4 = findViewById(R.id.button4)
        here5 = findViewById(R.id.button5)
        here6 = findViewById(R.id.button6)
        here7 = findViewById(R.id.button7)
        here8 = findViewById(R.id.button8)
        here9 = findViewById(R.id.button9)
        here10 = findViewById(R.id.button10)
        intent1 = Intent(this, PlantActivity::class.java)
       /* rostok1 = findViewById(R.id.imageView)
        rostok2 = findViewById(R.id.imageView2)
        rostok3 = findViewById(R.id.imageView3)
        rostok4 = findViewById(R.id.imageView4)*/

        var counterint = 0
        fun lvlup(gline:FloatArray, lline:FloatArray, rline:FloatArray){
            rline[0]+=0.05f
            gline[1]+=0.05f
            lline[3]+=0.05f
        }
        add.setOnClickListener {
            startActivity(intent1)
        }
       /* var count1 = 0
        add.setOnClickListener{
            count1++
            if (count1==5){
                rostok1.visibility = VISIBLE
            }
            if (count1==10){
                rostok2.visibility = VISIBLE
            }
            if (count1==15){
                rostok3.visibility = VISIBLE
            }
            if (count1==20){
                rostok4.visibility = VISIBLE
            }*/
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