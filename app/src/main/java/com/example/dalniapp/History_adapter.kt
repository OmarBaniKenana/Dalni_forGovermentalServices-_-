package com.example.dalniapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions

class History_adapter (xx: FirebaseRecyclerOptions<History_tict>, val context: Context) :
    FirebaseRecyclerAdapter<History_tict, History_adapter.myviewholder>(xx)  {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_card, parent, false)
        return myviewholder(view)
    }

    override fun onBindViewHolder(holder: myviewholder, position: Int, model: History_tict) {
        holder.name3.text = model.name
        holder.date1.text = model.date

holder.itemView.setOnClickListener {
    var intent: Intent = Intent(context, SearchActivity::class.java)
    //  var bundle = Bundle()

    intent.putExtra("searchString",  model.name.toString())
    context.startActivity(intent)

}
    }


    fun deleteItem(position:Int) {

        snapshots.getSnapshot(position).ref.removeValue()


    }


    class myviewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name3 = itemView.findViewById(R.id.txt1_his) as TextView
        var date1 = itemView.findViewById(R.id.txt2_his) as TextView

    }

}

