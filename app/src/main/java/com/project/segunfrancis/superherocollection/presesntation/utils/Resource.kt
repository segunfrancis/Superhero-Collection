package com.project.segunfrancis.superherocollection.presesntation.utils

/**
 * Created by SegunFrancis
 */

sealed class Resource<T> {
    data class Loading<T>(val message: String): Resource<T>()
    data class Success<T>(val result: T, val message: String): Resource<T>()
    data class Error<T>(val error: Throwable, val message: String): Resource<T>()
}