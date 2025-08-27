package ru.aloyaloya.gametracker.di

import kotlin.reflect.KClass

interface ComponentProvider {
    fun <T : Any> provideComponent(key: String, clazz: KClass<T>): T
}