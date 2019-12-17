package com.robertsproats.marsrobots.domain.usecase

abstract class UseCase<in P, R> {

    abstract suspend fun execute(parameters: P? = null): R

}