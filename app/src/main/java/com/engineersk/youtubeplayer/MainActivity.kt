package com.engineersk.youtubeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.button.MaterialButton
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mBtnPlaySingle: MaterialButton
    private lateinit var mBtnStandAlone: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mBtnPlaySingle = findViewById(R.id.btnPlaySingle)
        mBtnStandAlone = findViewById(R.id.btnStandAlone)
        mBtnPlaySingle.setOnClickListener(this)
        mBtnStandAlone.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = when(view?.id){
            R.id.btnPlaySingle -> Intent(this, YouTubeActivity::class.java)
            R.id.btnStandAlone -> Intent(this, StandAloneActivity::class.java)
            else -> throw IllegalArgumentException("Button clicked is Undefined!!!")
        }
        startActivity(intent)
    }
}