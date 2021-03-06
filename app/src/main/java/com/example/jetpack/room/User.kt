package com.example.jetpack.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbluser")
data class User(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String,
        val email: String,
        val phoneNumber: String,
        val address: String
)