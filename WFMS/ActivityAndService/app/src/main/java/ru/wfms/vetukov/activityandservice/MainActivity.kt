package ru.wfms.vetukov.activityandservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.channels.Channel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart = main_service_start
        val btnStop = main_service_stop

        btnStart.setOnClickListener(this)
        btnStop.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        val service = Intent(this, LogService::class.java)
        when (v?.id) {
            R.id.main_service_start -> startService(service)
            R.id.main_service_stop ->  {
                stopService(service)
                val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val CHANAL_ID = "my_channel"
                val NOTIFICATION_ID = 1
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val notificationChannel = NotificationChannel(CHANAL_ID, "Мои уведомления", NotificationManager.IMPORTANCE_DEFAULT)
                    notificationChannel.description = "Опиание канала"
                    // Ерунда всякая
                    notificationChannel.enableLights(true)
                    notificationChannel.lightColor = Color.GREEN
                    //
                    notificationManager.createNotificationChannel(notificationChannel)
                }
                val builder = NotificationCompat.Builder(this, CHANAL_ID)
                    .setContentTitle("Информация")
                    .setContentText("Сурвис остановки")
                    .setVibrate(longArrayOf(0, 2000, 1100, 1000))
                    .setSmallIcon(R.mipmap.ic_launcher)

                var intent = Intent(this, MainActivity::class.java)
                val pIntent = PendingIntent.getActivity(this, 0, intent, 0)
                builder.setContentIntent(pIntent)
                notificationManager.notify(NOTIFICATION_ID, builder.build())
            }
            else -> clickStop()
        }
    }

    private fun clickStop() {
        TODO("Not yet implemented")
    }

    private fun clickStart() {
        TODO("Not yet implemented")
    }
}
