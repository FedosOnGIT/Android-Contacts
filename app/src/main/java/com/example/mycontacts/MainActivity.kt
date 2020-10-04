package com.example.mycontacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var numbers : List<Contact>
    private lateinit var contacts : List<MyContact>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (!checkPermission()) {
            askPermission()
        } else {
            numbers = fetchAllContacts()
            createContacts()
            initRecycler()
        }
    }


    private fun initRecycler() {
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ContactAdapter(contacts) {
            val settings = Intent(this, PhonePage::class.java)
            settings.putExtra(PhonePage.FOR_EXTRA_IMAGE, it.image)
            settings.putExtra(PhonePage.FOR_EXTRA_NUMBER, it.contact.phoneNumber)
            settings.putExtra(PhonePage.FOR_EXTRA_NAME, it.contact.name)
            Toast.makeText(
                this,
                "Clicked on ${it.image}",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(settings)
        }
    }

    private fun checkPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_CONTACTS
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_CONTACTS),
            MY_CONSTANT
        )
    }

    data class MyContact(val contact: Contact, val image: Int)

    private val avatars = arrayOf(
        R.drawable.avatar_1,
        R.drawable.avatar_2,
        R.drawable.avatar_3,
        R.drawable.avatar_4,
        R.drawable.avatar_5,
        R.drawable.avatar_6,
        R.drawable.avatar_7,
        R.drawable.avatar_8
    )

    private fun createContacts() {
        var index = 0
        contacts = numbers.map {
            MyContact(it, avatars[(index++) % 8])
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_CONSTANT -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Thank you for permission", Toast.LENGTH_SHORT).show()
                    numbers = fetchAllContacts()
                    createContacts()
                } else {
                    Toast.makeText(this, "Sorry but I don't have your permission", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        const val MY_CONSTANT = 25
    }
}