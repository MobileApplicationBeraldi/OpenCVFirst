package com.example.opencvfirst

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.activity.ComponentActivity
import org.opencv.android.OpenCVLoader
import org.opencv.android.Utils
import org.opencv.core.Mat
val TAG = "OPENCV"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var msg = ""
        if (OpenCVLoader.initDebug())
            msg = "Loaded OpenCV version: "+ OpenCVLoader.OPENCV_VERSION
        else msg="Fail to load OpenCV"
        //Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
        setContentView(mView(this))
    }

    inner class mView(context: Context): View(context){

        var name = "keys.jpg"
        //var name = "face.jpg"
        val bm =  BitmapFactory.decodeStream(assets.open(name))
        val bmHeigth = bm.height
        val bmWidth = bm.width
        val mScale = Matrix()

        init {

            var m = Mat()
            Utils.bitmapToMat(bm, m)

            with(OpenCV){
                val r = IntArray(2)
                r[0]=100;r[1]=100
                //Toast.makeText(context,
                //    m.channels().toString(),Toast.LENGTH_SHORT).show()
                //BlurFilter(m)
                //m=getChannel(m,0)
                //threshold(m)
                m = changeBackground(m)
                //thresholdColor(m)
                //negative(m)
                //add(m)
                //and(m)
                //AR(m,this@MainActivity)
                //m = equalizer(m)
                //m= contrastStretching(m)
            }

            Utils.matToBitmap(m,bm)
        }

        override fun onDraw(canvas: Canvas) {
            super.onDraw(canvas)
            canvas.drawBitmap(bm, mScale,null)
        }

        override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
            super.onSizeChanged(w, h, oldw, oldh)
            val ax = w.toFloat()/bmWidth
            val ay = h.toFloat()/bmHeigth
            mScale.setScale(ax, ax)

         }

    }
}


