package com.example.RestApi.controller

import com.example.RestApi.dto.PersonDto
import com.example.RestApi.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService
) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<PersonDto> = personService.getAll()

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getById(@PathVariable id: Int): PersonDto = personService.getById(id)

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getAllByName(@PathVariable name: String) = personService.getAllByName(name)

    @GetMapping("/lastName/{lastName}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getAllByLastName(@PathVariable lastName: String) = personService.getAllByLastName(lastName)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: PersonDto): Int = personService.create(dto)

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun update(@PathVariable id: Int, @RequestBody dto: PersonDto) {
        personService.update(id, dto)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun deleteById(@PathVariable id: Int) {
        personService.deleteById(id)
    }

}