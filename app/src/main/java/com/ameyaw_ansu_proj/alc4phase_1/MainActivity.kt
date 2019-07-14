package com.ameyaw_ansu_proj.alc4phase_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_about.setOnClickListener {
            startActivity<AboutActivity>()
        }

        btn_profile.setOnClickListener {
            startActivity<ProfileActivity>()
        }
    }
}
