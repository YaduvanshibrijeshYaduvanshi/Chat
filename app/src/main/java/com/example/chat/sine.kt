package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class sine : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editpass: EditText
    private lateinit var btnsine: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbrefe: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sine)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editName = findViewById(R.id.name)
        editEmail = findViewById(R.id.email)
        editpass = findViewById(R.id.pass)
        btnsine = findViewById(R.id.buttonsine)


        btnsine.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editpass.text.toString()

            sineup(name,email,password)
        }



    }
    private fun sineup(name:String, email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping home

                    addUsewrTodatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@sine, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@sine, "some error found", Toast.LENGTH_SHORT).show()

                }
            }

    }

    private fun addUsewrTodatabase(name: String, email: String, uid: String){
        mDbrefe = FirebaseDatabase.getInstance().getReference()

        mDbrefe.child("User").child(uid).setValue(User(name,email,uid))


    }


}