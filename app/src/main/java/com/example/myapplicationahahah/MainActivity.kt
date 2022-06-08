package com.example.myapplicationahahah

import android.R.attr.digits
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplicationahahah.repository.Repository
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var viewLength: TextView
    private lateinit var viewChain: TextView
    private lateinit var listView: ListView
    lateinit var timer: Runnable


    @RequiresApi(Build.VERSION_CODES.O)
    @Throws(IOException::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewLength = findViewById(R.id.length)
        listView = findViewById(R.id.list_view)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val answer = JSONObject("""{
                "length": 230, 
                "chain": {
                    "index": 228, 
                    "transactions": [{
                        "x,y,z": "1,8,5", 
                        "public key": "dfsjkk231289fhdio", 
                        "name": "Andrey", 
                        "timestamp": 1654515372.738047
                    }, {
                        "x,y,z": "8,6,3", 
                        "public key": "dfsjkk231289fhdio", 
                        "name": "Andrey", 
                        "timestamp": 1654515377.7489734
                    }], 
                    "timestamp": 1654515382.757515, 
                    "previous_hash": "000056dea4ff51ed8b48f6da023973087294edf03d85a90ca903c47499b9de94", 
                    "nonce": 29179, 
                    "hash": "00000d157a05e339175905cf5bf5825673a7e06a0f67175c1f1afdd27954c3d8"
                }, 
                "peers": ["http://192.168.2.154:8000/", "http://192.168.2.199:8000/"]
        }""".trimIndent())

        try {
            val handler = Handler()
            var iterator = 1
            timer = Runnable() {
                viewModel.getChain()
                viewModel.myResponse.observe(this, Observer { response ->
                    if (response.isSuccessful) {
                        var myResponse: MyResponse
                        Log.d("Response", response.body()?.length.toString())
                        viewLength.text = "transactions ip"
                        //var transactions = response.body()?.chain?.get("transactions")
                        //viewChain.text = "chain: " + response.body()?.chain?.joinToString(prefix = "<", postfix = ">", separator = "")
                        //viewChain.text = "chain:\n" + "transaction 1: " + response.body()?.chain?.get("transactions")
                        //transactions.
                        val chainArray = response.body()?.chain?.transactions
                        var listItems = mutableListOf<String>()
                        if (chainArray != null) {
                            for (i in 0 until chainArray.size) {
                                var transaction = chainArray[i]?.coordinates
                                transaction += ","
                                var stringOut = ""
                                stringOut += transaction
                                var sum = 0
                                var digits = ""
                                for (j in transaction){
                                    if (j.isDigit()){
                                        digits += j
                                    }
                                    else{
                                        if(!digits.isEmpty()){
                                            sum += digits.toInt()
                                            stringOut += "\t" + digits + "\t+\t"
                                            digits = ""
                                        }
                                    }
                                }
                                stringOut += "\tsum = ${sum}"
                                listItems.add(i, stringOut)
                            }
                        }

                        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)

                    } else {
                        Log.d("Response", response.errorBody().toString())
                    }
                })
                if (true) {
                    iterator++
                    handler.postDelayed(timer, 3000)
                }
            }
            handler.post(timer)
        }
        catch (e:Exception){
            Log.d("Response", "lalalal")
        }
    }
    /*@Throws(IOException::class)
    private fun onOnIntercept(chain: Chain): Response? {
        try {
            val response: Response = chain.proceed(chain.request())
            val content: String = UtilityMethods.convertResponseToString(response)
            Log.d(TAG, lastCalledMethodName.toString() + " - " + content)
            return response.newBuilder().body(create(response.body().contentType(), content))
                .build()
        } catch (exception: SocketTimeoutException) {
            exception.printStackTrace()
            if (listener != null) listener.onConnectionTimeout()
        }
        return chain.proceed(chain.request())
    }*/
}


