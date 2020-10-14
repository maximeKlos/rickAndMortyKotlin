package com.example.rickanmortykotlin.activities

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.rickanmortykotlin.R
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_info_connected_user.*

class InfoConnectedUserFragment(user: FirebaseUser?) : Fragment() {
    var user: FirebaseUser? = user
    lateinit var tvEmail: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info_connected_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvEmail = view.findViewById(R.id.emailUser)

        tvEmail.text = user?.email ?: "Aucun"
    }

    companion object {
        fun newInstance(user: FirebaseUser?) = InfoConnectedUserFragment(user)
    }
}