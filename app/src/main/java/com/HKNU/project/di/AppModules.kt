package com.HKNU.project.di

import androidx.multidex.BuildConfig
import com.HKNU.project.MyApplication.Companion.gson
import com.HKNU.project.common.AppConstant
import com.HKNU.project.network.SomeInfoRepository
import com.HKNU.project.network.SomeInfoRepositoryImpl
import com.HKNU.project.network.SomeInfoService
import com.HKNU.project.network.SomeInfoServiceImpl

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import timber.log.Timber
import javax.inject.Singleton

//Comm Hilt 를 사용하기 위한 Module 들을 정의한 파일.
// ApplicationModule 은 Retrofit 통신 모듈을 정의한 모듈로, 현재 단계에서 별도의 수정이 필요 없을 듯함. 이슈 발생 시 문의.
//Todo ApiModules 은 공공 api 통신의 규격에 따라 정의 되어야 하는 클래스와 연관되어 수정이 필요함.

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(AppConstant.API_HOST)
            .client(provideClientBuilder())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    fun provideClientBuilder(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder =
            OkHttpClient.Builder().cookieJar(NetCookie())
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClientBuilder.addInterceptor(interceptor)
        return okHttpClientBuilder.build()
    }

}

//Todo 공공 api 요청 모듈 제공. 프로젝트 형태와 구현 상황에 맞춰 수정하여 사용.
@Module
@InstallIn(SingletonComponent::class)
abstract class ApiModules {

    @Binds
    abstract fun bindsSomeInfoService(serviceImpl: SomeInfoServiceImpl): SomeInfoService

    @Binds
    abstract fun bindsSomeInfoRepository(repositoryImpl: SomeInfoRepositoryImpl): SomeInfoRepository
}

class NetCookie : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookies.forEach { _cookie ->
            android.webkit.CookieManager.getInstance().setCookie(url.toString(), _cookie.toString())
        }
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return android.webkit.CookieManager.getInstance().getCookie(url.toString())?.let { it ->
            it.split("[,;]".toRegex())
                .dropLastWhile { it.isEmpty() }
                .map { Cookie.parse(url, it.trim())!! }
                .toList()
                .onEach { cookie -> Timber.i("NetCookie - $cookie") }
        } ?: emptyList()
    }
}