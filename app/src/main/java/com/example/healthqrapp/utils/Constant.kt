package com.example.healthqrapp.utils

import com.example.healthqrapp.model.InsuranceDetailsModel
import com.google.gson.Gson

object Constant {

    /*Shared Preferences Keys*/
    const val PREF_ADDTOCART_DATA = "UserData"
    var CART_TITLE="CartTitle"


    /**
     * @return All user data.
     *//*
    fun getUserData(): InsuranceDetailsModel {
        return Gson().fromJson(
            Utility.getPreference(PREF_ADDTOCART_DATA),  InsuranceDetailsModel::class.java   )
    }*/

    /***
     * get cart title
     */
    fun getCartTitle():String?{
        return Utility.getPreference(CART_TITLE)
    }

    /***
     * get cart image
     */
    /*fun getCartImage():Int{
        return getUserData().image
    }*/

    /***
     * get cart size
     */
   /* fun getCartSize():String{
        return getUserData().size
    }*/

    /***
     * get cart price
     */
  /*  fun getCartPrice():String{
        return getUserData().amount
    }*/

}