package com.example.healthqrapp.lib.utils

import com.example.healthqrapp.BuildConfig

object Constant {

     var SELECTED_ITEM_COUNT="0"
     var SELECTED_LOGIN_TYPE = ""

    /*Shared Preferences Keys*/
    const val PREF_ADDTOCART_DATA = "UserData"
    var CART_TITLE="CartTitle"

    var BASE_URL ="" /*when (BuildConfig.BUILD_TYPE) {
        "debug" -> DEV_BASE_URL
        "preprod" -> PRE_PROD_BASE_URL
        "staging" -> STAG_BASE_URL
        else -> PROD_BASE_URL
    }*/

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