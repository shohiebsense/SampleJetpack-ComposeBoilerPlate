package com.example.sampleboilerplate.manager.cache

import androidx.datastore.core.DataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import java.io.IOException

abstract class BaseCacheManager {

	abstract suspend fun clear(block: ((Boolean) -> Unit)? = null)

	suspend inline fun <reified T: Any> save(data: T, dataStore: DataStore<T>, noinline  block: ((Boolean) -> Unit)? = null){
		withContext(Dispatchers.IO) {
			try {
				dataStore.updateData {
					data
				}
				block?.let {
					withContext(Dispatchers.Main){
						it(true)
					}
				}
			} catch (e: Exception) {

			}
		}
	}


	suspend inline fun <reified T: Any> getValue(dataStore: DataStore<T>) : T? {
		return withContext(Dispatchers.IO){
			try{
				val data = dataStore.data.first()
				withContext(Dispatchers.Main) { data }
			} catch(e: Exception){
				withContext(Dispatchers.Main) {T::class.java.newInstance()}
			}
		}
	}

	inline fun <reified T> getValueAsFlow(dataStore: DataStore<T>) : Flow<T> {
		return dataStore.data.catch {
			exception ->
			exception.printStackTrace()
			if(exception is IOException){
				emit(T::class.java.newInstance())
			}
			else throw exception
		}
	}


}