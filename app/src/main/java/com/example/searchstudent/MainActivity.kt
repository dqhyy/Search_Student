package com.example.searchstudent

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchstudent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentList: MutableList<Student>
    private lateinit var filteredList: MutableList<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Thiết lập View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo danh sách sinh viên
        studentList = mutableListOf(
            Student("Nguyen Van A", "MSSV001"),
            Student("Le Thi B", "MSSV002"),
            Student("Tran Van C", "MSSV003"),
            Student("Cao Van D", "MSSV004"),
            Student("Ngo Kha E", "MSSV005"),
            Student("Tran Xuan F", "MSSV006"),
            Student("Cao Ba G", "MSSV007"),
            Student("Do Van H", "MSSV008"),
            Student("Phung Thanh I", "MSSV009"),

            // Thêm nhiều sinh viên khác...
        )
        filteredList = studentList.toMutableList()

        // Thiết lập RecyclerView và Adapter
        studentAdapter = StudentAdapter(filteredList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = studentAdapter

        // Bắt sự kiện nhập liệu trong ô tìm kiếm
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }
        })
    }

    // Hàm lọc danh sách sinh viên
    private fun filter(keyword: String) {
        if (keyword.length > 2) {
            filteredList.clear()
            val query = keyword.toLowerCase()
            for (student in studentList) {
                if (student.name.toLowerCase().contains(query) || student.mssv.toLowerCase().contains(query)) {
                    filteredList.add(student)
                }
            }
        } else {
            filteredList.clear()
            filteredList.addAll(studentList)
        }
        studentAdapter.notifyDataSetChanged()
    }
}
