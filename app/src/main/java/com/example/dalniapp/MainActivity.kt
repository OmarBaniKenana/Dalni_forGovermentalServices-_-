package com.example.dalniapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_user.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var x: String? = null
    lateinit var sharedPreferences: SharedPreferences



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)









        val user1 = FirebaseAuth.getInstance().currentUser
        if (user1!=null) {




        }else{




        }

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)

        val editor1 = getSharedPreferences("user", Context.MODE_PRIVATE)
        val name_user1 = editor1.getString("name_user", "")
        val emal_user1 = editor1.getString("email_user", "")
        val photo_user1 = editor1.getString("photo_user", "")

        val navigationView : NavigationView  = findViewById(R.id.drawer_view)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
        val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
        val photourl = headerView.findViewById(R.id.profile_image1) as ImageView

        navUsername.text = name_user1.toString()
        navemail.text = emal_user1.toString()
        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(photourl)




        /*-----------------------Toolbar----------------------*/
        setSupportActionBar(toolbar1)


        /*-----------------------drawerToggle----------------------*/
        val drawerToggle: ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this, drawer_layout, toolbar1, R.string.opem, R.string.drawer_close_string
        ) {

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)

        drawerToggle.syncState()

        /*-----------------------drawerSelectedItem----------------------*/
        drawer_view.setNavigationItemSelectedListener(this)

        drawer_view.itemIconTintList = null



    }



    override fun onBackPressed() {
        if (drawer_layout!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout!!.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.getItemId()) {


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
        drawer_layout!!.closeDrawer(GravityCompat.START)
        return true
    }


    fun act_1_2(view: View) {

        var xx = tt2_1.text
/*
   var intent: Intent = Intent(this, SearchActivity::class.java)
   var bundle = Bundle()
   bundle.putString("searchString", xx.toString())
   intent.putExtras(bundle)
   startActivity(intent)

   startActivity(Intent(this, SearchActivity::class.ja.apply {
       // putExtra("name",ed_m1.text.toString())
       // putExtra("name",ed_m1.text.toString())
       putExtra("sendtext_m_s", xx)
   })
*/
        var intent: Intent = Intent(this, SearchActivity::class.java)
      //  var bundle = Bundle()

        intent.putExtra("searchString", xx.toString())
        startActivity(intent)

}

    fun act_1_sug(view: View) {

        startActivity(Intent(this, SuggestionActivity::class.java))


    }

}