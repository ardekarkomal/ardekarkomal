package com.example.healthqrapp.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext : Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()
        setClickListener()
    }

/*
* abstract function to get layout id for setting content view
*/
    abstract fun getLayout():Int

/*
*  abstract function for initializing
*/
    abstract fun init()

/*
*  abstract function for setup click Listeners
*/
    abstract fun setClickListener()
}