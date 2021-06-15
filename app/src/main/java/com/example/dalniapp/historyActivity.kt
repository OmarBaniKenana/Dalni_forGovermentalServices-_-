package com.example.dalniapp

import android.content.Context
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
import com.google.firebase.database.Query
import com.google.firebase.database.core.view.QueryParams
import kotlinx.android.synthetic.main.activity_history.*
import kotlinx.android.synthetic.main.activity_main.*

import java.util.*

class historyActivity : AppCompatActivity() {


    private lateinit var recview: RecyclerView
    private lateinit var adaptett:History_adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)


        setSupportActionBar(toolbar22)

        back_h.setOnClickListener {
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
        val user =FirebaseAuth.getInstance().currentUser?.uid.toString()

        recview = findViewById<RecyclerView>(R.id.view_reci5)
        recview.layoutManager= LinearLayoutManager(this)
        val xx = FirebaseRecyclerOptions.Builder<History_tict>()
            .setQuery(
                FirebaseDatabase.getInstance().reference.child("history").child(user),
                History_tict::class.java
            )
            .build()

        adaptett = History_adapter(xx,this)
        recview.adapter = adaptett

        ItemTouchHelper(object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            adaptett.deleteItem(viewHolder.adapterPosition)

            }
        }).attachToRecyclerView(recview)
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