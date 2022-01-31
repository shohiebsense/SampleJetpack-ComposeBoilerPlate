package com.example.sampleboilerplate.manager.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import com.example.sampleboilerplate.AppConfigurationCacheProto
import com.example.sampleboilerplate.manager.cache.appconfiguration.AppConfigurationCacheSerializer


private const val APP_CONFIGURATION_CACHE_NAME = "app_configuration_cache"


val Context.appConfigurationCacheDataStore: DataStore<AppConfigurationCacheProto> by dataStore(
	fileName = APP_CONFIGURATION_CACHE_NAME,
	serializer = AppConfigurationCacheSerializer
)
