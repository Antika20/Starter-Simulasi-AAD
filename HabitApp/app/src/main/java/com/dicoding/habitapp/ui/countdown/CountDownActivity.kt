package com.dicoding.habitapp.ui.countdown

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import com.dicoding.habitapp.R
import com.dicoding.habitapp.data.Habit
import com.dicoding.habitapp.notification.NotificationWorker
import com.dicoding.habitapp.utils.HABIT
import com.dicoding.habitapp.utils.HABIT_ID
import com.dicoding.habitapp.utils.HABIT_TITLE
import com.dicoding.habitapp.utils.ONE_TIME_REQUEST_HABBIT

class CountDownActivity : AppCompatActivity() {
    private  val workManager by lazy { WorkManager.getInstance(this)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_count_down)
        supportActionBar?.title = "Count Down"


        val habit = intent.getParcelableExtra<Habit>(HABIT) as Habit

        findViewById<TextView>(R.id.tv_count_down_title).text = habit.title

        val viewModel = ViewModelProvider(this).get(CountDownViewModel::class.java)

        //TODO 10 : Set initial time and observe current time. Update button state when countdown is finished
        viewModel.setInitialTime(habit.minutesFocus)
        viewModel.currentTimeString.observe(this){
            findViewById<TextView>(R.id.tv_count_down).text = it
        }

        viewModel.eventCountDownFinish.observe(this){
            updateButtonState(it)
            startOneTimeTaskHabbit(habit)
        }

        //TODO 13 : Start and cancel One Time Request WorkManager to notify when time is up.

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            viewModel.startTimer()
            updateButtonState(true)
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            viewModel.resetTimer()
            cancelHabbit()
            updateButtonState(false)
        }
    }


    private  fun startOneTimeTaskHabbit(habit: Habit){
        val data = Data.Builder()
            .putString(HABIT_TITLE,habit.title)
            .putInt(HABIT_ID,habit.id)
            .build()
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val oneTimeWorkRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java)
            .setInputData(data)
            .setConstraints(constraints)
            .addTag(ONE_TIME_REQUEST_HABBIT)
            .build()
        workManager.enqueue(oneTimeWorkRequest)


    }



    private fun cancelHabbit(){
        workManager.cancelAllWorkByTag(ONE_TIME_REQUEST_HABBIT)
    }

    private fun updateButtonState(isRunning: Boolean) {
        findViewById<Button>(R.id.btn_start).isEnabled = !isRunning
        findViewById<Button>(R.id.btn_stop).isEnabled = isRunning
    }
}