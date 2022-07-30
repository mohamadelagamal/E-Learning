package com.main.ui.home.ui.courses

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.model.Category
import com.model.Course

class CoursesViewModel : ViewModel() {
    val popularCourses = MutableLiveData<MutableList<Course>>()
    val mostPopularCategories = MutableLiveData<MutableList<Category>>()
}