package com.mesum.readparseassetfiledb

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mesum.readparseassetfiledb.model.Item
import com.mesum.readparseassetfiledb.model.ItemJson
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ParseDataViewModel(val ItemDao: ItemDao) : ViewModel() {

     fun insertRawData(context: Context){
        viewModelScope.launch {
            for (i in getItemJson(context)) {
                val item =
                    Item(itemName = i.name, itemPrice = i.price, quantityInStock = i.quantity)
                ItemDao.insert(item)

            }
        }
    }

   private fun getItemJson(context : Context): List<ItemJson>{
        lateinit var jsonString: String
        try {
            jsonString =
                context.assets.open("item.json")
                    .bufferedReader()
                    .use { it.readText() }



        }catch (e : Exception){
            Log.d("saif", e.toString())
        }


        val listCountryType = object : TypeToken<List<ItemJson>>() {}.type
        return Gson().fromJson( jsonString, listCountryType)



    }

    class ParseViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ParseDataViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return ParseDataViewModel(itemDao) as T
            }

            throw IllegalArgumentException("Unknown ViewModel class")

        }

    }

}