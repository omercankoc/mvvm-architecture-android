package com.omercankoc.mvvm.model

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("name")
    val country : String? = null,
    @SerializedName("capital")
    val capital : String? = null,
    @SerializedName("region")
    val region : String? = null,
    @SerializedName("currency")
    val currency : String? = null,
    @SerializedName("language")
    val language : String? = null,
    @SerializedName("flag")
    val flag : String? = null
) {
}