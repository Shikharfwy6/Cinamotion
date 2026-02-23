package com.cinemotion.timeline

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

class TimelineView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {
    
    private val trackPaint = Paint().apply {
        color = Color.parseColor("#2D2D2D")
    }
    
    private val playheadPaint = Paint().apply {
        color = Color.parseColor("#FF6B35")
        strokeWidth = 4f
    }
    
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        
        // Draw tracks
        for (i in 0..3) {
            val top = i * 200f
            canvas.drawRect(0f, top, width.toFloat(), top + 180f, trackPaint)
        }
        
        // Draw playhead
        canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), playheadPaint)
    }
}