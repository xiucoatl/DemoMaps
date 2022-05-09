package com.example.demomaps

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var map:GoogleMap

    companion object{
        const val REQUEST_CODE_LOCATION=0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createFragment()
    }

    private fun enableLocation(){
        if(!::map.isInitialized)return
        if(isLocationPermissionGranted())
        {
            map.isMyLocationEnabled=true
        }else{
            requestLocationPermission()
        }
    }


    private fun createFragment(){
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        //fragment listo  para cargar
        map = googleMap
        findViewById<ImageButton>(R.id.Rest1).setOnClickListener {
            //, 19.041656492778053, -98.197651
            crearMarker(19.040946441076535, -98.19765906201728,"casa barroca")
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(19.040946441076535, -98.19765906201728), 18f),
                4000,
                null
            )
        }
        findViewById<ImageButton>(R.id.Rest2).setOnClickListener {

            crearMarker(19.045883375004205, -98.19410366931548,"anafre rojo")
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(19.045883375004205, -98.19410366931548), 18f),
                4000,
                null
            )
        }
        findViewById<ImageButton>(R.id.Rest3).setOnClickListener {

            crearMarker(19.0440451617472, -98.19731673800491,"vittorios")
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(19.0440451617472, -98.19731673800491), 18f),
                4000,
                null
            )
        }
        findViewById<ImageButton>(R.id.rest4).setOnClickListener {

            crearMarker(19.042028622208633, -98.19619743082387,"la casa del mendrugo")
            map.animateCamera(
                CameraUpdateFactory.newLatLngZoom(LatLng(19.042028622208633, -98.19619743082387), 18f),
                4000,
                null
            )
        }
        //crearMarker()
        enableLocation()
    }

    private fun crearMarker(lat:Double,long: Double,name: String){
        map.clear()
        val coordenadas = LatLng(lat, long)
        val marker = MarkerOptions().position(coordenadas).title(name)
        map.addMarker(marker)


    }

    private fun isLocationPermissionGranted()=
        ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED

    private fun requestLocationPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
            //mostarar la ventana de permisos

        }else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE_LOCATION)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            REQUEST_CODE_LOCATION->if(grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                map.isMyLocationEnabled=true

            }else{
                Toast.makeText(this, "para activat permiso ve a ajustes", Toast.LENGTH_LONG).show()
            }
            else ->{}
        }
    }


}