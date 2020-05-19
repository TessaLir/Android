package ru.wfms.vetukov.activityandservice

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LogService : Service() {

    private var stop = false

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        log("Старт сервиса")
        stop = false
        Thread(Runnable {
            kotlin.run {
                var i = 0;

                while (true) {
                    if (stop) break
                    log("Прошло $i секунд")
                    Thread.sleep(1000)
                    i++
                }

            }
        }).start()
    }

    override fun onDestroy() {
        super.onDestroy()
        stop = true
        log("Остановка сервиса")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        log("Запуск сервива")
        return super.onStartCommand(intent, flags, startId)
    }

    private fun log(message: String) {
        Log.d("Happy", message)
    }

}
