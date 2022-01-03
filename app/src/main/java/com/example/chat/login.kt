package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editpass: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsine: Button
    private lateinit var mAuth: FirebaseAuth




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //hide actionBarr from login activity
        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()
        editEmail = findViewById(R.id.email)
        editpass = findViewById(R.id.pass)
        btnlogin = findViewById(R.id.buttonLogin)
        btnsine = findViewById(R.id.buttonsine)

        btnsine.setOnClickListener {
            val intent = Intent(this, sine::class.java)
            startActivity(intent)
        }

        btnlogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editpass.text.toString()

            login(email,password)
        }


    }
    private fun login(email: String, password: String){

        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for login
                    //code for jumping home
                    val intent = Intent( this, MainActivity::class.java)

                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "User dose not exist", Toast.LENGTH_SHORT).show()


                }
            }

    }



}