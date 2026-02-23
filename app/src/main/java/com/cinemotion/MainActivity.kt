package com.cinemotion

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cinemotion.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.buttonNewProject.setOnClickListener {
            // Open Editor
            startActivity(Intent(this, EditorActivity::class.java))
        }
        
        binding.buttonOpenProject.setOnClickListener {
            // TODO: Open file picker
            startActivity(Intent(this, EditorActivity::class.java))
        }
    }
}
