package com.example.dalniapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_search.*
import java.text.SimpleDateFormat
import java.util.*

class myadapter(options: FirebaseRecyclerOptions<model>, val context: Context) :
    FirebaseRecyclerAdapter<model, myadapter.myviewholder>(options) {


    val date_1 = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")


    override fun onBindViewHolder(holder: myviewholder, position: Int, model: model) {

        var v_idd = model.GovrId

        if (v_idd == "nullxxx") {
            var x = "hhhhhhhhhhhhh"
            holder.GovrId2.text = x
        } else {


            val x = v_idd?.let {
                FirebaseDatabase.getInstance().reference.child("Government").child(it)
                    .addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            val k = snapshot.child("name").value.toString()
                            val k2 = snapshot.child("appImg").value.toString()
                            holder.GovrId2.text = k



                            Glide.with(context)
                                .load(k2)
                                .error(R.drawable.ic_search)
                                .into(holder.idService4)


                        }

                        override fun onCancelled(error: DatabaseError) {

                        }


                    })
            }



        }

        var v_id = getRef(position).key

        holder.name.text = model.name
        holder.GovrId.text = v_idd

        holder.idService2.text = v_id.toString()


        holder.itemView.setOnClickListener {
            Log.d(v_id, "sssssssssssss")



            val user = FirebaseAuth.getInstance().currentUser
            //val x = edt1.text.toString()
            if (user==null) {

                Toast.makeText(context, "no user", Toast.LENGTH_SHORT).show()
            }else{
                val date = Date()
                // println(datex.format(date))

                var m1 = History_tict(model.name,date_1.format(date).toString())

                val x = FirebaseDatabase.getInstance().reference
                x.child("history").child(user?.uid.toString()).push().setValue(m1)
                //  x.push().setValue("edt3.text")


            }

            val i = Intent(context, TabbedActivity::class.java)
            i.putExtra("digov", holder.GovrId.text)
            i.putExtra("diser", holder.idService2.text)
            context.startActivity(i)




        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return myviewholder(view)
    }

    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var name = itemView.findViewById(R.id.nametext) as TextView
        var GovrId = itemView.findViewById(R.id.coursetext) as TextView
        var GovrId2 = itemView.findViewById(R.id.coursetext3) as TextView
        var idService2 = itemView.findViewById(R.id.coursetext2) as TextView
        var idService4 = itemView.findViewById(R.id.coursetext4) as ImageView

    }


}


