package com.example.healthqrapp.model

import com.google.gson.annotations.SerializedName

abstract class BaseData(
    @SerializedName("status")
    open val status: String = "",
    @SerializedName("message")
    open val message: String = "",
    @SerializedName("errorCode")
    open val errorCode: String = ""
)
