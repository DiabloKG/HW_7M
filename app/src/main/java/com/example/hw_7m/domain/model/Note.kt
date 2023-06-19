package com.example.hw_7m.domain.model

class Note(
    val id: Int = DEFAULT_ID,
    val title: String,
    val description: String
    ) : java.io.Serializable {
    companion object {
        const val DEFAULT_ID = 0
    }
}