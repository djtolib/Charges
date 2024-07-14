package com.tolib.charges.ui.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tolib.charges.R
import com.tolib.charges.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction().add(R.id.fragmentContainerView, CityFragment()).commitAllowingStateLoss()
    }
}