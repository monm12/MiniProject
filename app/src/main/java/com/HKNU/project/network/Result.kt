package com.HKNU.project.network

sealed class Result<out S, out E> {

    data class Success<out S>(val data: S) : Result<S, Nothing>()
    data class Error<out E>(val exception: E) : Result<Nothing, E>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>

    fun <S> success(s: S) = Success(s)
    fun <E> error(e: E) = Error(e)

    fun fold(fnS: (S) -> Any, fnE: (E) -> Any): Any =
        when (this) {
            is Success -> fnS(data)
            is Error -> fnE(exception)
        }
}

fun <S, E> Result<S, E>.onSuccess(fn: (success: S) -> Unit): Result<S, E> =
    this.apply { if (this is Result.Success) fn(data) }
fun <S, E> Result<S, E>.onException(fn: (exception: E) -> Unit): Result<S, E> =
    this.apply { if (this is Result.Error) fn(exception) }