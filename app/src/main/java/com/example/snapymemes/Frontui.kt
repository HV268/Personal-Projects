package com.example.snapymemes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Frontui : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frontui)
    }

    fun start(view: View) {
        val intent=Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

}