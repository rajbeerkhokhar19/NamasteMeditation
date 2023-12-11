package com.demo.namastemeditation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demo.namastemeditation.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.action_home -> {
                    val fragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@setOnItemSelectedListener true
                }

                R.id.action_meditate -> {
                    val fragment = MeditateFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@setOnItemSelectedListener true
                }

                R.id.action_remind_me -> {
                    val fragment = RemindMeFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@setOnItemSelectedListener true
                }


                R.id.action_profile -> {
                    val fragment = ProfileFragment.newInstance()
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, fragment, fragment.javaClass.simpleName)
                        .commit()
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.main_container,
                    HomeFragment.newInstance()
                ).commit()
        }
    }
}