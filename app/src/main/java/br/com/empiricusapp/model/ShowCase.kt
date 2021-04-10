package br.com.empiricusapp.model


import com.google.gson.annotations.SerializedName

data class ShowCase(
    @SerializedName("groups")
    val groups: List<Group>
)