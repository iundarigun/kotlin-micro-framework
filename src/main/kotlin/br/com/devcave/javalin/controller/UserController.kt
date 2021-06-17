package br.com.devcave.javalin.controller

import br.com.devcave.javalin.model.User
import br.com.devcave.javalin.repository.UserRepository
import io.azam.ulidj.ULID
import io.javalin.http.Context
import org.apache.logging.log4j.LogManager

class UserController(private val userRepository: UserRepository) {

    private val logger = LogManager.getLogger(UserController::class.java)

    fun getAll(ctx: Context) {
        logger.info("getAll")
        ctx.json(userRepository.getAll())
    }

    fun create(ctx: Context) {
        logger.info("create")
        ctx.json(userRepository.create(
            User(
                id = ULID.random(),
                name = "dadada"
            )
        ))
    }
}