package com.example.dalniapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_g1.*


class G1Activity : AppCompatActivity() {


    var gTic2 = ArrayList<G_tict>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_g1)



        lode_g()
        g_view.adapter =g_adapter(gTic2,this)

        // val cc2 =findViewById<>(R.id.cc2) as
        cc2.setViewPager(g_view)

        bu_next.setOnClickListener {

            if ( g_view.currentItem +1 <7 ){
                g_view.currentItem = g_view.currentItem+1
                bu_next.text ="next"

            }else{

                bu_next.text ="finsh"
                startActivity(Intent(this,MainActivity::class.java).apply {
                    finish()
                })
            }


        }

    }

    fun lode_g() {


        gTic2.add(G_tict("fd3", "dsfdfdds", R.drawable.logo_hd))
        gTic2.add(G_tict("fded", "dfdsds", R.drawable.g2))
        gTic2.add(G_tict("d1", "dsds",  R.drawable.g3))
        gTic2.add(G_tict("d2", "dsdfds",  R.drawable.g4))
        gTic2.add(G_tict("d3", "dsfdfdds",  R.drawable.g5))
        gTic2.add(G_tict("d3", "dsfdfdds",  R.drawable.g6))
        gTic2.add(G_tict("d3", "dsfdfdds",  R.drawable.g7))


    }
}