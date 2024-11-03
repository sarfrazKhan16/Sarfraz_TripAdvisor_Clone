package com.example.tripadvisor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        firebaseAuth = FirebaseAuth.getInstance()

        // References to UI elements
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        val emailEt: EditText = findViewById(R.id.emailEt)
        val passET: EditText = findViewById(R.id.passET)
        val confirmPassEt: EditText = findViewById(R.id.confirmPassEt)

        // Listener for navigating to Sign In
        textView.setOnClickListener {
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        // Listener for Sign Up button
        button.setOnClickListener {
            val email = emailEt.text.toString()
            val pass = passET.text.toString()
            val confirmPass = confirmPassEt.text.toString()

            // Validation of input fields
            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {
                    // Firebase authentication logic
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, SignIn::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception?.message ?: "Error occurred", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
