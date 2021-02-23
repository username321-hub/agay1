package com.deepdweller.agay

import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.deepdweller.agay.Data.counter
import com.deepdweller.agay.Data.cultures
import com.deepdweller.agay.Data.gline
import com.deepdweller.agay.Data.initFilter
import com.deepdweller.agay.Data.lline
import com.deepdweller.agay.Data.plants
import com.deepdweller.agay.Data.rline
import com.deepdweller.agay.Eventik.event
import com.deepdweller.agay.Eventik.instruments
import com.deepdweller.agay.Eventik.instrumentsString
import com.deepdweller.agay.Eventik.solutions
import com.deepdweller.agay.History.plantHistory
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var progress : Int = 0

class MainActivity : AppCompatActivity() {
    val PLANS_COUNT_FOR_FINISH = 4
    val plantMaster = PlantMaster()
    val progressBar : ProgressBar by lazy {findViewById(R.id.progress_bar) }

    internal var titleList: List<String> ?= null
    internal var adapter: ExpandableListAdapter ?= null
    val expandedListView: ExpandableListView by lazy {findViewById(R.id.listview)}

    val buttonPlant: Button by lazy { findViewById(R.id.add_button) }
    val buttonHarvest: Button by lazy { findViewById(R.id.sbor_button) }
    val history: TextView by lazy { findViewById(R.id.history) }
    val show_result: TextView by lazy { findViewById(R.id.show_result_check) }

    fun calculateScore(): MutableList<Int> {
        val score = mutableListOf<Int>()
        for (i in 0..plantHistory.size - 2) {
            score.add(plantMaster.howIsGoodChoice(plantHistory[i], plantHistory[i + 1]))
            Log.i("History", score.toString())
        }
        return score
    }

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val row = ArrayList<String>()
            row.add("Адрес: $")
            row.add("Возраст: $")
            row.add("Телефон: $")

            listData["Полная информация"] = row

            return listData
        }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        history.text = "0/$PLANS_COUNT_FOR_FINISH"

        progressBar.setProgress(progress, true)
        progressBar.max = PLANS_COUNT_FOR_FINISH
        progressBar.progressDrawable.setColorFilter(Color.rgb(0, 191, 50), android.graphics.PorterDuff.Mode.SRC_IN)

        val myCanvasView: ImageView = findViewById(R.id.myView)
        myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Выберите растение")
        var checkedItem = 1
        builder.setSingleChoiceItems(plants, checkedItem) { dialog, which ->
            checkedItem = which
        }

        builder.setPositiveButton("OK") { dialog, which ->
            if (plantMaster.isPlanted == true) {
                Toast.makeText(applicationContext, "Поле уже засажено", Toast.LENGTH_SHORT).show()
            } else {
                plantMaster.isPlanted = true
                plantMaster.isCanHarvest = false
                draw(myCanvasView)
                plantHistory.add(cultures[checkedItem])
                counter++

                Toast.makeText(this, "${plantMaster.howIsGoodChoice(cultures[checkedItem], cultures[checkedItem+1])}", Toast.LENGTH_SHORT).show()

                progressBar.setProgress(counter, true)
                if (counter == PLANS_COUNT_FOR_FINISH) {
                    //dialogEvent(builder, checkedItem)
                    show_result.text = calculateScore().toString()
                    restartGame()
                }
                history.text = (counter).toString() + "/$PLANS_COUNT_FOR_FINISH"
            }
            /*for (i in 0..plantHistory.size - 2){
                if (calculateScore().isNotEmpty()){
                    if (calculateScore()[i] == 1){
                        Toast.makeText(this, "Верно", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "Не верно", Toast.LENGTH_SHORT).show()
                    }
                }
            }*/
        }
        builder.setNegativeButton("Отмена", null)

        buttonPlant.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
        }
        buttonHarvest.setOnClickListener {
            if (plantMaster.isCanHarvest) {

                buttonHarvest.alpha = 0.5F
                buttonHarvest.isClickable = false

                plantMaster.isPlanted = false
                initFilter()
                myCanvasView.invalidate()
            }
        }

        val expandableListView = findViewById<ExpandableListView>(R.id.listview)
        expandedListView.let{
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView.setAdapter(adapter)
            expandableListView.setOnGroupExpandListener { groupPosition ->  }

            expandableListView.setOnGroupCollapseListener { groupPosition ->  }
            expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                false
            }
        }
    }

    private fun restartGame() {
        plantHistory.clear()
        counter = 0
    }

    private fun draw(myCanvasView: ImageView) {
        MainScope().launch {
            for (i in 1..6) {
                buttonHarvest.alpha = 0.5F
                buttonHarvest.isClickable = false
                buttonHarvest.text = "Созревание..."
                delay(500)
                val cmData: FloatArray = floatArrayOf(
                    rline[0], rline[1], rline[2], rline[3], 0f,
                    gline[0], gline[1], gline[2], gline[3], 0f,
                    Data.bline[0], Data.bline[1], Data.bline[2], Data.bline[3], 0f,
                    lline[0], lline[1], lline[2], lline[3], 0f
                )
                val mColorMatrix = ColorMatrix(cmData)
                val mfilter = ColorMatrixColorFilter(mColorMatrix)
                myCanvasView.colorFilter = mfilter
                lvlup()
                myCanvasView.invalidate()
            }
            plantMaster.isCanHarvest = true
            buttonHarvest.alpha = 1F
            buttonHarvest.isClickable = true
            buttonHarvest.text = "Собрать урожай"
        }
    }

    fun toSolution(tools:String):Instrument{
        val _tools = Instrument(tools)
        return _tools
    }
/*    fun fixEvent(event:Event, tool:Instrument){
        var printvar = ""
        for(i in 0..event.solutions.size-1){
            if (tool.name in event.solutions[i].name)
                printvar = "right"
            println(printvar)
            break
        }
        if (printvar!="right"){
            println("wrong")
        }
    }*/
    fun toRate(checkedItem: Int) {
        var rate = "WRONG"
        for (i in 0 until solutions.size) {
            if (instruments[checkedItem].name == solutions[i].name)
                rate = "RIGHT"
                break
        }
        Toast.makeText(applicationContext, rate, Toast.LENGTH_SHORT).show()
    }

    fun lvlup() {
        rline[1] += 0.05f
        gline[1] += 0.05f
        lline[3] += 0.05f
    }

    fun dialogEvent(builder:AlertDialog.Builder, checkedItem1: Int){
        var checkedItem = checkedItem1
        builder.setTitle(event.eventText+" Выбери инструмент!")
        builder.setSingleChoiceItems(instrumentsString, checkedItem) { dialog, which ->
            checkedItem=which
        }
        builder.setPositiveButton("OK") { dialog, which ->
            toRate(checkedItem)
        }
        builder.setNegativeButton("Отмена", null)
        counter=0
    }
}