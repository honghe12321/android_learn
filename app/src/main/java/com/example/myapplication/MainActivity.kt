package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import android.view.View
import android.widget.EditText
import android.widget.TextView

/**
 * 应用的主页面。
 *
 * Android 系统创建这个 Activity 后会调用 onCreate()。
 * onCreate() 使用 activity_main.xml 作为页面布局。
 */

private const val KEY_COUNT = "key_count"

class MainActivity : AppCompatActivity() {
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        // 让 AppCompatActivity 完成基础初始化
        super.onCreate(savedInstanceState)
        count = savedInstanceState?.getInt(KEY_COUNT) ?: 0
        // 读取 res/layout/activity_main.xml，
        // 创建其中的 LinearLayout 和 TextView，并显示到屏幕上
        setContentView(R.layout.activity_main)
        // 隐藏顶栏
        supportActionBar?.hide()

        val confirmButton = findViewById<Button>(R.id.confirm_button)

        val nameInput =
            findViewById<EditText>(R.id.name_input)

        val greetButton =
            findViewById<Button>(R.id.greet_button)

        val greetingResult =
            findViewById<TextView>(R.id.greeting_result)

        if (count > 0) {
            confirmButton.text =
                getString(R.string.click_count, count)
        }

        confirmButton.setOnClickListener {
            count++
            Toast.makeText(
                this,
                getString(R.string.click_count,count),
                Toast.LENGTH_SHORT,
            ).show()
            confirmButton.text = getString(R.string.click_count,count)
        }

        greetButton.setOnClickListener {
            val name =
                nameInput.text.toString().trim()

            if (name.isBlank()) {
                nameInput.error =
                    getString(R.string.name_required)
                Toast.makeText(
                    this,
                    getString(R.string.name_required),
                    Toast.LENGTH_SHORT,
                ).show()
            } else {
                nameInput.error = null

                greetingResult.text =
                    getString(R.string.greeting, name)

                greetingResult.visibility =
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