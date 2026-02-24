package com.cinemotion

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class EditorActivity : AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        
        // Safe findViewById with null checks
        findViewById<Button>(R.id.btnPlay)?.setOnClickListener {
            Toast.makeText(this, "Play clicked", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<Button>(R.id.btnCut)?.setOnClickListener {
            Toast.makeText(this, "Cut clicked", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<Button>(R.id.btnAddText)?.setOnClickListener {
            Toast.makeText(this, "Add Text clicked", Toast.LENGTH_SHORT).show()
        }
    }
}
