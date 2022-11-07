package com.example.RestApi.repository

import com.example.RestApi.model.PersonModel

interface PersonRepository {

    fun getAll(): List<PersonModel>

    fun findById(id: Int): PersonModel?

    fun getAllByName(name: String): List<PersonModel>

    fun getAllByLastName(lastName: String): List<PersonModel>

    fun create(name: String, lastName: String): Int

    fun update(id: Int, name: String, lastName: String)

    fun deleteById(id: Int)

}