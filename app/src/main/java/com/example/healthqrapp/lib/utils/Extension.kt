package com.example.healthqrapp.lib.base

import android.content.Context
import android.widget.Toast

/**
 * Print message in Toast
 */
fun showMessage(context:Context,msg:String){
    Toast.makeText(context,msg,Toast.LENGTH_LONG).show()
}



