package com.cinemotion

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.android.material.tabs.TabLayout

class EditorActivity : AppCompatActivity() {
    
    private lateinit var playerView: PlayerView
    private lateinit var player: ExoPlayer
    private lateinit var btnPlayPause: ImageButton
    private lateinit var tvAddVideo: TextView
    private lateinit var tvTime: TextView
    private lateinit var tabLayout: TabLayout
    
    // Tool panels
    private lateinit var panelCut: LinearLayout
    private lateinit var panelText: LinearLayout
    private lateinit var panelEffects: LinearLayout
    private lateinit var panelColor: LinearLayout
    private lateinit var panelAudio: LinearLayout
    
    private var isPlaying = false
    private var currentVideoUri: Uri? = null
    
    // Video picker
    private val pickVideo = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        uri?.let {
            currentVideoUri = it
            loadVideo(it)
        }
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        
        initViews()
        setupPlayer()
        setupListeners()
        setupTabs()
    }
    
    private fun initViews() {
        playerView = findViewById(R.id.playerView)
        btnPlayPause = findViewById(R.id.btnPlayPause)
        tvAddVideo = findViewById(R.id.tvAddVideo)
        tvTime = findViewById(R.id.tvTime)
        tabLayout = findViewById(R.id.tabLayout)
        
        // Panels
        panelCut = findViewById(R.id.panelCut)
        panelText = findViewById(R.id.panelText)
        panelEffects = findViewById(R.id.panelEffects)
        panelColor = findViewById(R.id.panelColor)
        panelAudio = findViewById(R.id.panelAudio)
    }
    
    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView.player = player
        
        // Show add video button initially
        tvAddVideo.visibility = View.VISIBLE
        playerView.visibility = View.GONE
    }
    
    private fun setupListeners() {
        // Add video
        tvAddVideo.setOnClickListener {
            pickVideo.launch("video/*")
        }
        
        // Back button
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
        
        // Export button
        findViewById<ImageButton>(R.id.btnExport).setOnClickListener {
            Toast.makeText(this, "Export feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Playback controls
        btnPlayPause.setOnClickListener {
            togglePlayPause()
        }
        
        findViewById<ImageButton>(R.id.btnRewind).setOnClickListener {
            seekBackward()
        }
        
        findViewById<ImageButton>(R.id.btnForward).setOnClickListener {
            seekForward()
        }
        
        // Cut tools
        findViewById<Button>(R.id.btnSplit).setOnClickListener {
            splitAtPlayhead()
        }
        
        findViewById<Button>(R.id.btnTrim).setOnClickListener {
            Toast.makeText(this, "Trim feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            Toast.makeText(this, "Delete feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        // Text tools
        findViewById<Button>(R.id.btnAddTextConfirm).setOnClickListener {
            addTextLayer()
        }
        
        // Effects
        findViewById<Button>(R.id.btnBlur).setOnClickListener {
            applyEffect("Blur")
        }
        
        findViewById<Button>(R.id.btnGlitch).setOnClickListener {
            applyEffect("Glitch")
        }
        
        findViewById<Button>(R.id.btnChroma).setOnClickListener {
            applyEffect("Chroma Key")
        }
        
        findViewById<Button>(R.id.btnParticles).setOnClickListener {
            applyEffect("Particles")
        }
    }
    
    private fun setupTabs() {
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> showPanel(panelCut)
                    1 -> showPanel(panelText)
                    2 -> showPanel(panelEffects)
                    3 -> showPanel(panelColor)
                    4 -> showPanel(panelAudio)
                }
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    
    private fun showPanel(panel: LinearLayout) {
        panelCut.visibility = View.GONE
        panelText.visibility = View.GONE
        panelEffects.visibility = View.GONE
        panelColor.visibility = View.GONE
        panelAudio.visibility = View.GONE
        
        panel.visibility = View.VISIBLE
    }
    
    private fun loadVideo(uri: Uri) {
        tvAddVideo.visibility = View.GONE
        playerView.visibility = View.VISIBLE
        
        val mediaItem = MediaItem.fromUri(uri)
        player.setMediaItem(mediaItem)
        player.prepare()
        
        updateTimeDisplay()
    }
    
    private fun togglePlayPause() {
        if (currentVideoUri == null) {
            Toast.makeText(this, "Please add a video first", Toast.LENGTH_SHORT).show()
            return
        }
        
        isPlaying = !isPlaying
        if (isPlaying) {
            player.play()
            btnPlayPause.setImageResource(android.R.drawable.ic_media_pause)
        } else {
            player.pause()
            btnPlayPause.setImageResource(android.R.drawable.ic_media_play)
        }
    }
    
    private fun seekBackward() {
        player.seekTo(player.currentPosition - 10000)
        updateTimeDisplay()
    }
    
    private fun seekForward() {
        player.seekTo(player.currentPosition + 10000)
        updateTimeDisplay()
    }
    
    private fun splitAtPlayhead() {
        if (currentVideoUri == null) {
            Toast.makeText(this, "Please add a video first", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "Split at ${formatTime(player.currentPosition)}", Toast.LENGTH_SHORT).show()
    }
    
    private fun addTextLayer() {
        val text = findViewById<EditText>(R.id.etText).text.toString()
        if (text.isEmpty()) {
            Toast.makeText(this, "Please enter text", Toast.LENGTH_SHORT).show()
            return
        }
        Toast.makeText(this, "Added text: $text", Toast.LENGTH_SHORT).show()
    }
    
    private fun applyEffect(effectName: String) {
        Toast.makeText(this, "Applied $effectName effect", Toast.LENGTH_SHORT).show()
    }
    
    private fun updateTimeDisplay() {
        val current = formatTime(player.currentPosition)
        val total = formatTime(player.duration)
        tvTime.text = "$current / $total"
    }
    
    private fun formatTime(ms: Long): String {
        val seconds = (ms / 1000) % 60
        val minutes = (ms / (1000 * 60)) % 60
        val hours = (ms / (1000 * 60 * 60))
        
        return if (hours > 0) {
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        } else {
            String.format("%02d:%02d", minutes, seconds)
        }
    }
    
    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}
