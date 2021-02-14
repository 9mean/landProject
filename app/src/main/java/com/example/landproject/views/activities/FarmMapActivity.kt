package com.example.landproject.views.activities

import android.Manifest
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.landproject.repositories.retrofits.MapApiConst.CLIENT_ID
import com.example.landproject.databinding.ActivityFarmMapBinding
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.LocationOverlay
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource

class FarmMapActivity : AppCompatActivity(), OnMapReadyCallback {
    val PERMISSIONS_REQUEST_CODE = 100
    lateinit var binding:ActivityFarmMapBinding
    private lateinit var marker : Marker
    var REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)
    private lateinit var mapView: MapView
    private lateinit var naverMap: NaverMap
    private lateinit var uiSettings: UiSettings
    private lateinit var locationSource: FusedLocationSource
    private lateinit var locationOverlay: LocationOverlay
    private lateinit var cameraUpdate: CameraUpdate

    var lat:Double=37.5386169
    var lon:Double=127.082375

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityFarmMapBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NaverMapSdk.getInstance(this).client = NaverMapSdk.NaverCloudPlatformClient(CLIENT_ID)
        mapView=binding.mapView
        mapView.getMapAsync(this)
        locationSource = FusedLocationSource(this, PERMISSIONS_REQUEST_CODE)
    }
    override fun onStart() {
        super.onStart()
        mapView.onStart()
        Log.d("TAG","onStart")
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        Log.d("TAG","onResume")
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        Log.d("TAG","onPause")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
        Log.d("TAG","onDestroy")
    }
    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }
    override fun onMapReady(p0: NaverMap) {
        Log.d("TAG","onMapReady")
        naverMap=p0
        uiSettings=naverMap.uiSettings
        uiSettings.isLocationButtonEnabled = true
        naverMap.locationSource = locationSource
        locationOverlay = naverMap.locationOverlay
        //불러온 위치로 카메라설정
        cameraUpdate = CameraUpdate.scrollTo(LatLng(lat, lon))
                .animate(CameraAnimation.Easing)
        naverMap.moveCamera(cameraUpdate)
        marker = Marker()
        marker.captionColor = Color.BLUE
        marker.captionText = "선택위치"

        marker.position = LatLng(lat, lon)
        marker.map = naverMap
        naverMap.addOnLocationChangeListener { location ->
            Log.d("TAG","${location.latitude}, ${location.longitude}")
        }
        naverMap.setOnMapClickListener { pointF, latLng ->
            marker.position=LatLng(latLng.latitude,latLng.longitude )
            //불러온 위치로 카메라설정
            cameraUpdate = CameraUpdate.scrollTo(LatLng(latLng.latitude, latLng.longitude))
                .animate(CameraAnimation.Easing)
            naverMap.moveCamera(cameraUpdate)
            //map_location_btn.text=
            Log.d("TAG","${latLng.latitude}, ${latLng.longitude}")
        }

    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated) { // 권한 거부됨
                naverMap.locationTrackingMode = LocationTrackingMode.None
            }
            return
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}