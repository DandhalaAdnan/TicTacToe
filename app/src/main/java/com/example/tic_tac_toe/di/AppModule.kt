package com.example.tic_tac_toe.di // ktlint-disable package-name

import com.example.tic_tac_toe.data.KtorRealTimeMessagingClient
import com.example.tic_tac_toe.data.RealtimeMessagingClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.websocket.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(CIO) {
            install(Logging)
            install(WebSockets)
        }
    }

    @Singleton
    @Provides
    fun provideRealTimeMessagingClient(httpClient: HttpClient): RealtimeMessagingClient {
        return KtorRealTimeMessagingClient(httpClient)
    }
}
