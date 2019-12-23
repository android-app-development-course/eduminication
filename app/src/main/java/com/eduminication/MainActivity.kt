package com.eduminication

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import cn.bmob.v3.Bmob
import com.eduminication.databinding.ActivityMainBinding
import com.eduminication.utils.BmobId
import com.eduminication.viewmodel.SharedViewModel
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {
    val sharedViewModel= SharedViewModel()

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        val headerView = navigation_view.inflateHeaderView(R.layout.nav_header_main)

        EasyPermissions.requestPermissions(this, "", 0, Manifest.permission.INTERNET)

        Bmob.initialize(this, BmobId)

        Ion.getDefault(this).configure().setLogging("diary-ion", Log.DEBUG)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(
            toolbar,
            navController,
            AppBarConfiguration(navController.graph, drawer_layout)
        )

        //to customize click listener but also preserve the nav graph jump
        navigation_view.setNavigationItemSelectedListener { item ->
            val handled: Boolean = when (item.itemId) {
                R.id.nav_chat -> {
                    navController.navigate(GlobalNavigationDirections.actionGlobalNavChat())
                    true
                }
                else -> NavigationUI.onNavDestinationSelected(item, navController)
            }

            if (handled) drawer_layout.closeDrawer(navigation_view)
            handled
        }

        headerView.findViewById<View>(R.id.avatar).setOnClickListener {
            navController.navigate(GlobalNavigationDirections.actionGlobalNavLogin())
            drawer_layout.closeDrawer(navigation_view)
        }
    }
}
