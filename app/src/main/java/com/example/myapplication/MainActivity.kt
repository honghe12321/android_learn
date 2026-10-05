package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.view.View
// 导入导入生成的 Binding 类
import com.example.myapplication.databinding.ActivityMainBinding

/**
 * 应用的主页面。
 *
 * Android 系统创建这个 Activity 后会调用 onCreate()。
 * onCreate() 使用 activity_main.xml 作为页面布局。
 */

private const val KEY_COUNT = "key_count"

class MainActivity : AppCompatActivity() {

    // 初始化整个布局文件id
    // 声明一个私有的、稍后初始化的、可变的 ActivityMainBinding 类型变量
    private lateinit var binding: ActivityMainBinding
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // 让 AppCompatActivity 完成基础初始化
        super.onCreate(savedInstanceState)
        count = savedInstanceState?.getInt(KEY_COUNT) ?: 0

        //这里ActivityMainBinding.inflate(layoutInflater)做了什么
        //找到 activity_main.xml
        //      ↓
        //读取 XML
        //      ↓
        //创建 LinearLayout
        //      ↓
        //创建 EditText、Button、TextView
        binding =
            ActivityMainBinding.inflate(layoutInflater)

        //把整棵 View 树显示到 Activity 中。
        setContentView(binding.root)
        // 隐藏顶栏
        supportActionBar?.hide()


        //旧的绑定方式,不需要了,都包含在binding中

        // val confirmButton = findViewById<Button>(R.id.confirm_button)
        //
        // val nameInput = findViewById<EditText>(R.id.name_input)
        //
        // val greetButton = findViewById<Button>(R.id.greet_button)
        //
        // val greetingResult = findViewById<TextView>(R.id.greeting_result)


        // if (count > 0) {
        //     confirmButton.text =
        //         getString(R.string.click_count, count)
        // }
        //        ↓直接改成
        if (count > 0) {
            binding.confirmButton.text =
                getString(R.string.click_count, count)
        }

        // 下面同理
        binding.confirmButton.setOnClickListener {
            count++
            Toast.makeText(
                this,
                getString(R.string.click_count,count),
                Toast.LENGTH_SHORT,
            ).show()
            binding.confirmButton.text = getString(R.string.click_count,count)
        }

        binding.greetButton.setOnClickListener {
            val name =
                binding.nameInput.text.toString().trim()

            if (name.isBlank()) {
                binding.nameInput.error =
                    getString(R.string.name_required)
                Toast.makeText(
                    this,
                    getString(R.string.name_required),
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                binding.nameInput.error = null

                binding.greetingResult.text =
                    getString(R.string.greeting, name)

                binding.greetingResult.visibility =
                    View.VISIBLE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("MainActivity", "onSaveInstanceState: 被调用了")
        outState.putInt(KEY_COUNT, count)
        super.onSaveInstanceState(outState)
    }
}