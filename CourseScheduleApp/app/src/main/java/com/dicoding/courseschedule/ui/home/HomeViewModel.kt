package com.dicoding.courseschedule.ui.home

import androidx.lifecycle.*
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.data.DataRepository
import com.dicoding.courseschedule.util.QueryType
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: DataRepository): ViewModel() {

    private val _queryType = MutableLiveData<QueryType>()

     val _scheduleCourse = Transformations.switchMap(_queryType) {
         repository.getNearestSchedule(it)
     }

    init {
        _queryType.value = QueryType.CURRENT_DAY
    }

    fun setQueryType(queryType: QueryType) {
        _queryType.value = queryType
    }

    fun getTodayCourse(): LiveData<Course?> =
    repository.getNearestSchedule(
    _queryType.value?:  QueryType.CURRENT_DAY

    )
}
