package com.example.healthqrapp.utils

import android.content.Context
import java.security.Key

object Utility {
    /**
     * Store string in preferences file.
     *
     * @param key   Preferences Key
     * @param value String to be stored
     */
    fun addPreference(context:Context,key: String, value: String) {
        val sharedPreference =  context.getSharedPreferences("PREFERENCE_NAME",Context.MODE_PRIVATE)
        val editor =sharedPreference.edit()
        editor.putString(key, value)
        editor.apply()
        editor.commit()
    }

    /**
     * Retrieve string from preferences file.
     *
     * @param key Preferences Key
     * @return String from preferences file based on Key
     */
    fun getPreference(key: String): String? {
        return null
    }
}