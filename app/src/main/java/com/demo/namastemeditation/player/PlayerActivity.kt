package com.demo.namastemeditation.player

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.PowerManager
import android.view.View.OnTouchListener
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.demo.namastemeditation.R
import java.util.Locale
import java.util.Timer
import java.util.TimerTask


class PlayerActivity : AppCompatActivity() {
    private val url: String? by lazy {
        intent.getStringExtra(
            KEY_URL
        )
    }

    private val soundUrl: String? by lazy {
        intent.getStringExtra(
            KEY_SOUND_URL
        )
    }
    private val title: String? by lazy {
        intent.getStringExtra(
            KEY_TITLE
        )
    }

    private val description: String? by lazy {
        intent.getStringExtra(
            KEY_DESCRIPTION
        )
    }

    private var mMediaPlayer: MediaPlayer? = null

    private var isPlayEnabled = true
    private lateinit var tvSpent: TextView
    private lateinit var tvRemaining: TextView
    private lateinit var seekbar: SeekBar
    private var totalDuration =0
    private var spentDuration =0
    private lateinit var timer: Timer

    companion object {
        const val KEY_URL = "_url"
        const val KEY_SOUND_URL = "_sound_url"
        const val KEY_TITLE = "_title"
        const val KEY_DESCRIPTION = "_description"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        val btnBack = findViewById<ImageView>(R.id.imageArrowLeft)
        val image = findViewById<ImageView>(R.id.imageView)
        val tvTitle = findViewById<TextView>(R.id.tvTitle)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val ivPlay = findViewById<ImageView>(R.id.ivPause)
        tvSpent = findViewById<TextView>(R.id.tvSpent)
        tvRemaining = findViewById<TextView>(R.id.tvRemaining)
        seekbar = findViewById<SeekBar>(R.id.seekBar)
        seekbar.setOnTouchListener { _, _ -> true }
        timer=Timer()

        Handler(Looper.getMainLooper()).postDelayed({
            playSound()
        }, 3000)

        btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        ivPlay.setOnClickListener {
            isPlayEnabled = if (!isPlayEnabled) {
                ivPlay.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.img_pause))
                playSound()
                true
            } else {
                ivPlay.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.img_play))
                pauseSound()
                false
            }
        }

        Glide.with(this)
            .load(
                url
            )
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.logo).into(image)
        tvTitle.text = title
        tvDescription.text = description
    }

    private fun playSound() {
        if (mMediaPlayer == null) {
            if (soundUrl?.isNotEmpty() == true) {
                mMediaPlayer = when (soundUrl) {
                    "sound1" -> {
                        MediaPlayer.create(this, R.raw.sound1)
                    }
                    "sound2" -> {
                        MediaPlayer.create(this, R.raw.sound2)
                    }
                    "sound3" -> {
                        MediaPlayer.create(this, R.raw.sound3)
                    }
                    else -> {
                        MediaPlayer.create(this, R.raw.sound4)
                    }
                }
                totalDuration = mMediaPlayer?.duration ?:0
                tvRemaining.text= formatTime(totalDuration)
                seekbar.max=totalDuration
                timer.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                            if (mMediaPlayer != null && mMediaPlayer?.isPlaying ==true) {
                                tvSpent.post { tvSpent.text =
                                    formatTime(mMediaPlayer?.currentPosition ?:0) }
                                seekbar.progress=mMediaPlayer?.currentPosition ?:0
                            } else {
                                timer.cancel()
                                timer.purge()
                            }
                        }
                    }
                }, 0, 1000)

                mMediaPlayer?.start()

            } else {
                val myUri: Uri =
                    Uri.parse("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3")
                mMediaPlayer = MediaPlayer().apply {
                    setWakeMode(applicationContext, PowerManager.PARTIAL_WAKE_LOCK)
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    setDataSource(applicationContext, myUri)
                    prepare()
                    totalDuration = mMediaPlayer?.duration ?:0
                    tvRemaining.text= formatTime(totalDuration)
                    seekbar.max=totalDuration
                    timer.scheduleAtFixedRate(object : TimerTask() {
                        override fun run() {
                            runOnUiThread {
                                if (mMediaPlayer != null && mMediaPlayer?.isPlaying ==true) {
                                    tvSpent.post { tvSpent.text =
                                        formatTime(mMediaPlayer?.currentPosition ?:0) }
                                    seekbar.progress=mMediaPlayer?.currentPosition ?:0
                                } else {
                                    timer.cancel()
                                    timer.purge()
                                }
                            }
                        }
                    }, 0, 1000)
                    start()
                }
            }

        } else {
            timer=Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    runOnUiThread {
                        if (mMediaPlayer != null && mMediaPlayer?.isPlaying ==true) {
                            tvSpent.post { tvSpent.text =
                                formatTime(mMediaPlayer?.currentPosition ?:0) }
                            seekbar.progress=mMediaPlayer?.currentPosition ?:0
                        } else {
                            timer.cancel()
                            timer.purge()
                        }
                    }
                }
            }, 0, 1000)
            mMediaPlayer?.start()
        }
    }

    private fun pauseSound() {
        if (mMediaPlayer?.isPlaying == true) mMediaPlayer?.pause()
        Toast.makeText(this, "Audio paused", Toast.LENGTH_SHORT).show();
    }

    fun stopSound() {
        if (mMediaPlayer != null) {
            mMediaPlayer?.stop()
            mMediaPlayer?.release()
            mMediaPlayer = null
        }
    }


    override fun onStop() {
        super.onStop()
        if (mMediaPlayer != null) {
            mMediaPlayer?.release()
            mMediaPlayer = null
            timer.cancel()
            timer.purge()
        }
    }

    private fun formatTime(millis: Int): String {
        val allSeconds = millis / 1000
        val allMinutes: Int
        val seconds: Byte
        val minutes: Byte
        val hours: Byte
        return if (allSeconds >= 60) {
            allMinutes = (allSeconds / 60).toInt()
            seconds = (allSeconds % 60).toByte()
            if (allMinutes >= 60) {
                hours = (allMinutes / 60).toByte()
                minutes = (allMinutes % 60).toByte()
                String.format(Locale.US, "%d:%d:" + formatSeconds(seconds), hours, minutes, seconds)
            } else String.format(Locale.US, "%d:" + formatSeconds(seconds), allMinutes, seconds)
        } else String.format(Locale.US, "0:" + formatSeconds(allSeconds.toByte()), allSeconds)
    }

    private fun formatSeconds(seconds: Byte): String {
        return if (seconds < 10) "0%d" else "%d"
    }

}