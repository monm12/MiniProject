package com.HKNU.project.network

import com.HKNU.project.extensions.empty
import com.HKNU.project.model.SomeInfoResponse
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

interface SomeInfoRepository {
    suspend fun getSomeInfoList(
        key: String,
        page: Int,
        org: String = String.empty(),
        mobile: Int = 1,
    ): Result<SomeInfoResponse, Exception>
}

class SomeInfoRepositoryImpl @Inject constructor(
    private val service: SomeInfoService,
) : SomeInfoRepository {
    override suspend fun getSomeInfoList(
        key: String,
        page: Int,
        org: String,
        mobile: Int,
    ): Result<SomeInfoResponse, Exception> {
        return request(
            service.getSomeInfoList(key, page, org, mobile)
        )
    }

    private fun <T> request(
        response: Response<T>,
    ): Result<T, Exception> {
        return try {
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!)
            } else {
                Result.Error(Exception("Fail to retrieve data."))
            }
        } catch (exception: Exception) {
            Timber.e(exception.toString())
            Result.Error(exception)
        }
    }
}