package com.hfad.android.mvvmtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.android.mvvmtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.title.text = viewModel.getCounterValue().toString()

        viewModel.counterLiveData.observe(this, Observer {
            binding.title.text = it.toString()
        })

        binding.increment.setOnClickListener {
            viewModel.incrementCounter()
          //  binding.title.text = viewModel.getCounterValue().toString()
        }

        binding.increment.setOnLongClickListener {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, FirstFragment())
                .commit()
            true
        }

        binding.reset.setOnClickListener {
            binding.title.text = ""
        }

        binding.reset.setOnLongClickListener {
        // viewModel.incrementWithDelay()
            supportFragmentManager.beginTransaction()
                .add(R.id.container_1, SecondFragment())
                .commit()

            true
        }
    }
}