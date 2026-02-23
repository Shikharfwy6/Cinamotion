package com.cinemotion

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cinemotion.databinding.ActivityEditorBinding

class EditorActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityEditorBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        // Setup buttons
        binding.btnPlay.setOnClickListener {
            // TODO: Play/Pause video
        }
        
        binding.btnCut.setOnClickListener {
            // TODO: Cut at playhead
        }
        
        binding.btnAddText.setOnClickListener {
            // TODO: Add text layer
        }
    }
}
