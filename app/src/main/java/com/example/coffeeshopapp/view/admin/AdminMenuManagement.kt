package com.example.coffeeshopapp.view.admin
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminMenuManagement : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_menu_management)

        val goBack = findViewById<Button>(R.id.buttonBack)
        goBack.setOnClickListener {
            onBackPressed()
        }

        val buttonETeaStock = findViewById<Button>(R.id.buttonETeaStock)
        buttonETeaStock.setOnClickListener {
            val intent = Intent(this, AdminEnglishTea::class.java)
            startActivity(intent)
        }

        val buttonMTeaStock = findViewById<Button>(R.id.buttonMTeaStock)
        buttonMTeaStock.setOnClickListener {
            val intent = Intent(this, AdminMasalaTea::class.java)
            startActivity(intent)
        }

        val buttonCoffeeStock = findViewById<Button>(R.id.buttonCoffeeStock)
        buttonCoffeeStock.setOnClickListener {
            val intent = Intent(this, AdminKarakCoffee::class.java)
            startActivity(intent)
        }

        val buttonCroissantStock = findViewById<Button>(R.id.buttonCroissantStock)
        buttonCroissantStock.setOnClickListener {
            val intent = Intent(this, AdminButterCroissant::class.java)
            startActivity(intent)
        }

        val buttonCakeStock = findViewById<Button>(R.id.buttonCakeStock)
        buttonCakeStock.setOnClickListener {
            val intent = Intent(this, AdminChocolateCake::class.java)
            startActivity(intent)
        }
    }
}