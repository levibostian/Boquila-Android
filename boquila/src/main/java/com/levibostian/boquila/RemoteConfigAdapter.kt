package com.levibostian.boquila

interface RemoteConfigAdapter {
    fun <T> getValue(id: String): T?
    fun <T> getValue(id: String, defaultValue: T): T
    fun activate()
    fun refresh(onComplete: (Result<Unit>) -> Unit)
}