package com.example.sampleboilerplate.manager.api


sealed class ResponseState<T>(val data: T? = null){
	class OnSuccess<T>(data: T) :  ResponseState<T>(data)
	class OnError<T>(data: T? = null, val message: String = data.toString()) : ResponseState<T>(data = data){
		override fun toString(): String {
			return message
		}
	}
	class OnException<T>(data: T) : ResponseState<T>(data = data)
}
