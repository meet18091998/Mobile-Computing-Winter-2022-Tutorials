package com.mc2022.template

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_my_another_name.*

class ShowMyAnotherName : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_my_another_name)

        var name = intent.getStringExtra("my_name")

        show_another_name_textview.text = name

        show_name_2.setOnClickListener {
            val intent = Intent(applicationContext, ShowMyName::class.java)
            intent.putExtra("my_name", show_another_name_edittext.text.toString())
            startActivity(intent)
        }

    }
}