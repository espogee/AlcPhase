package com.ameyaw_ansu_proj.alc4phase_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        loadProfile()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun loadProfile() {
        val arrayList = arrayListOf(
            "Android",
            "Ghana",
            "ansugeabour@gmail.com",
            "+233242253858",
            "@Derick Ameyaw",
            "Derrick Ameyaw Ansu G."
        )
        tv_track?.text = arrayList[0]
        tv_country?.text = arrayList[1]
        tv_email?.text = arrayList[2]
        tv_contact?.text = arrayList[3]
        tv_username?.text = arrayList[4]
        tv_name?.text = arrayList[5]
    }


}
