package com.eduminication

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import cn.bmob.v3.Bmob
import com.eduminication.databinding.ActivityMainBinding
import com.eduminication.utils.BombId
import com.koushikdutta.ion.Ion

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        Bmob.initialize(this, BombId)
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(
            binding.toolbar,
            navController,
            AppBarConfiguration(navController.graph, binding.drawerLayout)
        )

        //to customize click listener but also preserve the nav graph jump
        binding.navigationView.setNavigationItemSelectedListener { item ->
            val handled: Boolean

            when (item.itemId) {
                R.id.nav_chat -> {
                    val builder = NavOptions.Builder()
                        .setLaunchSingleTop(true)
                        .setEnterAnim(R.anim.nav_default_enter_anim)
                        .setExitAnim(R.anim.nav_default_exit_anim)
                        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
                    if (item.order and Menu.CATEGORY_SECONDARY == 0)
                        builder.setPopUpTo(navController.graph.startDestination, false)
                    val options = builder.build()
                    handled = try {
                        val bundle = Bundle()
                        //pass the data
                        bundle.putString("user", "BlurringShadow")
                        navController.navigate(item.itemId, bundle, options)
                        true
                    } catch (e: IllegalArgumentException) {
                        false
                    }
                }

                else -> handled = NavigationUI.onNavDestinationSelected(item, navController)
            }
            if (handled) binding.drawerLayout.closeDrawer(binding.navigationView)

            handled
        }

        var headview = binding.navigationView.inflateHeaderView(R.layout.nav_header_main)
        var head_image = headview.findViewById<ImageView>(R.id.avatar)
        head_image.setOnClickListener {
            navController.navigate(R.id.nav_login)
        }

        Ion.getDefault(this).configure().setLogging("diary-ion", Log.DEBUG)
    }
}
