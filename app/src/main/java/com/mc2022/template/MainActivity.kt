package com.mc2022.template

import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.time.Duration

class MainActivity : AppCompatActivity() {
    companion object {
        var self: MainActivity? = null
        var mediaPlayer : MediaPlayer? = null
        var new_num = 0;
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        self = this

        play_song.setOnClickListener {
            if (mediaPlayer == null) {
                Toast.makeText(this, "Downloading Song Now", Toast.LENGTH_LONG).show()
                download_song.performClick()
            }
            else {
                if (mediaPlayer!!.isPlaying) {
                    mediaPlayer?.pause()
                    play_song.text = "Play"
                }
                else {
                    mediaPlayer?.start()
                    play_song.text = "Pause"
                }
            }
        }

        download_song.setOnClickListener {
            if (mediaPlayer == null) {
                Toast.makeText(this, "Downloading Song Now", Toast.LENGTH_LONG).show()

                val constraint = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val request: WorkRequest = OneTimeWorkRequestBuilder<DownloadMusic>()
                    .setConstraints(constraint)
                    .build()

//                val request2 = PeriodicWorkRequestBuilder<DownloadMusic>(Duration.ofSeconds(10))
//                    .build()

                WorkManager.getInstance(this).enqueue(request)
            }
        }


    }
}