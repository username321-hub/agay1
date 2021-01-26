package com.deepdweller.agay
import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import android.graphics.ColorMatrix
import androidx.core.content.res.ResourcesCompat
import java.lang.Thread.sleep

class MyCanvasView(context: Context): View(context) {
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
        var r = 127
        var g = 127
        var b = 127
        var cmData:FloatArray = floatArrayOf(
            0f, 0f, 0f, 0f, 0f,
            1f, 1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f, 0f,
            0f, 1f, 0f, 0f, 0f)
        var mColorMatrix = ColorMatrix(cmData)
        var mfilter = ColorMatrixColorFilter(mColorMatrix)
        mColorMatrix = ColorMatrix(cmData)
        var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.setStyle(Paint.Style.FILL_AND_STROKE)
        paint.setColorFilter(mfilter)
        canvas.save()
        canvas.drawBitmap(extraBitmap, 0f, 0f, paint)
    }
}