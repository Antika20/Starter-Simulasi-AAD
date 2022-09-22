package com.dicoding.courseschedule.notification

import android.app.*
import android.content.BroadcastReceiver
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.ui.home.HomeActivity
import com.dicoding.courseschedule.util.*
import java.util.*

class DailyReminder : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d("TAG", "onReceive: true")
        executeThread {
            val repository = DataRepository.getInstance(context)
            val courses = repository?.getTodaySchedule()

            Log.d("TAG", "onReceive:$courses")

            courses?.let {
                if (it.isNotEmpty()) showNotification(context, it)
            }
        }
    }

    //TODO 12 : Implement daily reminder for every 06.00 a.m using AlarmManager
    @RequiresApi(Build.VERSION_CODES.M)
    fun setDailyReminder(context: Context) {
        Log.d("TAG", "setDailyReminder: run ")
        val alarmManagerCourse = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 6)
        calendar.set(Calendar.MINUTE, 0 )
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent =
            PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT )
        alarmManagerCourse.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

        Toast.makeText(context, "Daily Reminder Course Set Up", Toast.LENGTH_SHORT).show()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun cancelAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyReminder::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(context, NOTIFICATION_ID, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
        Toast.makeText(context, "Daily Reminder Course dibatalkan", Toast.LENGTH_SHORT).show()

    }

    fun showNotification(context: Context, content: List<Course>) {
        //TODO 13 : Show today schedules in inbox style notification & open HomeActivity when notification tapped
        val notificationStyle = NotificationCompat.InboxStyle()
        val timeString = context.resources.getString(R.string.notification_message_format)
        content.forEach {
            val courseData = String.format(timeString, it.startTime, it.endTime, it.courseName)
            notificationStyle.addLine(courseData)
        }

        val channel_IDCourse = NOTIFICATION_CHANNEL_ID
        val channel_NameCourse = NOTIFICATION_CHANNEL_NAME
        val intent = Intent(context, HomeActivity::class.java)

//        val intentNOTIF = PendingIntent.getActivity(
//        context, NOTIFICATION_ID,intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val pendingIntent = TaskStackBuilder.create(context).run {
            addParentStack(HomeActivity::class.java)
            addNextIntent(intent)
            getPendingIntent(
                    NOTIFICATION_ID, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val mNotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val mBuilder = NotificationCompat.Builder(context, channel_IDCourse)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_notifications)
            .setStyle(notificationStyle)
            .setContentTitle(context.resources.getString(R.string.today_schedule))
            .setContentText(context.resources.getString(R.string.notification_message_format))
            .setAutoCancel(true)

        // Oreo keatas
        @RequiresApi(Build.VERSION_CODES.M)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channel_IDCourse,
                channel_NameCourse,
                NotificationManager.IMPORTANCE_DEFAULT,
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000)
            mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            mBuilder.setChannelId(channel_IDCourse)
            mNotificationManager.createNotificationChannel(channel)

//            mNotificationManager.createNotificationChannel(channel)
        }
        notificationStyle.setBuilder(mBuilder)
        notificationStyle.setSummaryText("Reminder")
        notificationStyle.setBigContentTitle("Today's Schedules")


        val notification = mBuilder.build()
        mNotificationManager.notify(NOTIFICATION_ID, notification)
    }
}