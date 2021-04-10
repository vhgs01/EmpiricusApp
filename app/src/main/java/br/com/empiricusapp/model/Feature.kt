package br.com.empiricusapp.model


import com.google.gson.annotations.SerializedName

data class Feature(
    @SerializedName("description")
    val description: String,
    @SerializedName("title")
    val title: String
)