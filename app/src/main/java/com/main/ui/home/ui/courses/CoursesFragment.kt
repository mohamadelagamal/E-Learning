package com.main.ui.home.ui.courses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.e_learning.R
import com.example.e_learning.databinding.FragmentCoursesBinding
import com.main.ui.home.ui.home.CourseAdapter
import com.model.Category
import com.model.Course

class CoursesFragment : Fragment() {

    private lateinit var binding: FragmentCoursesBinding
    private lateinit var coursesViewModel: CoursesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        coursesViewModel =
            ViewModelProvider(this)[CoursesViewModel::class.java]

        binding = FragmentCoursesBinding.inflate(inflater, container, false)
        /*val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        binding.lifecycleOwner = this
        binding.coursesViewModel = coursesViewModel

        val coursesAdapter = CourseAdapter()
        val categoryAdapter = CategoryAdapter()
        binding.rvMostPopular.adapter = categoryAdapter
        binding.rvPopular.adapter = coursesAdapter
        binding.rvBestSelling.adapter = coursesAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        coursesViewModel.mostPopularCategories.value =
            MutableList(5) { Category("$it", "Design", R.drawable.ic_design) }

        coursesViewModel.popularCourses.value =
            MutableList(5) { Course("$it", "UX Design", "60$") }
    }
}
