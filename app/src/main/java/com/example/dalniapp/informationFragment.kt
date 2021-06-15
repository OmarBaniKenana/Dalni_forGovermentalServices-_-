package com.example.dalniapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_app.*
import kotlinx.android.synthetic.main.fragment_information.*

class informationFragment(k5: String, k6: String, k7: String, k8: String) : Fragment() {

    var img1: String = k5
    var name1: String = k6
    var ids: String = k7
    var idg: String = k8

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FirebaseDatabase.getInstance().reference.child("TraGover").child(ids)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val s = snapshot.child("name").value.toString()
                    //  val s2: String = snapshot.child("FinFees").value.toString()

                    info_tv_2.text = s



                    val s1 = snapshot.child("requirements").childrenCount
                    for (ii in 1..s1) {

                        val sum: String =
                            snapshot.child("requirements").child(ii.toString()).value.toString()



                        if (ii.toInt()==1){
                            info_tv_3.text =
                                ii.toString().plus(info_tv_3.text.toString()).plus(sum).plus("\n")
                        }else {
                            info_tv_3.text = info_tv_3.text.toString().plus(ii).plus(".").plus(sum).plus("\n")
                        }


                    }


                        /////////////////////////////////////////////////////////////////


                    val s2 = snapshot.child("procedures").childrenCount
                    for (ii in 1..s2) {

                        val sum2: String = snapshot.child("procedures").child(ii.toString()).value.toString()


                        if (ii.toInt()==1){
                            info_tv_4.text =
                                ii.toString().plus(info_tv_4.text.toString()).plus(sum2).plus("\n")
                        }else {
                            info_tv_4.text = info_tv_4.text.toString().plus(ii).plus(".").plus(sum2).plus("\n")
                        }

                    }
                    ///////////////////////////////////////////////////////////////////

                        val s3 = snapshot.child("FinFees").childrenCount
                        for (ii in 1..s3) {


                        val sum3: String = snapshot.child("FinFees").child(ii.toString()).value.toString()
                     if (ii.toInt()==1){
                         info_tv_5.text =
                             ii.toString().plus(info_tv_5.text.toString()).plus(sum3).plus("\n")
                     }else {
                         info_tv_5.text = info_tv_5.text.toString().plus(ii).plus(".").plus(sum3).plus("\n")
                     }
                    }

                    /*   for (ii in 1..s2){
                           println(ii)
                           println("$ii/n$ii")
                           println(ii.toString().plus("\n").plus("ss"))
                           println("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
                       }
                       //val s3 =snapshot.child("FineFees").value.toString()
                       //val s4 =snapshot.child("FineFees").value.toString()
                       //val s5 =snapshot.child("FineFees").value.toString()*/
                   // info_tv_4.text = s2.toString()
                   // info_tv_5.text = s2.toString()
                }

                override fun onCancelled(error: DatabaseError) {
                }
            })



        Glide.with(this)
            .load(img1)
            .error(R.drawable.ic_search)
            .into(info_iv_1)
        info_tv_1.text = name1


    }


}