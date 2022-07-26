package com.main.ui.home.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learning.databinding.RvCoursesItemBinding
import com.model.Course

class CourseAdapter : ListAdapter<Course,
        CourseAdapter.CourseViewHolder>(DiffCallback) {

    /*var onItemClickListener: ((Course) -> Unit)? = null
    var onItemLongClickListener: ((Course) -> Unit)? = null*/

    inner class CourseViewHolder(
        private var binding: RvCoursesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(course: Course) {
            binding.course = course
            binding.executePendingBindings()

            /*binding.root.setOnClickListener {
                onItemClickListener?.invoke(course)
            }
            binding.root.setOnLongClickListener {
                onItemLongClickListener?.invoke(course)
                true
            }*/
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Course>() {
        override fun areItemsTheSame(
            oldItem: Course,
            newItem: Course
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Course,
            newItem: Course
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            RvCoursesItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = getItem(position)
        holder.bind(course)
    }
}