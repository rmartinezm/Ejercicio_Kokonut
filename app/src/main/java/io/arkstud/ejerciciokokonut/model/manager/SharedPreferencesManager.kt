package io.arkstud.ejerciciokokonut.model.manager

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesManager {

    /* Shared Preferences instance */
    private lateinit var sharedPreferences: SharedPreferences

    /**
     *
     * @param applicationContext
     */
    fun initialize(applicationContext: Context, name: String){
        sharedPreferences = applicationContext.getSharedPreferences(name, Context.MODE_PRIVATE)
    }

    /**
     *
     * @param key
     * @param value
     */
    fun putString(key: String, value: String?) {
        with (sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    /**
     *
     * @param key
     * @param defaultValue
     */
    fun getString(key: String, defaultValue: String? = null) : String? = sharedPreferences.getString(key, defaultValue)

    /**
     *
     * @param key
     * @param defaultValue
     */
    fun getBoolean(key: String, defaultValue: Boolean = false) : Boolean? = sharedPreferences.getBoolean(key, defaultValue)

    /**
     *
     * @param key
     * @param value
     */
    fun putBoolean(key: String, value: Boolean) {
        with (sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }



}