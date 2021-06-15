package com.example.dalniapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.android.material.navigation.NavigationView
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.coroutines.handleCoroutineException

class SearchActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {


    private lateinit var recview: RecyclerView
    private lateinit var adapter: myadapter

   // var displayList: ArrayList<model> = ArrayList()


    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)



        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)


        val editor1 =getSharedPreferences("user", Context.MODE_PRIVATE)
        val name_user1 =editor1.getString("name_user","")
        val emal_user1 =editor1.getString("email_user","")
        val photo_user1 =editor1.getString("photo_user","")


        val navigationView : NavigationView  = findViewById(R.id.drawer_view_search)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
        val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
        val photourl = headerView.findViewById(R.id.profile_image1) as ImageView
        navUsername.text = name_user1.toString()
        navemail.text = emal_user1.toString()

        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(photourl)








        var tx = intent.getStringExtra("searchString")




        setSupportActionBar(toolbar15)

        proBar1.visibility = View.VISIBLE
        recview = findViewById<RecyclerView>(R.id.recview)
        recview.layoutManager = LinearLayoutManager(this)

        val options = FirebaseRecyclerOptions.Builder<model>()
            .setQuery(
                FirebaseDatabase.getInstance().reference.child("TraGover").orderByChild("name")
                    .startAt(tx).endAt(tx + "\uf8ff"), model::class.java
            )
            .build()

        adapter = myadapter(options, this)
        recview.adapter = adapter
        //   processsearch1(data_search_a)
        Handler().postDelayed({

            proBar1.visibility = View.GONE

        }, 1500)

        drawer_view_search.setNavigationItemSelectedListener(this)
        drawer_view_search.itemIconTintList = null
    }

    override fun onBackPressed() {
        if (drawer_layout_search!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_search!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {


            R.id.menu_item_0 -> {

                startActivity(Intent(this, MainActivity::class.java).apply {
                    // putExtra("name",ed_m1.text.toString())


                })

            }

            R.id.menu_item_1 -> {


                startActivity(Intent(this, UserActivity::class.java).apply {
                    // putExtra("name",ed_m1.text.toString())
                })


            }
            R.id.menu_item_2 -> {


                //    startActivity(Intent(this, NewPasswordActivity::class.java).apply {
                // putExtra("name",ed_m1.text.toString())   })

                startActivity(Intent(this, G1Activity::class.java).apply {
                    // putExtra("name",ed_m1.text.toString())
                })
                finish()


            }
            R.id.menu_item_3 -> {

                // startActivity(Intent(this, LoginActivity::class.java).apply {
                // putExtra("name",ed_m1.text.toString())
                //  })



                startActivity(Intent(this, historyActivity::class.java).apply {
                    // putExtra("name",ed_m1.text.toString())
                })


            }
            R.id.menu_item_4 -> {

                // startActivity(Intent(this, LoginActivity::class.java).apply {
                // putExtra("name",ed_m1.text.toString())
                //  })
                startActivity(Intent(this, About_usActivity::class.java).apply {
                    // putExtra("name",ed_m1.text.toString())
                })


            }


        }
        drawer_layout_search!!.closeDrawer(GravityCompat.START)
        return true
    }



    override fun onStart() {
        super.onStart()

        adapter.startListening()

    }

    override fun onStop() {

        super.onStop()

        adapter.stopListening()

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchmenu, menu)
        val item = menu?.findItem(R.id.seach)

        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String?): Boolean {
                if (s != null) {
                    processsearch1(s)
                }
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {

                if (s != null) {
                    processsearch1(s)
                }

                return false
            }

        })


        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item?.itemId){

            R.id.back ->{
                var intent = Intent (this, MainActivity::class.java)
                startActivity(intent)
           finish()}
            else-> {
            }
        }
        return super.onOptionsItemSelected(item)
    }
    fun processsearch1(s: String) {
        proBar1.visibility = View.VISIBLE
        val options = FirebaseRecyclerOptions.Builder<model>()
            .setQuery(
                FirebaseDatabase.getInstance().reference.child("TraGover")
                    .orderByChild("name").startAt(s).endAt(s + "\uf8ff"), model::class.java
            )

            .build()
        // startAt(`%${s}%`).endAt(s + "\uf8ff").endAt(s + "\uf8ff")
        adapter = myadapter(options, this)
        adapter.startListening()
        recview.adapter = adapter
        proBar1.visibility = View.GONE

    }

}