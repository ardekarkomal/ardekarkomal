package com.example.healthqrapp.lib.retrofit

import com.example.healthqrapp.lib.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * APIService class is used to get singleton Retrofit instance and make call to server to retrieve data from server.
 * It is also used to declare API references with their input parameters.
 *
 * @author Shardul Mandvikar
 */
interface APIService {

    companion object {

        /**
         * @return Instance of RetrofitAPI class
         */
        fun getBaseUrl(): APIService {

            val instance: APIService by lazy {
                val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .baseUrl(Constant.BASE_URL)
                    .build()
                retrofit.create(APIService::class.java)
            }

            return instance
        }

        /**
         * @return Instance of OkHttpClient class with modified timeout
         */
        private fun getClient(): OkHttpClient {
            val httpTimeout: Long = 20
            val okHttpClientBuilder = OkHttpClient.Builder()
            okHttpClientBuilder.connectTimeout(httpTimeout, TimeUnit.SECONDS)
            okHttpClientBuilder.readTimeout(httpTimeout, TimeUnit.SECONDS)
            return okHttpClientBuilder.build()
        }
    }
}

