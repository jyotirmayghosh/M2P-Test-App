package com.jyotirmayg.testapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.navigation.fragment.NavHostFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        findViewById<LinearLayoutCompat>(R.id.imgContact).setOnClickListener {
            navController.navigate(R.id.action_dashboardFragment_to_contactFragment)
        }
        findViewById<LinearLayoutCompat>(R.id.imgHome).setOnClickListener {
            navController.navigate(R.id.action_contactFragment_to_dashboardFragment)
        }
    }
}