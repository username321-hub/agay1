package com.deepdweller.agay

import android.graphics.Color
import android.graphics.Color.GRAY
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.deepdweller.agay.History.plantHistory
import com.deepdweller.agay.Data.counter
import com.deepdweller.agay.Data.cultures
import com.deepdweller.agay.Data.gline
import com.deepdweller.agay.Data.initFilter
import com.deepdweller.agay.Data.lline
import com.deepdweller.agay.Data.plants
import com.deepdweller.agay.Data.rline
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val PLANS_COUNT_FOR_FINISH = 6

    val plantMaster = PlantMaster()

    val buttonPlant: Button by lazy { findViewById(R.id.add_button) }
    val buttonSbor: Button by lazy { findViewById(R.id.sbor_button) }
    val year: TextView by lazy { findViewById(R.id.textView) }
    val history: TextView by lazy { findViewById(R.id.history) }

    fun calculateScore() : MutableList<Int>{
        val score = mutableListOf<Int>()
        for (i in 0..plantHistory.size - 2) {
            score.add(plantMaster.howIsGoodChoice(plantHistory[i], plantHistory[i + 1]))
        }
        return score
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView: ImageView = findViewById(R.id.myView)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN


        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выберите растение")
        var checkedItem = 1
        builder.setSingleChoiceItems(plants, checkedItem) { dialog, which ->
            checkedItem = which
        }

        builder.setPositiveButton("OK") { dialog, which ->
            if (plantMaster.isPlanted==true){
                Toast.makeText(applicationContext, "Поле уже засажено", Toast.LENGTH_SHORT).show()
            }
            else{
                plantMaster.isPlanted = true
                plantMaster.isCanSbor = false
                so(myCanvasView)
                if (counter == PLANS_COUNT_FOR_FINISH)
                {
                    counter=0
                    history.text =  calculateScore().toString()
                }
                plantHistory.add(cultures[checkedItem])
                counter++

                if (counter == PLANS_COUNT_FOR_FINISH)
                {
                    counter=0
                    history.text =  calculateScore().toString()
                }
                year.text = counter.toString() + "/$PLANS_COUNT_FOR_FINISH"
            }
        }
        builder.setNegativeButton("Cancel", null)

        buttonPlant.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
        }
        buttonSbor.setOnClickListener {
            if (plantMaster.isCanSbor) {
                plantMaster.isPlanted = false
                initFilter()
                myCanvasView.invalidate()
            }
        }
        so(myCanvasView)
    }

    private fun so(myCanvasView: ImageView) {
        MainScope().launch {
            for (i in 1..10) {
                delay(800)
                var cmData: FloatArray = floatArrayOf(
                    rline[0], rline[1], rline[2], rline[3], 0f,
                    gline[0], gline[1], gline[2], gline[3], 0f,
                    Data.bline[0], Data.bline[1], Data.bline[2], Data.bline[3], 0f,
                    lline[0], lline[1], lline[2], lline[3], 0f
                )
                var mColorMatrix = ColorMatrix(cmData)
                var mfilter = ColorMatrixColorFilter(mColorMatrix)
                myCanvasView.setColorFilter(mfilter)
                lvlup()
                myCanvasView.invalidate()
            }
            plantMaster.isCanSbor = true
        }
    }

    fun lvlup() {
        rline[1] += 0.05f
        gline[1] += 0.05f
        lline[3] += 0.05f
    }
}