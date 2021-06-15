package com.example.dalniapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.android.synthetic.main.header_menu.*

class UserActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferences2: SharedPreferences
    private val RC_SIGN_IN: Int = 1999
    lateinit var list_mail: List<AuthUI.IdpConfig>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)


        val editor1 =getSharedPreferences("user",Context.MODE_PRIVATE)
        val name_user1 =editor1.getString("name_user","")
        val emal_user1 =editor1.getString("email_user","")
        val photo_user1 =editor1.getString("photo_user","")


        name_txt.text=name_user1
        email_txt.text=emal_user1
        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(profile_image)


        val navigationView : NavigationView  = findViewById(R.id.drawer_view_u)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
        val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
        val photourl = headerView.findViewById(R.id.profile_image1) as ImageView
        navUsername.text = name_user1.toString()
        navemail.text = emal_user1.toString()

        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(photourl)

// Choose authentication providers
        list_mail = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

// Create and launch sign-in intent
        val user1 = FirebaseAuth.getInstance().currentUser
        if (user1 != null) {



        } else {





            f22()

        }


        sign_out_btn.setOnClickListener {
            val edit2 = sharedPreferences.edit()
            edit2.putString("name_user", "user1")
            edit2.putString("email_user", "user email")
            edit2.putString("photo_user", "null2")

            edit2.apply()

            AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener {
                    sign_out_btn.isEnabled = false
                    name_txt.text = "user"
                    email_txt.text = "Email"

                   Glide.with(this).load(R.drawable.ic_user_img)
                        .error(R.drawable.ic_user_img)
                        .into(profile_image)

                   f22()


                }
        }


        setSupportActionBar(toolbar_user)

        back_u.setOnClickListener {
            var intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

        drawer_view_u.setNavigationItemSelectedListener(this)
        drawer_view_u.itemIconTintList = null

    }

    override fun onBackPressed() {
        if (drawer_layout_u!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_u!!.closeDrawer(GravityCompat.START)
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
        drawer_layout_u!!.closeDrawer(GravityCompat.START)
        return true
    }


    private fun f22() {

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(list_mail)
                .build(),
            RC_SIGN_IN
        )

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {


                val user = FirebaseAuth.getInstance().currentUser
                val edit2 = sharedPreferences.edit()
                edit2.putString("name_user", user?.displayName)
                edit2.putString("email_user", user?.email)
                edit2.putString("photo_user", user?.photoUrl.toString())


                edit2.apply()

                name_txt.text=user?.displayName
                email_txt.text = user?.email
                Glide.with(this).load(user?.photoUrl)
                    .error(R.drawable.ic_search)
                    .into(profile_image)




                val navigationView : NavigationView  = findViewById(R.id.drawer_view_u)
                val headerView : View = navigationView.getHeaderView(0)
                val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
                val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
                val photourl = headerView.findViewById(R.id.profile_image1) as ImageView
                navUsername.text = user?.displayName
                navemail.text = user?.email

                Glide.with(this).load(user?.photoUrl)
                    .error(R.drawable.ic_search)
                    .into(photourl)





                sign_out_btn.isEnabled = true


            } else {

            }
        }
    }

}