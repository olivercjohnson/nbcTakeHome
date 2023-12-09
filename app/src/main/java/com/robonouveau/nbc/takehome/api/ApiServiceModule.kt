package com.robonouveau.nbc.takehome.api

import com.robonouveau.nbc.takehome.interactors.ReadFileInteractor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiServiceModule {

    @Singleton
    @Provides
    fun provideNbcService(
        fileInteractor: ReadFileInteractor
    ): NbcService {
        return MockNbcService(fileInteractor)
    }

}
