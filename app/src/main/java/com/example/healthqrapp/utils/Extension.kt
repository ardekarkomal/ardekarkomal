package com.example.healthqrapp.base

import android.content.Context
import android.widget.Toast


fun showMessage(context:Context,msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}

