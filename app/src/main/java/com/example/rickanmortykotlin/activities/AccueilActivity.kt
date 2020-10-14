package com.example.rickanmortykotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rickanmortykotlin.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class AccueilActivity : AppCompatActivity() {

    lateinit var mAuth: FirebaseAuth
    lateinit var password: EditText
    lateinit var email: EditText
    lateinit var connexion: Button
    lateinit var signin: Button
    lateinit var signout: Button
    lateinit var connectedUser: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accueil)

        mAuth = FirebaseAuth.getInstance()
        password = findViewById(R.id.editTextTextPassword)
        email = findViewById(R.id.editTextTextEmailAddress)
        connexion = findViewById(R.id.connect)
        signin = findViewById(R.id.signin)
        signout = findViewById(R.id.signout)
        connectedUser = findViewById(R.id.textViewUserConnected)
    }

    override fun onStart(){
        super.onStart()
        val currentUser = mAuth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(user: FirebaseUser?) {
            connectedUser.text = mAuth.currentUser?.email ?: "Aucun"
    }

    fun signin(view: View) {
        val emailUser = email.text.toString()
        val passwordUser = password.text.toString()

        mAuth.createUserWithEmailAndPassword(emailUser, passwordUser)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                    email.setText("")
                    password.setText("")
                    user?.sendEmailVerification()
                    updateUI(null)
                } else {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    email.setText("")
                    password.setText("")
                    updateUI(null)
                }
            }
    }
    fun connect(view: View) {
        val emailUser = email.text.toString()
        val passwordUser = password.text.toString()

        mAuth.signInWithEmailAndPassword(emailUser, passwordUser)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    if (!user?.isEmailVerified!!) {
                        Toast.makeText(this, "Authentication failed : verify email", Toast.LENGTH_SHORT).show()
                        mAuth.signOut()
                        updateUI(null)
                    } else {
                        Toast.makeText(this, "Authentication success.", Toast.LENGTH_SHORT).show()
                        email.setText("")
                        password.setText("")
                        signin.visibility = View.INVISIBLE
                        connexion.visibility = View.INVISIBLE
                        signout.visibility = View.VISIBLE
                        updateUI(user)
                        val intent = Intent(this, ListePersonnagesActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    password.setText("")
                    updateUI(null)
                }
            }
    }

    fun signout(view: View) {
        mAuth.signOut()
        signout.visibility = View.INVISIBLE
        signin.visibility = View.VISIBLE
        connexion.visibility = View.VISIBLE
        updateUI(null)
    }
}