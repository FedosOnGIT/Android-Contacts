package com.example.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phone_page.*

class PhonePage : AppCompatActivity() {
    companion object {
        const val FOR_EXTRA_IMAGE = "for_extra_image"
        const val FOR_EXTRA_NUMBER = "for_extra_number"
        const val FOR_EXTRA_NAME = "for_extra_name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_page)
        makeImage()
    }

    fun makeImage() {
        val avatar = intent.getIntExtra(FOR_EXTRA_IMAGE, 0)
        calling_avatar.setImageResource(avatar)
        val nickname = intent.getStringExtra(FOR_EXTRA_NAME)
        calling_name.text = nickname
        val phoneNumber = intent.getStringExtra(FOR_EXTRA_NUMBER)
        calling_number.text = phoneNumber

    }
}