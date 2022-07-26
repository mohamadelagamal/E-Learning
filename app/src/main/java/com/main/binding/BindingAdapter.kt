package com.main.binding

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.main.ui.home.ui.home.CourseAdapter
import com.model.Course

@androidx.databinding.BindingAdapter("app:error")
fun setError(textInputLayout: TextInputLayout, error: String) {
    textInputLayout.error = error
}

@BindingAdapter("app:imageSrc")
fun setImage(imageView: ImageView, imageId: Int) {
    imageView.setImageResource(imageId)
}

@BindingAdapter("Courses")
fun setCourses(
    recyclerView: RecyclerView,
    courses: List<Course>? = listOf(Course("0", "uxDesign", "60$"))
) {
    val adapter = recyclerView.adapter as CourseAdapter
    adapter.submitList(courses)
}

@BindingAdapter("bindText")
fun bindTextView(
    textView: TextView,
    tv_course: String? = ""
) {
    textView.text = tv_course
}