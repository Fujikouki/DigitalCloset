package com.example.digitalcloset

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.digitalcloset.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        
    }

}