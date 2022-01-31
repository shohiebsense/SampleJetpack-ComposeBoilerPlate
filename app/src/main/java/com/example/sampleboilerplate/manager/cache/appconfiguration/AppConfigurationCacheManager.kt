package com.example.sampleboilerplate.manager.cache.appconfiguration

import android.content.Context
import com.example.sampleboilerplate.AppConfigurationCache
import com.example.sampleboilerplate.AppConfigurationCacheProto
import com.example.sampleboilerplate.manager.cache.BaseCacheManager
import com.example.sampleboilerplate.manager.cache.appConfigurationCacheDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppConfigurationCacheManager @Inject constructor(@ApplicationContext context: Context) : BaseCacheManager() {

    val appConfigurationCacheDataStore = context.appConfigurationCacheDataStore

    suspend fun saveConfiguration(appConfigurationCache: AppConfigurationCache){
        val appConfigurationCacheProto = AppConfigurationCacheProto.newBuilder()
                //info initialize another parameter
           // .setOnBoardingState(appConfigurationCache.onBoardingState)
            .build()

        save(appConfigurationCacheProto, appConfigurationCacheDataStore)
    }

    fun getAppConfigurationCacheAsFlow() = getValueAsFlow(appConfigurationCacheDataStore).map {  appConfigurationCacheValue ->
        com.example.sampleboilerplate.model.cache.AppConfigurationCache(
            onBoardingState = appConfigurationCacheValue.onBoardingState
        )
    }

    override suspend fun clear(block: ((Boolean) -> Unit)?) {
        withContext(Dispatchers.IO){
            try{
                appConfigurationCacheDataStore.updateData {
                    it.toBuilder().clear().build()
                }
                block?.let {
                    withContext(Dispatchers.Main) {it(true)}
                }
            }catch(e: Exception){
                block?.let {
                    withContext(Dispatchers.Main) {it(false)}
                }
            }
        }
    }

}


