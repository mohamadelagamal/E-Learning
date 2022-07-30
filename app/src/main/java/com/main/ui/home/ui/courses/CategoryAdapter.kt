package com.main.ui.home.ui.courses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.e_learning.databinding.RvCategoriesItemBinding
import com.example.e_learning.databinding.RvCoursesItemBinding
import com.model.Category
import com.model.Course

class CategoryAdapter : ListAdapter<Category,
        CategoryAdapter.CategoryViewHolder>(DiffCallback) {

    /*var onItemClickListener: ((Course) -> Unit)? = null
    var onItemLongClickListener: ((Course) -> Unit)? = null*/

    inner class CategoryViewHolder(
        private var binding: RvCategoriesItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
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

    companion object DiffCallback : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Category,
            newItem: Category
        ): Boolean {
            return oldItem.name == newItem.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            RvCategoriesItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = getItem(position)
        holder.bind(category)
    }
}