package com.example.coffeeshopapp.view.admin
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminOrders : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_orders)

        val goBack = findViewById<Button>(R.id.buttonBack2)
        goBack.setOnClickListener {
            onBackPressed()
        }
    }
}