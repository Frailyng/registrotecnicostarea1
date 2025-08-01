package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1.di

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateAdapter {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

    @ToJson
    fun tojson(value: Date?): String? {
        return value?.let { dateFormat.format(it) }
    }

    @FromJson
    fun fromJson(value: String?): Date? {
        return value?.let { dateFormat.parse(it) }
    }
}