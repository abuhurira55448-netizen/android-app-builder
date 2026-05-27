package com.example.simpleapp

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*
import android.graphics.Typeface

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(40, 40, 40, 40)
            setBackgroundColor(Color.rgb(245, 247, 250))
        }

        val title = TextView(this).apply {
            text = "مرحبا بك في تطبيقي"
            textSize = 26f
            setTextColor(Color.rgb(13, 71, 161))
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val desc = TextView(this).apply {
            text = "هذا مشروع أندرويد بسيط جاهز للرفع على GitHub وبناء APK."
            textSize = 17f
            setTextColor(Color.DKGRAY)
            gravity = Gravity.CENTER
            setPadding(0, 25, 0, 25)
        }

        val button = Button(this).apply {
            text = "اضغط هنا"
            textSize = 18f
            setOnClickListener {
                Toast.makeText(this@MainActivity, "تم تشغيل التطبيق بنجاح ✅", Toast.LENGTH_SHORT).show()
            }
        }

        root.addView(title)
        root.addView(desc)
        root.addView(button)
        setContentView(root)
    }
}
