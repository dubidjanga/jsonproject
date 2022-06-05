package com.example.myapplicationahahah

import com.example.myapplicationahahah.model.Blockchain
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName

class MyResponse {
    @SerializedName("details")
    @Expose
    var details: List<Blockchain>? = null

    @SerializedName("success")
    @Expose
    var success: Int? = null

    @SerializedName("message")
    @Expose
    var message: String? = null
}