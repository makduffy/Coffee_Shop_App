package com.example.coffeeshopapp.view.admin
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminSignIn : AppCompatActivity() {
    // Hardcoded admin credentials
    private val adminEmail = "admin@gmail.com"
    private val adminPassword = "letmein1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_sign_in)

        val buttonSignIn = findViewById<Button>(R.id.buttonSignIn)
        buttonSignIn.setOnClickListener {
            // Retrieve user input from EditTexts
            val emailInput = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val passwordInput = findViewById<EditText>(R.id.editTextTextPassword).text.toString()

            // Check if the input matches the admin credentials
            if (emailInput == adminEmail && passwordInput == adminPassword) {
                // Authentication successful, navigate to AdminDashboard activity
                val intent = Intent(this, AdminPanel::class.java)
                startActivity(intent)
                finish() // Optional: Finish the current activity if you don't want the user to go back to it
            } else {
                // Authentication failed, show an error message or handle accordingly
                Toast.makeText(this, "Invalid credentials, please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}