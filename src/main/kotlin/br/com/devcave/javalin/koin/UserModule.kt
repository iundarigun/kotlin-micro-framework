package br.com.devcave.javalin.koin

import br.com.devcave.javalin.controller.UserController
import br.com.devcave.javalin.repository.UserRepository
import org.koin.dsl.module

val userModule = module {
    single { UserController(get()) }
    single { UserRepository() }
}