package com.example.RestApi.service

import com.example.RestApi.dto.PersonDto
import com.example.RestApi.exception.PersonNotFoundException
import com.example.RestApi.model.PersonModel
import com.example.RestApi.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(

    private val personRepository: PersonRepository,
) : PersonService {

    override fun getAll(): List<PersonDto> = personRepository.getAll().map { it.toDto() }

    override fun getById(id: Int): PersonDto =
        personRepository.findById(id)?.toDto() ?: throw PersonNotFoundException(id)

    override fun getAllByName(name: String): List<PersonDto> = personRepository.getAllByName(name).map { it.toDto() }

    override fun getAllByLastName(lastName: String): List<PersonDto> =
        personRepository.getAllByLastName(lastName).map { it.toDto() }

    override fun create(dto: PersonDto): Int = personRepository.create(dto.name, dto.lastName)

    override fun update(id: Int, dto: PersonDto) {
        personRepository.update(id, dto.name, dto.lastName)
    }

    override fun deleteById(id: Int) {
        personRepository.deleteById(id)
    }

    private fun PersonModel.toDto() = PersonDto(
        id = id,
        name = name,
        lastName = lastName,
    )

}