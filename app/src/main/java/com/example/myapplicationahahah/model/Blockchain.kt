package com.example.myapplicationahahah.model

import android.view.SurfaceControl
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Constructor
import java.sql.Timestamp

data class Chain(
    val index: Int,
    val transactions: MutableList<MyTransaction>,
    val timestamp: String,
    val previous_hash: String,
    val nonce: Int,
    val hash: String
)

data class MyTransaction(
    @SerializedName("x,y,z")
    val coordinates : String,
    @SerializedName("public key")
    val public_key : String,
    val name: String,
    val timestamp: String
)

data class Blockchain (
    val length: Int,
    val chain: Chain,
    //val peers: List<String>
)