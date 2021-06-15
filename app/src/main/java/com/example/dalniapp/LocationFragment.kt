package com.example.dalniapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_location.view.*


class LocationFragment(img1:String ,imgur1:String ,imgur:String) : Fragment(), OnMapReadyCallback {


        var i1 = img1.toDouble()
        var i2 = imgur1.toDouble()
        var i_url = imgur.toString()

    private lateinit var mMap: GoogleMap




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_location, container, false)

     //  val x =  findViewById(R.id. bu2_map) as Button

        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment

        val xx = view.findViewById<Button>(R.id. bu2_map)

 xx.setOnClickListener {
    //
     val i = Intent(  Intent.ACTION_VIEW, Uri.parse(i_url))
         i.setPackage("com.google.android.apps.maps")
        startActivity(i)

 }
        //sendUserToGoogleMap()
     //   val i = Intent(  Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.gam_municipality"))
     //   startActivity(i)
 mapFragment.getMapAsync(this)
     
 return view

}
  /* private fun sendUserToGoogleMap() {


        val i = Intent(  Intent.ACTION_VIEW, Uri.parse( "https://play.google.com/store/apps/details?id=com.gam_municipality"))
       context?.startActivity(i)
    }*/


    override fun onMapReady(googleMap: GoogleMap) {
 mMap = googleMap

 // Add a marker in Sydney and move the camera
 val sydney = LatLng(i1, i2)
 mMap.addMarker(MarkerOptions().position(sydney).title("Marker"))
 mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15f))
}
}