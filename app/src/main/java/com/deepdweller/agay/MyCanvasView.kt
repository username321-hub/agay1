package com.deepdweller.agay
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import com.deepdweller.agay.data.bline
import com.deepdweller.agay.data.gline
import com.deepdweller.agay.data.lline
import com.deepdweller.agay.data.rline

class MyCanvasView(context: Context, attributeSet: AttributeSet): View(context, attributeSet) {
    private lateinit var extraBitmap: Bitmap
//    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)


    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        extraBitmap = BitmapFactory.decodeResource(resources, R.drawable.rostki)

//        extraCanvas.drawColor(backgroundColor)
//        if (::extraBitmap.isInitialized) extraBitmap.recycle()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var cmData:FloatArray = floatArrayOf(
            rline[0], rline[1], rline[2], rline[3], 0f,
            gline[0], gline[1], gline[2], gline[3], 0f,
            bline[0], bline[1], bline[2], bline[3], 0f,
            lline[0], lline[1], lline[2], lline[3], 0f)
        var mColorMatrix = ColorMatrix(cmData)
        var mfilter = ColorMatrixColorFilter(mColorMatrix)
        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setStyle(Paint.Style.FILL_AND_STROKE)
        paint.setColorFilter(mfilter)
        canvas.save()
        canvas.drawBitmap(extraBitmap, 0f, 0f, paint)
    }
}