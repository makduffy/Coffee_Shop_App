package com.example.coffeeshopapp.view.customer

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshopapp.databinding.ActivityCustomerOrderSubmittedBinding
import com.example.coffeeshopapp.model.Feedback
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class CustomerOrderSubmitted : AppCompatActivity() {
    private lateinit var binding: ActivityCustomerOrderSubmittedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerOrderSubmittedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmitFeedback.setOnClickListener {
            val rating = binding.ratingBar.rating
            val feedbackRef = FirebaseDatabase.getInstance().getReference("feedback")
            val feedbackId = feedbackRef.push().key

            val comment = binding.feedbackComment.text.toString()
            val currentUser = FirebaseAuth.getInstance().currentUser
            val customerId = currentUser?.uid
            val feedback = customerId?.let { it1 -> Feedback(it1, rating, comment) }

            feedbackId?.let {
                feedbackRef.child(it).setValue(feedback).addOnSuccessListener {
                    Toast.makeText(this, "Feedback submitted", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnReturnToMenu.setOnClickListener {
            val intent = Intent(this, CustomerMenu::class.java)
            startActivity(intent)
        }

    }
}
