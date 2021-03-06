package com.asturchio.shamelessapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_preview.*

class PreviewActivity : AppCompatActivity() {
//lateinit means that we will assign it later.
    private lateinit var message: Message
    private lateinit var messagePreviewText: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)
/*
        //this grabs the data from mainactivity
        val contactName = intent.getStringExtra("Contact Name")
        val contactNumber = intent.getStringExtra("Contact Number")
        val myDisplayName = intent.getStringExtra("My Display Name")
        val includeJunior = intent.getBooleanExtra("Include Junior",false)
        val jobTitle = intent.getStringExtra("Job Title")
        val immediateStart = intent.getBooleanExtra("Immediate Start", false)
        val startDate = intent.getStringExtra("Start Date")
        val testString = "Contact Name: $contactName, Contact Number: $contactNumber, My Display Name: $myDisplayName, Include Junior: $includeJunior, Job Title: $jobTitle, Immediate Start: $immediateStart, Start Date: $startDate"
        text_view_message.text = testString

 */
        displayMessage()
        setUpButton()
    }

    private fun displayMessage() {
        message = intent.getSerializableExtra("Message") as Message
        messagePreviewText = """
                Hi ${message.contactName},
                
                My name is ${message.myDisplayName} and I am ${message.getFullJobDescription()}.
                
                I have a portfolio of apps to demonstrate my technical skills that I can show on request.
                
                I am able to start a new position ${message.getAvailability()}.
                
                Please get in touch if you have any suitable roles for me.
                
                Thanks and best regards.
            """.trimIndent()

        text_view_message.text = messagePreviewText
    }

    private fun setUpButton() {

        button_send_message.setOnClickListener {
            //Sent message intent

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto: ${message.contactNumber}")
                putExtra("sms_body", messagePreviewText)
            }
            startActivity(intent)
        }

    }


}