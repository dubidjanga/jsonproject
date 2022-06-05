package com.example.myapplicationahahah

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationahahah.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewLength: TextView
    private lateinit var viewChain: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewLength = findViewById(R.id.length)
        viewChain = findViewById(R.id.chain)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getChain()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                var myResponse: MyResponse
                Log.d("Response", response.body()?.length.toString())
                viewLength.text = response.body()?.length.toString()
                //viewChain.text = response.body()?.chain.toString()
            } else {
                Log.d("Response", response.errorBody().toString())
            }
        })

    }

}