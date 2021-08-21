package com.engineersk.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

private const val YOUTUBE_VIDEO_ID = "Vjhcq3pSsp0"
private const val YOUTUBE_PLAY_LIST = "PLXtTjtWmQhg1SsviTmKkWO5n0a_-T0bnD"
private const val TAG = "YouTubeActivity"

class YouTubeActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private val DIALOG_REQUEST_CODE = 1
    private val playerView by lazy { YouTubePlayerView(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_you_tube)

        val layout = layoutInflater.inflate(R.layout.activity_you_tube, null) as ConstraintLayout
        setContentView(layout)

        playerView.layoutParams = ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT
        )
        layout.addView(playerView)
        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean
    ) {
        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
        Log.d(TAG, "onInitializationSuccess: youtube player is ${youtubePlayer?.javaClass}")
        Toast.makeText(
            this, "Initialized Youtube Player successfully!!!",
            Toast.LENGTH_LONG
        ).show()

        youtubePlayer?.setPlayerStateChangeListener(playerStateChangedListener)
        youtubePlayer?.setPlaybackEventListener(playbackEventListener)
        if (!wasRestored) {
            youtubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        } else {
            youtubePlayer?.play()
        }
    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youtubeInitializationResult: YouTubeInitializationResult?
    ) {

        if (youtubeInitializationResult?.isUserRecoverableError == true) {
            youtubeInitializationResult.getErrorDialog(this, DIALOG_REQUEST_CODE).show()
        } else {
            val errorMessage = "There was an error initializing the youtube player " +
                    "($youtubeInitializationResult)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    private val playbackEventListener = object : YouTubePlayer.PlaybackEventListener {
        override fun onPlaying() {
            Toast.makeText(
                this@YouTubeActivity, "Good video is playing!!!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onPaused() {
            Toast.makeText(
                this@YouTubeActivity, "Video has paused!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onStopped() {
            Toast.makeText(
                this@YouTubeActivity, "Video has stopped!",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onBuffering(p0: Boolean) {

        }

        override fun onSeekTo(p0: Int) {

        }

    }

    private val playerStateChangedListener = object : YouTubePlayer.PlayerStateChangeListener {
        override fun onLoading() {

        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
            Toast.makeText(
                this@YouTubeActivity, "Click Ad now! Make the video creator rich",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onVideoStarted() {
            Toast.makeText(
                this@YouTubeActivity, "Video has started",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onVideoEnded() {
            Toast.makeText(
                this@YouTubeActivity, "Congratulations! You have completed another video",
                Toast.LENGTH_SHORT
            ).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d(TAG, "onActivityResult called result code $resultCode for request code $requestCode")
        if (requestCode == DIALOG_REQUEST_CODE) {
            intent?.toString()?.let { Log.d(TAG, it) }
            Log.d(TAG, intent?.extras.toString())
            playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)
        }
    }
}
