package com.example.RestApi.repository

import com.example.RestApi.model.PersonModel
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.jdbc.support.GeneratedKeyHolder
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class PersonRepositoryImpl(

    private val jdbcTemplate: NamedParameterJdbcTemplate,
    private val jdbcTemplateForCreateTable: JdbcTemplate

) : PersonRepository {

    @PostConstruct
    fun init() {
        jdbcTemplateForCreateTable.execute(
            "create table if not exists persons(" +
                    "id serial primary key, " +
                    "name varchar(255)," +
                    " last_name varchar(255)" +
                    ")"
        )
    }

    override fun getAll(): List<PersonModel> =
        jdbcTemplate.query(
            "select * from persons order by id",
            ROW_MAPPER
        )

    override fun findById(id: Int): PersonModel? =
        jdbcTemplate.query(
            "select * from persons where id = :id",
            mapOf(
                "id" to id,
            ),
            ROW_MAPPER
        ).firstOrNull()

    override fun getAllByName(name: String): List<PersonModel> =
        jdbcTemplate.query(
            "select * from persons where name = :name",
            mapOf(
                "name" to name,
            ),
            ROW_MAPPER
        )

    override fun getAllByLastName(lastName: String): List<PersonModel> =
        jdbcTemplate.query(
            "select * from persons where last_name = :lastName",
            mapOf(
                "lastName" to lastName,
            ),
            ROW_MAPPER
        )

    override fun create(name: String, lastName: String): Int {
        val keyHolder = GeneratedKeyHolder()
        jdbcTemplate.update(
            "insert into persons (name, last_name) values(:name, :lastName)",
            MapSqlParameterSource(
                mapOf(
                    "name" to name,
                    "lastName" to lastName,
                )
            ),
            keyHolder,
            listOf("id").toTypedArray()
        )
        return keyHolder.keys?.getValue("id") as Int
    }

    override fun update(id: Int, name: String, lastName: String) {
        jdbcTemplate.update(
            "update persons set name = :name, last_name = :lastName where id = :id",
            mapOf(
                "id" to id,
                "name" to name,
                "lastName" to lastName,
            )
        )
    }

    override fun deleteById(id: Int) {
        jdbcTemplate.update(
            "delete from persons where id = :id",
            mapOf(
                "id" to id,
            )
        )
    }

    private companion object {
        val ROW_MAPPER = RowMapper<PersonModel> { rs, _ ->
            PersonModel(
                id = rs.getInt("id"),
                name = rs.getString("name"),
                lastName = rs.getString("last_name")
            )
        }
    }

}