package edu.ucne.Aplicada2.Tarea1.registrotecnicostarea1

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

@HiltAndroidApp
class RegistroTecnicos: Application() {

    class NotificationApplication : Application() {
        override fun onCreate() {
            super.onCreate()
                val channel = NotificationChannel(
                    "notification_channel_id",
                    "Notificaciones Generales",
                    NotificationManager.IMPORTANCE_HIGH
                )
                channel.description = "Canal para notificaciones generales de la app"

                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as
                        NotificationManager
                notificationManager.createNotificationChannel(channel)
            }
        }
    }