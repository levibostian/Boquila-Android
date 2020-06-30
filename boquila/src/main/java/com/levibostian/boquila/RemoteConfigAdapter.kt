package com.levibostian.boquila

interface RemoteConfigAdapter {
    fun getValue(id: String): String?
    fun getValue(id: String, defaultValue: String): String

    fun <T: Any> getValue(id: String, clazz: Class<T>): T?
    fun <T: Any> getValue(id: String, clazz: Class<T>, defaultValue: T): T

    fun <T: Any> getValues(id: String, clazz: Class<T>): List<T>?
    fun <T: Any> getValues(id: String, clazz: Class<T>, defaultValue: List<T>): List<T>

    fun activate()
    fun refresh(onComplete: (Result<Unit>) -> Unit)
}