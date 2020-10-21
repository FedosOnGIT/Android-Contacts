package com.example.mycontacts

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_phone_page.*
import kotlinx.android.synthetic.main.contact.*

class PhonePage : AppCompatActivity() {
    companion object {
        const val FOR_EXTRA_IMAGE = "for_extra_image"
        const val FOR_EXTRA_NUMBER = "for_extra_number"
        const val FOR_EXTRA_NAME = "for_extra_name"
    }
    lateinit var nickname : String
    lateinit var phoneNumber : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_page)
        makeImage()
        calling_card.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }
    }

    private fun makeImage() {
        val avatar = intent.getIntExtra(FOR_EXTRA_IMAGE, 0)
        calling_avatar.setImageResource(avatar)
        nickname = intent.getStringExtra(FOR_EXTRA_NAME).toString()
        calling_name.text = nickname
        phoneNumber = intent.getStringExtra(FOR_EXTRA_NUMBER).toString()
        calling_number.text = phoneNumber

    }
}