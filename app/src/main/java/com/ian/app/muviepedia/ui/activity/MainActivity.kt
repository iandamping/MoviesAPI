package com.ian.app.muviepedia.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}