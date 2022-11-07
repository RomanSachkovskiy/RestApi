package com.example.RestApi.service

import com.example.RestApi.dto.PersonDto

interface PersonService {

    fun getAll(): List<PersonDto>

    fun getById(id: Int): PersonDto

    fun getAllByName(name: String): List<PersonDto>

    fun getAllByLastName(lastName: String): List<PersonDto>

    fun create(dto: PersonDto): Int

    fun update(id: Int, dto: PersonDto)

    fun deleteById(id: Int)

}