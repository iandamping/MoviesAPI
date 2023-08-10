package com.ian.app.muviepedia.ui.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ian.app.muviepedia.databinding.ActivityMainBinding
import com.ian.app.muviepedia.di.activityComponent

/**
 *
Created by Ian Damping on 05/06/2019.
Github = https://github.com/iandamping
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        activityComponent()
        super.onCreate(savedInstanceState)
        window.setBackgroundDrawable(ColorDrawable(Color.BLACK))

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}