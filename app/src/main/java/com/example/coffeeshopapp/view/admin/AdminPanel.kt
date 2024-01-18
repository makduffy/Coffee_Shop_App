package com.example.coffeeshopapp.view.admin
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminPanel : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_panel)

        val buttonLogOut = findViewById<Button>(R.id.buttonLogOut)
        buttonLogOut.setOnClickListener {
            // Navigate to AdminSignIn activity
            val intent = Intent(this, AdminSignIn::class.java)
            startActivity(intent)
            finish()
        }

        val buttonMenuM = findViewById<Button>(R.id.buttonMenuM)
        buttonMenuM.setOnClickListener {
            // Navigate to AdminMenuManagement activity
            val intent = Intent(this, AdminMenuManagement::class.java)
            startActivity(intent)
        }

        val buttonOrders = findViewById<Button>(R.id.buttonOrders)
        buttonOrders.setOnClickListener {
            // Navigate to AdminOrders activity
            val intent = Intent(this, AdminOrders::class.java)
            startActivity(intent)
        }

        val buttonFeedback = findViewById<Button>(R.id.buttonFeedback)
        buttonFeedback.setOnClickListener {
            // Navigate to AdminFeedback activity
            val intent = Intent(this, AdminFeedback::class.java)
            startActivity(intent)
        }
    }
}