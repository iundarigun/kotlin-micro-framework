package br.com.devcave.javalin.repository.persistence

import org.jetbrains.exposed.sql.Table

private const val ID_LENGTH = 26
private const val USER_NAME_LENGTH = 200

object UserTable: Table("user") {
    val id = varchar("id", length = ID_LENGTH).primaryKey()
    val name = varchar("name", length = USER_NAME_LENGTH)
}