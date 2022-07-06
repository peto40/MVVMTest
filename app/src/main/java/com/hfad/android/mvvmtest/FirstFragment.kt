package com.hfad.android.mvvmtest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hfad.android.mvvmtest.databinding.FragmentFirstBinding
import kotlin.random.Random

class FirstFragment: Fragment() {

    lateinit var binding: FragmentFirstBinding
    val sharedViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentFirstBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
      //  val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.title.text = sharedViewModel.getCounterValue().toString()

        sharedViewModel.counterLiveData.observe(viewLifecycleOwner, Observer {
            binding.title.text = it.toString()
        })

        binding.reset.setOnClickListener {
            sharedViewModel.updateText("Random number is ${Random.nextInt()}")
        }
        binding.increment.setOnClickListener {
            sharedViewModel.incrementCounter()
            //  binding.title.text = viewModel.getCounterValue().toString()
        }

        sharedViewModel.sharedTextLiveData.observe(viewLifecycleOwner, Observer {
            binding.title.text = it
        })

    }
}