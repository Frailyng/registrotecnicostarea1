package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.data.remote.CompraApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton
import kotlin.jvm.java

@InstallIn(SingletonComponent::class)
@Module
object ApiModule {
    const val BASE_URL = "http://apip2compras.somee.com/"

    @Provides
    @Singleton
    fun providesMoshi(): Moshi =
        Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).add(DateAdapter())
            .build()

    @Provides
    @Singleton
    fun providesUsuariosApi(moshi: Moshi): CompraApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CompraApi::class.java)
    }
}