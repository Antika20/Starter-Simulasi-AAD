package com.dicoding.courseschedule.ui.add

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.home.HomeActivity
import com.dicoding.courseschedule.ui.list.ListViewModelFactory
import com.dicoding.courseschedule.util.TimePickerFragment
import java.text.SimpleDateFormat
import java.util.*

class AddCourseActivity : AppCompatActivity(), TimePickerFragment.DialogTimeListener {

    // Inisialisasi View Model
    private lateinit var  addCourseViewModel : AddCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        val factory = ListViewModelFactory.createFactory(this)
        addCourseViewModel = ViewModelProvider(this,factory).get(AddCourseViewModel::class.java)

        supportActionBar?.apply {
            title = getString(R.string.add_course)
            setDisplayHomeAsUpEnabled(true)
        }

        findViewById<ImageButton>(R.id.imgButton).setOnClickListener{
            showTimePickerStartTime()

        }

        findViewById<ImageButton>(R.id.imgButton2).setOnClickListener{
            showTimePickerEndTime()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_insert -> {
                val edtCourse = findViewById<TextView>(R.id.add_edCourse)
                val edtLeacture = findViewById<TextView>(R.id.add_ed_lecturer)
                val edtNote = findViewById<TextView>(R.id.add_ed_note)
                val SpinnerCourse = findViewById<Spinner>(R.id.SpinnerCourse)

                val  CourseAdd = edtCourse.text.toString().trim()
                val  LeactureAdd = edtLeacture.text.toString().trim()
                val  NoteAdd = edtNote.text.toString().trim()


                val dayCourse = SpinnerCourse.selectedItemPosition
                val startTime = findViewById<TextView>(R.id.add_start_item).text.toString()
                val endTime = findViewById<TextView>(R.id.add_end_time).text.toString()

                if (CourseAdd.isNotBlank() && LeactureAdd.isNotBlank() && NoteAdd.isNotBlank()) {
                    addCourseViewModel.insertCourse(
                        courseName =CourseAdd,
                        day = dayCourse,
                        startTime = startTime,
                        lecturer = LeactureAdd,
                        endTime = endTime,
                        note = NoteAdd
                    )
                    finish()
                } else {
                    Toast.makeText(this, getString(R.string.empty_list_message), Toast.LENGTH_SHORT)
                        .show()
                }
                true
            }
            android.R.id.home -> {
                startActivity(Intent(this,HomeActivity::class.java))
                    .also{finish()}
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun showTimePickerStartTime(){
        val dialogFragmentCourse = TimePickerFragment()
        dialogFragmentCourse.show(supportFragmentManager,"startPicker")

    }

    fun showTimePickerEndTime(){
        val dialogFragmentCourse = TimePickerFragment()
        dialogFragmentCourse.show(supportFragmentManager,"endPicker")
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY,hour)
            set(Calendar.MINUTE,minute)
        }.also {
            val dateFormatCourse = SimpleDateFormat("HH:mm", Locale.getDefault())
            if(tag == "startPicker" ){
                 setStartTimeCourse(dateFormatCourse,it)
            }
            else setEndTimeCourse(dateFormatCourse,it)
        }
    }
    private fun setStartTimeCourse(dateFormatCourse: SimpleDateFormat,calendar: Calendar){
        findViewById<TextView>(R.id.add_start_item).text = dateFormatCourse.format(calendar.time)
    }
    private fun setEndTimeCourse(dateFormatCourse: SimpleDateFormat,calendar: Calendar){
        findViewById<TextView>(R.id.add_end_time).text = dateFormatCourse.format(calendar.time)
    }
}