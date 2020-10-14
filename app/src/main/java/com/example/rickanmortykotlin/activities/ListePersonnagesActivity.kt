package com.example.rickanmortykotlin.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickanmortykotlin.R
import com.example.rickanmortykotlin.adapters.MyAdapter
import com.example.rickanmortykotlin.controllers.DataController
import com.example.rickanmortykotlin.interfaces.MyCallback
import com.example.rickanmortykotlin.interfaces.OnListClickListener
import com.example.rickanmortykotlin.model.Info
import com.example.rickanmortykotlin.model.Results
import java.util.*

class ListePersonnagesActivity : AppCompatActivity(), MyCallback, OnListClickListener {

    var page = 1
    var infos: Info? = null
    var listeCharacters: List<Results> = ArrayList()
    lateinit var numPage: TextView
    lateinit var prev: Button
    lateinit var next: Button
    lateinit var recyclerView: RecyclerView
    lateinit var controller: DataController
    lateinit var mAdapter: RecyclerView.Adapter<*>
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liste_personnage)

        numPage = findViewById(R.id.numPage)
        prev = findViewById(R.id.previous)
        next = findViewById(R.id.next)
        recyclerView = findViewById(R.id.recyclerView)

        controller = DataController(listeCharacters as MutableList<Results>, this as MyCallback)

        lectureAPI(page)
        prev.isEnabled = false

        recyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        recyclerView.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        mAdapter = MyAdapter(listeCharacters, this)
        recyclerView.adapter = mAdapter
    }

    fun previous(view: View) {
        recyclerView.scrollToPosition(0)
        page--
        lectureAPI(page)
        if (!next.isEnabled) next.isEnabled = true
        if (page == 1) prev.isEnabled = false
    }

    fun next(view: View) {
        recyclerView.scrollToPosition(0)
        page++
        lectureAPI(page)
        if (!prev.isEnabled) prev.isEnabled = true
        if (page == infos?.pages) next.isEnabled = false
    }

    fun setNumPage(page: Int) {
        if (infos != null) {
            val pageActuelle = page.toString() + "/" + infos!!.pages
            numPage.text = pageActuelle
        }
    }

    fun lectureAPI(page: Int) {
        controller.start(page)
        setNumPage(page)
    }

    override fun onWorkDone(result: Int) {
        infos = controller.infos
        listeCharacters = controller.characters
        mAdapter.notifyDataSetChanged()
    }

    override fun onListClickListener(position: Int) {
        val character = listeCharacters.get(position)
        val intent = Intent(this, DetailPersonnageActivity::class.java)
        intent.putExtra("personnage",character)
        startActivity(intent)
        Toast.makeText(this,listeCharacters.get(position).name, Toast.LENGTH_SHORT)
    }
}