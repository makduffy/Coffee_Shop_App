package com.example.coffeeshopapp.view.admin
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.R

class AdminChocolateCake : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_chocolate_cake)

        val goBack = findViewById<Button>(R.id.buttonBack4)
        goBack.setOnClickListener {
            onBackPressed()
        }
    }

    // This function invokes when the user presses the Increase button
    fun ButtonInc(view: View) {

        val counterMessageRef = findViewById<TextView>(R.id.textViewCounterDisplay)
        var counterValue = counterMessageRef.text.toString().toInt()
        if(counterValue+1 <= 20) { // check max counter value
            counterValue++
            counterMessageRef.text = counterValue.toString()
        }
    }

    // This function invokes when the user presses the Decrease button
    fun ButtonDec(view: View) {

        val counterMessageRef = findViewById<TextView>(R.id.textViewCounterDisplay)
        var counterValue = counterMessageRef.text.toString().toInt()
        if(counterValue-1 >= 0) { // check min counter value
            counterValue--
            counterMessageRef.text = counterValue.toString()
        }
    }

    // This function invokes when the user presses the Reset button
    fun ButtonReset(view: View) {

        val counterMessageRef = findViewById<TextView>(R.id.textViewCounterDisplay)
        var counterValue = counterMessageRef.text.toString().toInt()
        counterMessageRef.text = "0"
    }
}