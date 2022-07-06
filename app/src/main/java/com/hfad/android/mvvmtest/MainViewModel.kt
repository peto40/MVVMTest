package com.hfad.android.mvvmtest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private var counter:Int = 0

        private val _counterLiveData = MutableLiveData<Int>()
        val counterLiveData: LiveData<Int> = _counterLiveData

    private val _sharedTextLiveData = MutableLiveData<String>()
    val sharedTextLiveData: LiveData<String> = _sharedTextLiveData


    fun updateText(newText: String){
        _sharedTextLiveData.postValue(newText)
    }


    fun incrementCounter(){
        var current = _counterLiveData.value ?: 0

     //   _counterLiveData.value = ++current //     Should be called form Main Thread
     _counterLiveData.postValue(++current)//      The same as .value, can be called form any Thread

    }

    @OptIn(DelicateCoroutinesApi::class)
    fun incrementWithDelay(){
        GlobalScope.launch(Dispatchers.Default) {
            delay(3000)
            incrementCounter()

        }
    }

    fun getCounterValue() = counter

}