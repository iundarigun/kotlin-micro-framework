package br.com.devcave.javalin

import br.com.devcave.javalin.controller.UserController
import br.com.devcave.javalin.koin.userModule
import br.com.devcave.javalin.repository.persistence.UserTable
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder
import io.javalin.apibuilder.ApiBuilder.post
import io.javalin.apibuilder.ApiBuilder.get
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class RestApplication : KoinComponent {
    private val userController by inject<UserController>()

    fun start() {
        Javalin.create { config ->
            config.defaultContentType = "application/json"
        }.apply {
            routes {
                ApiBuilder.path("users") {
                    get(userController::getAll)
                    post(userController::create)
                }
            }
        }.start(7000)
    }

    fun connectDatabase() {
        Database.connect("jdbc:h2:mem:regular;DB_CLOSE_DELAY=-1;", "org.h2.Driver")

        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(UserTable)
        }
    }
}

fun main() {
    startKoin {
        modules(userModule)
    }
    RestApplication().start()
    RestApplication().connectDatabase()
}