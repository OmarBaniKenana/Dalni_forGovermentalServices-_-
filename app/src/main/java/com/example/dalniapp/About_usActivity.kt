package com.example.dalniapp

import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ActionMenuView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.ActionBarContextView
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_main.*

class About_usActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{


    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)


        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)


        val editor1 =getSharedPreferences("user", Context.MODE_PRIVATE)
        val name_user1 =editor1.getString("name_user","")
        val emal_user1 =editor1.getString("email_user","")
        val photo_user1 =editor1.getString("photo_user","")


        val navigationView : NavigationView  = findViewById(R.id.drawer_view_a)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
        val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
        val photourl = headerView.findViewById(R.id.profile_image1) as ImageView
        navUsername.text = name_user1.toString()
        navemail.text = emal_user1.toString()

        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(photourl)





        setSupportActionBar(toolbar_about)

        back_a.setOnClickListener {
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }


        drawer_view_a.setNavigationItemSelectedListener(this)
        drawer_view_a.itemIconTintList = null
    }
    override fun onBackPressed() {
        if (drawer_layout_a!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_a!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {


            R.id.menu_item_0 -> {

                startActivity(Intent(this, MainActivity::class.java).apply {

                })

            }

            R.id.menu_item_1 -> {


                startActivity(Intent(this, UserActivity::class.java).apply {

                })


            }
            R.id.menu_item_2 -> {

                startActivity(Intent(this, G1Activity::class.java).apply {

                })
                finish()


            }
            R.id.menu_item_3 -> {

                startActivity(Intent(this, historyActivity::class.java).apply {

                })

            }
            R.id.menu_item_4 -> {
                startActivity(Intent(this, About_usActivity::class.java).apply {

                })


            }


        }
        drawer_layout_a!!.closeDrawer(GravityCompat.START)
        return true
    }


}