package com.deepdweller.agay

import android.content.Intent
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.lline
import com.deepdweller.agay.data.rline
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class
MainActivity : AppCompatActivity() {
    lateinit var buttonPlant: Button
    lateinit var buttonSbor: Button
    val animals = arrayOf("рожь", "овёс", "пшеница")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvasView: ImageView = findViewById(R.id.myView)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        buttonPlant = findViewById(R.id.add_button)
        buttonSbor = findViewById(R.id.sbor_button)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выберите растение")
        var checkedItem = 1 // cow
        builder.setSingleChoiceItems(animals, checkedItem) { dialog, which ->
            checkedItem = which
        }
        builder.setPositiveButton("OK") { dialog, which ->
            Toast.makeText(applicationContext, checkedItem.toString(), Toast.LENGTH_LONG).show()
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
                delay(1000)
                var cmData: FloatArray = floatArrayOf(
                    data.rline[0], data.rline[1], data.rline[2], data.rline[3], 0f,
                    data.gline[0], data.gline[1], data.gline[2], data.gline[3], 0f,
                    data.bline[0], data.bline[1], data.bline[2], data.bline[3], 0f,
                    data.lline[0], data.lline[1], data.lline[2], data.lline[3], 0f
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