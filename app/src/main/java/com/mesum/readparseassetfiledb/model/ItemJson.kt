package com.mesum.readparseassetfiledb.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ItemJson(
    @SerializedName("name") @Expose var name: String,
    @SerializedName("price") @Expose var price: Double,
    @SerializedName("quantity") @Expose var quantity: Int
)
