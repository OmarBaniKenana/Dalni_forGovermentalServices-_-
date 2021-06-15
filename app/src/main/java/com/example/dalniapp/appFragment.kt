package com.example.dalniapp


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_app.*


class appFragment(img1:String ,imgur:String): Fragment() {

    var img:String = img1 as String
    var imgur:String = imgur as String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tv_app_1.text =imgur

        Glide.with(this)
            .load(img)
            .error(R.drawable.ic_search)
            .into(imageView)

        imageView2.setOnClickListener {
            val i = Intent(  Intent.ACTION_VIEW, Uri.parse(imgur))
            startActivity(i)
        }

    }

}