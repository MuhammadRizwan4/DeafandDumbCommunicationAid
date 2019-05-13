package com.example.muhammadrizwan.deafanddumbcommunicationaid.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.muhammadrizwan.deafanddumbcommunicationaid.R
import com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments.AddItem
import com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments.FrequentItemFragment
import com.example.muhammadrizwan.deafanddumbcommunicationaid.fragments.InterfaceFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.supportFragmentManager.beginTransaction().add(R.id.container, InterfaceFragment()).commit()

        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.interfaces -> {
                    this.supportFragmentManager.beginTransaction().replace(R.id.container, InterfaceFragment()).commit()
                    true
                }
                R.id.Add -> {
                    this.supportFragmentManager.beginTransaction().replace(R.id.container, AddItem()).commit()
                    true

                }
                else-> {
                    this.supportFragmentManager.beginTransaction().replace(R.id.container, FrequentItemFragment()).commit()
                    true
                }

            }


        }
    }
}
