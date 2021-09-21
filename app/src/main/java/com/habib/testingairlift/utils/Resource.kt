package com.habib.testingairlift.utils

/*
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}*/
sealed class Resource<T>(val data: T?, val message: String) {
    class Success<T>(data: T) : Resource<T>(data, Constants.SUCCESS)
    class Error<T>(message: String) : Resource<T>(null, message)
    class Loading<T>(message: String = Constants.LOADING) : Resource<T>(null, message)
}