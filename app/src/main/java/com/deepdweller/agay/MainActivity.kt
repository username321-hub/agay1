package com.deepdweller.agay

import android.content.Context
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatDrawableManager.get
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.deepdweller.agay.adapter.ProfileExpandableListAdapter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val PLANS_COUNT_FOR_FINISH = 2

    val plantMaster = PlantMaster()
    internal var titleList: List<String> ?= null
    internal var adapter: ExpandableListAdapter ?= null
    val expandedListView:ExpandableListView by lazy {findViewById(R.id.listview)}
    val buttonPlant: Button by lazy { findViewById(R.id.add_button) }
    val buttonHarvest: Button by lazy { findViewById(R.id.sbor_button) }
    val year: TextView by lazy { findViewById(R.id.textView) }
    val history: TextView by lazy { findViewById(R.id.history) }

    fun calculateScore(): MutableList<Int> {
        val score = mutableListOf<Int>()
        for (i in 0..plantHistory.size - 2) {
            score.add(plantMaster.howIsGoodChoice(plantHistory[i], plantHistory[i + 1]))
        }
        plantHistory.clear()
        return score
    }

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val redmiMobiles = ArrayList<String>()
            redmiMobiles.add("Адрес: $")
            redmiMobiles.add("Возраст: $")
            redmiMobiles.add("Телефон: $")

            listData["Полная информация"] = redmiMobiles

            return listData
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
            if (plantMaster.isPlanted == true) {
                Toast.makeText(applicationContext, "Поле уже засажено", Toast.LENGTH_SHORT).show()
            } else {
                plantMaster.isPlanted = true
                plantMaster.isCanHarvest = false
                draw(myCanvasView)
                plantHistory.add(cultures[checkedItem])
                counter++

                if (counter == PLANS_COUNT_FOR_FINISH) {
                    dialogEvent(builder, checkedItem)
                    history.text = calculateScore().toString()
                }
                year.text = counter.toString() + "/$PLANS_COUNT_FOR_FINISH"
            }
        }
        builder.setNegativeButton("Cancel", null)

        buttonPlant.setOnClickListener {
            val dialog = builder.create()
            dialog.show()
        }
        buttonHarvest.setOnClickListener {
            if (plantMaster.isCanHarvest) {
                plantMaster.isPlanted = false
                initFilter()
                myCanvasView.invalidate()
            }
        }

        val expandableListView = findViewById<ExpandableListView>(R.id.listview)
        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ProfileExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView.setAdapter(adapter)
            expandableListView.setOnGroupExpandListener { groupPosition ->  }

            expandableListView.setOnGroupCollapseListener { groupPosition ->  }

            expandableListView.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->
                false
            }
        }
    }

    private fun draw(myCanvasView: ImageView) {
        MainScope().launch {
            for (i in 1..10) {
                delay(500)
                var cmData: FloatArray = floatArrayOf(
                    rline[0], rline[1], rline[2], rline[3], 0f,
                    gline[0], gline[1], gline[2], gline[3], 0f,
                    Data.bline[0], Data.bline[1], Data.bline[2], Data.bline[3], 0f,
                    lline[0], lline[1], lline[2], lline[3], 0f
                )
                var mColorMatrix = ColorMatrix(cmData)
                var mfilter = ColorMatrixColorFilter(mColorMatrix)
                myCanvasView.colorFilter = mfilter
                lvlup()
                myCanvasView.invalidate()
            }
            plantMaster.isCanHarvest = true
        }
    }

    fun toSolution(tool:String):Instrument{
        var tool = Instrument(tool)
        return tool
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
        builder.setNegativeButton("Cancel", null)
        counter=0
    }
}