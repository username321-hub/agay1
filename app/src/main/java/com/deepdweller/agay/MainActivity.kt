package com.deepdweller.agay

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.lline
import com.deepdweller.agay.data.rline

class
MainActivity : AppCompatActivity() {
    lateinit var add:Button
    lateinit var sbor:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView : MyCanvasView = findViewById(R.id.myView)

        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        add=findViewById(R.id.add_button)
        sbor = findViewById(R.id.sbor_button)
        fun lvlup(gline:FloatArray, lline:FloatArray, rline:FloatArray){
            rline[0]+=0.05f
            gline[1]+=0.05f
            lline[3]+=0.05f
        }
        add.setOnClickListener{
            if (gline[1]<1.4f){
                lvlup(gline, lline, rline)
                myCanvasView.invalidate()
            }else{
                add.visibility = INVISIBLE
                rline[0]=0f
                gline[1]=1.3f
                lline[3]=1.3f
                myCanvasView.invalidate()
            }
            sbor.setOnClickListener {
                rline[0]=0.4f
                gline[1]=0.4f
                lline[3]=0.4f
                add.visibility = VISIBLE
                myCanvasView.invalidate()
            }
        }
    }
}