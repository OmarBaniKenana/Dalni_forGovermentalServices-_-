package com.example.dalniapp

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
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_about_us.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.drawer_layout
import kotlinx.android.synthetic.main.activity_main.drawer_view
import kotlinx.android.synthetic.main.activity_tabbed.*
import kotlinx.android.synthetic.main.activity_user.*

class TabbedActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

   // lateinit var k: String
   var k2: String? = null

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tabbed)




        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE)


        val editor1 =getSharedPreferences("user", Context.MODE_PRIVATE)
        val name_user1 =editor1.getString("name_user","")
        val emal_user1 =editor1.getString("email_user","")
        val photo_user1 =editor1.getString("photo_user","")


        val navigationView : NavigationView  = findViewById(R.id.drawer_view_tab)
        val headerView : View = navigationView.getHeaderView(0)
        val navUsername : TextView = headerView.findViewById(R.id.f_nemu_11)
        val navemail : TextView = headerView.findViewById(R.id.f_nemu_22)
        val photourl = headerView.findViewById(R.id.profile_image1) as ImageView
        navUsername.text = name_user1.toString()
        navemail.text = emal_user1.toString()

        Glide.with(this).load(photo_user1)
            .error(R.drawable.ic_search)
            .into(photourl)


        setSupportActionBar(toolbar16)

        back_t.setOnClickListener {
            var intent = Intent (this, SearchActivity::class.java)
            startActivity(intent)
            finish()

        }
        datalode()
        drawer_view_tab.setNavigationItemSelectedListener(this)
        drawer_view_tab.itemIconTintList = null
    }


    private fun datalode() {
        var diser1 = intent.getStringExtra("diser")
        var idgov1: String? = intent.getStringExtra("digov")

        /////////////////////////////


        if (idgov1 != null) {
            FirebaseDatabase.getInstance().reference.child("Government").child(idgov1)
                .addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val k = snapshot.child("appImg").value.toString()
                        val k2 = snapshot.child("appUrl").value.toString()
                        val k3 = snapshot.child("location").child("1").value.toString()
                        val k4 = snapshot.child("location").child("2").value.toString()

                        val k5 = snapshot.child("img").value.toString()
                        val k6 = snapshot.child("name").value.toString()

                        val k10= snapshot.child("location").child("3").value.toString()
                       // setUpTabs(k , k2, k3 , k4 ,k5,k6,k7,diser1 as String,idgov1 as String)
                        setUpTabs(k , k2, k3 , k4 ,k5,k6,diser1 as String,idgov1 as String,k10)

                    }

                    override fun onCancelled(error: DatabaseError) {

                    }


                })
        }
    }

    private fun setUpTabs(k:String ,k2:String,k3:String,k4:String,k5:String,k6:String,k8:String,k9:String,k10:String){




        ///////////////////////////////

        var viewPager = findViewById<ViewPager>(R.id.viewPager)
        var tabs= findViewById<TabLayout>(R.id.tabs)
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(informationFragment(k5,k6,k8,k9), "معلومات")
        adapter.addFragment(appFragment(k, k2), "التطبيق")
          adapter.addFragment(LocationFragment(k3,k4,k10), "الموقع")

        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)


        tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_info_24)
        tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_smartphone_24)
        tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_place_24)




    }







    override fun onBackPressed() {
        if (drawer_layout_tab!!.isDrawerOpen(GravityCompat.START)) {
            drawer_layout_tab!!.closeDrawer(GravityCompat.START)
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
        drawer_layout_tab!!.closeDrawer(GravityCompat.START)
        return true
    }



   }