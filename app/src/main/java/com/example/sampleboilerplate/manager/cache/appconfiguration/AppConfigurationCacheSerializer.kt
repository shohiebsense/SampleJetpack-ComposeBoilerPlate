package com.example.sampleboilerplate.manager.cache.appconfiguration

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.example.sampleboilerplate.AppConfigurationCacheProto
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream


//info sample only, proto file was generated
object AppConfigurationCacheSerializer : Serializer<AppConfigurationCacheProto> {
    override val defaultValue: AppConfigurationCacheProto
        get() = AppConfigurationCacheProto.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): AppConfigurationCacheProto {
        try {
            return AppConfigurationCacheProto.parseFrom(input)
        } catch (e: InvalidProtocolBufferException){
            throw CorruptionException("Cannot read proto", e)
        }
    }

    override suspend fun writeTo(t: AppConfigurationCacheProto, output: OutputStream) {
        t.writeTo(output)
    }

}
