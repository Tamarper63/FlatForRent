package db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import data.ApartmentModel

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "MySharedPreferences",
        Context.MODE_PRIVATE
    )
    private val gson = Gson()
    private val favoriteItemsKey = "favoriteItems"

    fun saveFavoriteItems(favoriteItems: ArrayList<ApartmentModel>) {
        val editor = sharedPreferences.edit()
        val json = gson.toJson(favoriteItems)
        editor.putString(favoriteItemsKey, json)
        editor.apply()
    }

    fun getFavoriteItems(): ArrayList<ApartmentModel> {
        val json = sharedPreferences.getString(favoriteItemsKey, null)
        val type = object : com.google.gson.reflect.TypeToken<ArrayList<ApartmentModel>>() {}.type
        return gson.fromJson(json, type) ?: ArrayList()
    }
}