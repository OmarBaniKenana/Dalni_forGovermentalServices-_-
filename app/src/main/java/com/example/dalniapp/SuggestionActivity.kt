package com.example.dalniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_history.toolbar22
import kotlinx.android.synthetic.main.activity_suggestion.*

class SuggestionActivity : AppCompatActivity() {

    private lateinit var recview: RecyclerView
    private lateinit var adaptett:suggestion_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestion)

        setSupportActionBar(toolbar33)

        back_s.setOnClickListener {
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        val user = FirebaseAuth.getInstance().currentUser?.uid.toString()

        recview = findViewById<RecyclerView>(R.id.view_reci55)
        recview.layoutManager = LinearLayoutManager(this)
        val xx = FirebaseRecyclerOptions.Builder<suggestion_tict>()
            .setQuery(
                FirebaseDatabase.getInstance().reference.child("history").child(user),
                suggestion_tict::class.java
            )
            .build()

        adaptett = suggestion_adapter(xx, this)
        recview.adapter = adaptett

    }

    override fun onStart() {
        super.onStart()

        adaptett.startListening()

    }

    override fun onStop() {

        super.onStop()

        adaptett.stopListening()

    }


}