package com.example.coffeeshopapp.view.admin
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminFeedback : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_feedback)

        val goBack = findViewById<Button>(R.id.buttonBack3)
        goBack.setOnClickListener {
            onBackPressed()
        }
    }
}