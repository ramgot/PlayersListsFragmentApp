package com.bignerdranch.android.playerslistsfragmentapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.launch
import androidx.recyclerview.widget.LinearLayoutManager
import com.bignerdranch.android.playerslistsfragmentapp.databinding.ActivityMainBinding

interface DataProvider {
    var result: Player?
}

class MainActivity : AppCompatActivity(), DataProvider {

    override var result: Player? = null

    private lateinit var binding: ActivityMainBinding
    private lateinit var playerDataSource: PlayerDataSource
    private lateinit var adapter: PlayersAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
    }
}