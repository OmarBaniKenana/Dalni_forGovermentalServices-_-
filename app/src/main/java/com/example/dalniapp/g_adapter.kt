package com.example.dalniapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class g_adapter(val grgtic: ArrayList<G_tict>,var context: Context) :
    RecyclerView.Adapter<g_adapter.pager2holder>() {


    override fun onBindViewHolder(holder: pager2holder, position: Int) {
        var ss =grgtic.get(position)
        holder.itemv_name.text = ss.name

        ss.img?.let { holder.itemv_img.setImageResource(it) }
        holder.itemv_img.setOnClickListener {
            if (position == 7) {

                Toast.makeText(context, "ssssssssssssssssssss", Toast.LENGTH_SHORT).show()
               // Intent(context,MainActivity::class.java)



            }

        }

    }

    override fun getItemCount(): Int {
        return grgtic.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): pager2holder {

        val view1 =
            LayoutInflater.from(parent.context).inflate(R.layout.g_card, parent, false)
        return pager2holder(view1)

    }

    class pager2holder(itemv: View) : RecyclerView.ViewHolder(itemv) {


        val itemv_name = itemv.findViewById(R.id.tv_g_1) as TextView

        val itemv_img = itemv.findViewById(R.id.iv_g_img) as ImageView

    }


}