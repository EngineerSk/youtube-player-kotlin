package com.engineersk.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.youtube.player.YouTubeStandalonePlayer

private const val YOUTUBE_VIDEO_ID = "Vjhcq3pSsp0"
private const val YOUTUBE_PLAY_LIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD"


class StandAloneActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mBtnPlayVideo: MaterialButton
    private lateinit var mBtnPlaylist: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stand_alone)
        mBtnPlayVideo = findViewById(R.id.btnPlayVideo)
        mBtnPlaylist = findViewById(R.id.btnPlayList)
        mBtnPlaylist.setOnClickListener(this)
        mBtnPlaylist.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = when (view?.id) {
            R.id.btnPlayVideo -> YouTubeStandalonePlayer.createVideoIntent(
                this,
                getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_VIDEO_ID,
                0,
                true,
                false
            )
            R.id.btnPlayList -> YouTubeStandalonePlayer.createPlaylistIntent(
                this,
                getString(R.string.GOOGLE_API_KEY),
                YOUTUBE_PLAY_LIST,
                0,
                0,
                true,
                true
            )
            else -> throw IllegalArgumentException("Button clicked is undefined!!!")
        }
        startActivity(intent)
    }
}