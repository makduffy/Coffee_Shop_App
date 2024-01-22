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
    private var feedbackSubmitted = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCustomerOrderSubmittedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
            val commentIsValid = binding.feedbackComment.text.toString().isNotBlank()
            binding.btnSubmitFeedback.isEnabled = rating > 0 && commentIsValid
        }

        binding.btnSubmitFeedback.setOnClickListener {
            if (!feedbackSubmitted) {
                val rating = binding.ratingBar.rating
                val comment = binding.feedbackComment.text.toString()
                if (rating > 0 && comment.isNotBlank()) {
                    submitFeedback(rating, comment)
                    feedbackSubmitted = true
                    binding.btnSubmitFeedback.isEnabled = false
                    intent = Intent(this, CustomerAccount::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Feedback already submitted", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnReturnToMenu.setOnClickListener {
            startActivity(Intent(this, CustomerMenu::class.java))
        }
    }

    private fun submitFeedback(rating: Float, comment: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val feedback = Feedback(currentUser.uid, rating, comment)
            val feedbackRef = FirebaseDatabase.getInstance().getReference("feedback")
            feedbackRef.push().setValue(feedback)
                .addOnSuccessListener {
                    Toast.makeText(this, "Feedback submitted", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Failed to submit feedback", Toast.LENGTH_SHORT).show()
                }
        }
    }
}

