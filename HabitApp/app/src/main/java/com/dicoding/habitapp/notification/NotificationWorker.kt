package com.dicoding.habitapp.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.dicoding.habitapp.R
import com.dicoding.habitapp.ui.add.AddHabitActivity
import com.dicoding.habitapp.utils.HABIT_ID
import com.dicoding.habitapp.utils.HABIT_TITLE
import com.dicoding.habitapp.utils.NOTIFICATION_CHANNEL_ID
import com.dicoding.habitapp.utils.NOTIF_UNIQUE_WORK

class NotificationWorker(ctx: Context, params: WorkerParameters) : Worker(ctx, params) {

    private val habitId = inputData.getInt(HABIT_ID, 0)
    private val habitTitle = inputData.getString(HABIT_TITLE)

    override fun doWork(): Result {
        val prefManager = androidx.preference.PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val shouldNotify = prefManager.getBoolean(applicationContext.getString(R.string.pref_key_notify), false)

        //TODO 12 : If notification preference on, show notification with pending intent
        showNotificationHabbit(shouldNotify)
        return Result.success()
    }

    private fun showNotificationHabbit(notifyHabbit: Boolean){
        if (notifyHabbit ){
            val mNotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            val mBuilder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID).apply {
                setContentTitle(habitTitle)
                setContentText(applicationContext.getString(R.string.notify_content))
                setSmallIcon(R.drawable.ic_notifications)

                val intent = Intent(applicationContext,AddHabitActivity::class.java)
                setContentIntent(PendingIntent.getActivity(applicationContext,0,intent,PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT))

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(NOTIFICATION_CHANNEL_ID,
                        NOTIF_UNIQUE_WORK, NotificationManager.IMPORTANCE_DEFAULT)

                    mNotificationManager.createNotificationChannel(channel)
                }
            }
            val notification = mBuilder.build()
            mNotificationManager.notify(habitId,notification)
        }

    }
}


