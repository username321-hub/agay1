package com.deepdweller.agay

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
import com.deepdweller.agay.History.plantHistoryString
import com.deepdweller.agay.Data.counter
import com.deepdweller.agay.Data.cultures
import com.deepdweller.agay.Data.gline
import com.deepdweller.agay.Data.lline
import com.deepdweller.agay.Data.plants
import com.deepdweller.agay.Data.rline
import com.deepdweller.agay.Data.score
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.w3c.dom.Text

/*object CheckGood {
    private val positiveMap = mutableMapOf<Culture, List<Culture>>(
        data.cultures[0] to listOf(data.cultures[1], data.cultures[2]),
        data.cultures[1] to listOf(data.cultures[0], data.cultures[2]),
        data.cultures[2] to listOf(data.cultures[1], data.cultures[0])
    )

    fun c(prev : Culture, next : Culture) : Rate{
        val isGoodNext = positiveMap[prev]?.contains(next) ?: false
        if (isGoodNext)
            return  Rate.GOOD
        else
            return  Rate.BAD
    }
}*/

class MainActivity : AppCompatActivity() {

    val plantMaster = PlantMaster()

    val buttonPlant:Button by lazy{findViewById(R.id.add_button) }
    val buttonSbor:Button by lazy{findViewById(R.id.sbor_button)}
    val year:TextView by lazy{findViewById(R.id.textView)}
    val history:TextView by lazy{findViewById(R.id.history)}
    val scoreTextView:TextView by lazy{findViewById(R.id.score)}

    fun toCulture(checkedItem:Int):Culture{
        return Data.cultures[checkedItem]
    }
    fun toStringCulture(checkedItem: Int):String{
        return plants[checkedItem]
    }
    fun scoreAdd(){
        for (i in 0..plantHistory.size-2){
            score.add(plantMaster.howIsGoodChoice(plantHistory[i], plantHistory[i+1]))
        }
    }

/*    fun rateToInt(rate: MutableList<Rate>):Int{
        for (i in 0..5){
            if (rate[i]==Rate.BAD){}
            if (rate[i]==Rate.GOOD){}
        }
    }*/

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
            Toast.makeText(applicationContext, checkedItem.toString(), Toast.LENGTH_LONG).show()
            plantHistoryString.add(toStringCulture(checkedItem))
            counter++
            if (counter==6){
                plantHistoryString.clear()
                scoreTextView.visibility = VISIBLE
/*            scoreTextView.text = rateToInt(score)*/
                history.text = plantHistoryString.toString()
                scoreAdd()
                for (i in score){
                    history.text = history.text.toString() + i.toString()
                }
            }
            else{
                history.text = plantHistoryString.toString()
            }
            year.text = counter.toString() + "/6"

            plantHistory.add(cultures[checkedItem])
        }
        builder.setNegativeButton("Cancel", null)

        buttonPlant.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
        }
        buttonSbor.setOnClickListener {
        }
        MainScope().launch{
            for (i in 1..20) {
                delay(800)
                var cmData: FloatArray = floatArrayOf(
                    Data.rline[0], Data.rline[1], Data.rline[2], Data.rline[3], 0f,
                    Data.gline[0], Data.gline[1], Data.gline[2], Data.gline[3], 0f,
                    Data.bline[0], Data.bline[1], Data.bline[2], Data.bline[3], 0f,
                    Data.lline[0], Data.lline[1], Data.lline[2], Data.lline[3], 0f
                )
                var mColorMatrix = ColorMatrix(cmData)
                var mfilter = ColorMatrixColorFilter(mColorMatrix)
                myCanvasView.setColorFilter(mfilter)
                lvlup()
                myCanvasView.invalidate()
            }
            /*data.rline[1] = 1.5f
            var cmData: FloatArray = floatArrayOf(
                data.rline[0], data.rline[1], data.rline[2], data.rline[3], 0f,
                data.gline[0], data.gline[1], data.gline[2], data.gline[3], 0f,
                data.bline[0], data.bline[1], data.bline[2], data.bline[3], 0f,
                data.lline[0], data.lline[1], data.lline[2], data.lline[3], 0f
            )
            var mColorMatrix = ColorMatrix(cmData)
            var mfilter = ColorMatrixColorFilter(mColorMatrix)
            myCanvasView.setColorFilter(mfilter)
            myCanvasView.invalidate()*/
        }
    }

    fun lvlup() {
        rline[1] += 0.05f
        gline[1] += 0.05f
        lline[3] += 0.05f
    }
}