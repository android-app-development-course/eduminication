package com.eduminication

import android.Manifest
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import cn.bmob.v3.Bmob
import com.eduminication.databinding.ActivityMainBinding
import com.eduminication.utils.BombId
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import pub.devrel.easypermissions.EasyPermissions

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet) =
        ActivityMainBinding.inflate(layoutInflater).root

    override fun onStart() {
        super.onStart()

        EasyPermissions.requestPermissions(this, "", 0, Manifest.permission.INTERNET)

        Bmob.initialize(this, BombId)
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

        avatar.setOnClickListener {
            navController.navigate(GlobalNavigationDirections.actionGlobalNavLogin())
            drawer_layout.closeDrawer(navigation_view)
        }
    }
}
