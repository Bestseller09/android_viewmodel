package com.amine.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){

    var number = 0
    private lateinit var timer: CountDownTimer

    fun addNumber() {
        number++
    }

//    val _seconds: MutableLiveData<Int>()
//            get() = _seconds

    private val _seconds = MutableLiveData<Int>()
    fun seconds(): LiveData<Int>{
        return _seconds
    }

    private val _finished = MutableLiveData<Boolean>()
    fun finished(): LiveData<Boolean>{
        return _finished
    }

    var _timerValue = MutableLiveData<Long>()



    fun startTimer(){
        timer = object : CountDownTimer(_timerValue.value!!.toLong(), 1000) {
            override fun onTick(p0: Long) {
                val timeleft = p0/1000
                _seconds.value  = timeleft.toInt()
            }

            override fun onFinish() {
                _finished.value = true
            }

        }.start()
    }

    fun stopTimer(){

        timer.cancel()
    }



    override fun onCleared() {
        super.onCleared()
    }


}