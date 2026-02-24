package com.cinemotion

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class EditorActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        
        findViewById<Button>(R.id.btnPlay).setOnClickListener {
            // TODO: Play/Pause
        }
        
        findViewById<Button>(R.id.btnCut).setOnClickListener {
            // TODO: Cut
        }
        
        findViewById<Button>(R.id.btnAddText).setOnClickListener {
            // TODO: Add text
        }
    }
}
