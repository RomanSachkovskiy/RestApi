package com.example.RestApi.model

import javax.persistence.Id
import javax.persistence.Column
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType

data class PersonModel(

    val id: Int = 0,

    val name: String = "",

    val lastName: String = ""
)