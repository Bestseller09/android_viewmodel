package com.amine.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.amine.viewmodel.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding: ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        var viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        mainBinding.textView?.let {
            it.text = viewModel.number.toString()
        }

            mainBinding.button.setOnClickListener{
                viewModel.addNumber()
                mainBinding.textView.text = viewModel.number.toString()
            }



        //Observing mutable data objects
        viewModel.seconds().observe(this, Observer{
            mainBinding.numberTxt.text = it.toString()
        })
        viewModel.finished().observe(this, Observer {
            if (it){
                Toast.makeText(this, "Finished!", Toast.LENGTH_SHORT).show()
            }
        })

        mainBinding.startBtn.setOnClickListener {
            if(mainBinding.numberInput.text.isEmpty() || mainBinding.numberInput.text.length < 4) {
                Toast.makeText(this, "Invalid Number!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel._timerValue.value = mainBinding.numberInput.text.toString().toLong()
                viewModel.startTimer()
            }
        }

        mainBinding.stopBtn.setOnClickListener {
            mainBinding.numberTxt.text = "0"
            viewModel.stopTimer()
        }



    }
}