package com.example.sampleboilerplate.manager.api.masterdataapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MasterDataModule {

    @Singleton
    @Provides
    fun provideMasterDataRepository(
        profilApi: MasterDataApi
    ) = MasterDataRepository(profilApi)

    @Provides
    @Singleton
    fun provideMasterDataApi(privateRetrofit: Retrofit): MasterDataApi =
        privateRetrofit.create(MasterDataApi::class.java)


}