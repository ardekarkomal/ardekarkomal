package com.example.healthqrapp.lib.retrofit

import com.example.healthqrapp.R
import com.example.healthqrapp.lib.utils.Utility
import com.example.healthqrapp.application.MainApplication
import com.example.healthqrapp.model.BaseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom API Callback class to handle response and failure from APIs.
 *
 * @param <T> Custom Model class which are created for handling response of the API.
 * @author Komal Ardekar
 */
abstract class APICallbacks<T> : Callback<T> {

    /**
     * @param call     Instance of Call interface.
     * @param response Response of the API to determine if it indicates success.
     */
    override fun onResponse(call: Call<T>, response: Response<T>) {
        Utility.printLog("Url: " + response.raw().request.url)

        if (response.isSuccessful && response.body() != null) {
            Utility.printLog("Response: ${response.body()}")

            if (response.body() is BaseData && (response.body() as BaseData).status == "1")
                onSuccess(response.body())
            else if (response.body() is BaseData && (response.body() as BaseData).status == "0" && ((response.body() as BaseData).errorCode == "99" || (response.body() as BaseData).errorCode == "103"))
                onForcedLogout()
            else
                onError(response.body())

        } else onFailure(call, Exception())
    }

    /**
     * @param call      Instance of Call interface.
     * @param throwable Throw an error.
     */
    override fun onFailure(call: Call<T>, throwable: Throwable) {
        val errorMsg = if (!Utility.isNetworkAvailable()) {
           MainApplication.instance.getContext().getString(R.string.internet_error_msg)

        } else {
            MainApplication.instance.getContext().getString(R.string.server_error_msg)
        }

        onFailure(errorMsg, throwable.message ?: "")
    }

    /**
     * @param response If not null and status is equal to 1 then sends response to the activity.
     */
    protected abstract fun onSuccess(response: T?)

    /**
     * When status us equal to o and error code is equal to 99 then trigger forced logout.
     */
    protected abstract fun onForcedLogout()

    /**
     * @param response If not null and status is equal to 0 then sends response to the activity.
     */
    protected abstract fun onError(response: T?)

    /**
     * @param generalErrorMsg UserData defined error message.
     * @param systemErrorMsg  System defined error message.
     */
    protected abstract fun onFailure(generalErrorMsg: String, systemErrorMsg: String)
}
