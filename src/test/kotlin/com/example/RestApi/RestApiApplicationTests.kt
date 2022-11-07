package com.example.RestApi

import com.example.RestApi.dto.PersonDto
import com.example.RestApi.exception.PersonNotFoundException
import com.example.RestApi.service.PersonService
import com.fasterxml.jackson.databind.ObjectMapper
import org.hamcrest.Matchers.hasItem
import org.junit.jupiter.api.*
import org.junit.jupiter.api.function.Executable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class RestApiApplicationTests {

    private val baseUri = "http://localhost:8080/person"
    private var personCreated: PersonDto = PersonDto(name = "Ivan", lastName = "Ivanov")
    private var personUpdated: PersonDto = PersonDto(name = "Ivan", lastName = "Gerasimov")

    companion object {
        var id: Int = 0
        var jsonPersonCreated: String = ""
        var jsonPersonUpdated: String = ""
    }

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var personService: PersonService

    @Test
    @Order(1)
    fun `1 - Get list of all persons`() {
        mockMvc.perform(get(baseUri).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk)
    }

    @Test
    @Order(2)
    fun `2 - Create new Person`() {
        jsonPersonCreated = objectMapper.writeValueAsString(personCreated)

        val request = mockMvc.perform(
            post(baseUri).content(jsonPersonCreated)
                .contentType(MediaType.APPLICATION_JSON)
        )
        request.andExpect(status().isCreated)
        id = request.andReturn().response.contentAsString.toInt()
    }

    @Test
    @Order(3)
    fun `3 - Get Person by id`() {
        mockMvc.perform(get("$baseUri/id/$id").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isFound)
            .andExpect(jsonPath("$.name").value(objectMapper.readTree(jsonPersonCreated).get("name").asText()))
            .andExpect(jsonPath("$.lastName").value(objectMapper.readTree(jsonPersonCreated).get("lastName").asText()))
    }

    @Test
    @Order(4)
    fun `4 - Update Person`() {
        val update: PersonDto = personUpdated
        update.id = id
        mockMvc.perform(
            put("$baseUri/$id").content(objectMapper.writeValueAsString(personUpdated))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isAccepted)


        Assertions.assertEquals(personService.getById(id).toString(), update.toString())
    }

    @Test
    @Order(5)
    fun `5 - Get persons by name`() {
        jsonPersonUpdated = objectMapper.writeValueAsString(personUpdated)
        val path = objectMapper.readTree(jsonPersonUpdated).get("name").asText()

        mockMvc.perform(get("$baseUri/name/$path").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isFound)
            .andExpect(jsonPath("$.[*].name", hasItem(path)))
    }

    @Test
    @Order(6)
    fun `6 - Get persons by lastName`() {
        val path = objectMapper.readTree(jsonPersonUpdated).get("lastName").asText()

        mockMvc.perform(get("$baseUri/lastName/$path").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isFound)
            .andExpect(jsonPath("$.[*].lastName", hasItem(path)))
    }

    @Test
    @Order(7)
    fun `7 - Delete Person`() {
        mockMvc.perform(delete("$baseUri/$id").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isAccepted)

        val thrown: PersonNotFoundException = Assertions.assertThrows(
            PersonNotFoundException::class.java,
            Executable {
                personService.getById(id)
            }
        )

        Assertions.assertEquals("Person with id = $id not found", thrown.message)
    }

}

