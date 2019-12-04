package com.eduminication;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.ViewParent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.NavGraph;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.koushikdutta.ion.Ion;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    public SharedViewModel sharedViewModel = new SharedViewModel();

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_resource, R.id.nav_notification,
                R.id.nav_chat, R.id.nav_setting, R.id.nav_data)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


        //set navigation view call back with nav graph
        NavigationUI.setupWithNavController(navigationView, navController);


        //to customize click listener but also preserve the nav graph jump
        navigationView.setNavigationItemSelectedListener(item -> {
            boolean handled;

            switch (item.getItemId()) {
                case R.id.nav_chat: {
                    NavOptions.Builder builder = new NavOptions.Builder()
                            .setLaunchSingleTop(true)
                            .setEnterAnim(R.anim.nav_default_enter_anim)
                            .setExitAnim(R.anim.nav_default_exit_anim)
                            .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
                            .setPopExitAnim(R.anim.nav_default_pop_exit_anim);
                    if ((item.getOrder() & Menu.CATEGORY_SECONDARY) == 0) {
                        try {
                            //noinspection JavaReflectionMemberAccess
                            builder.setPopUpTo(
                                    ((NavDestination) NavigationUI.class
                                            .getMethod("findStartDestination", NavGraph.class)
                                            .invoke(null, navController.getGraph())).getId(),
                                    false);
                        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }
                    NavOptions options = builder.build();
                    try {
                        Bundle bundle = new Bundle();
                        //pass the data
                        bundle.putString("user", "BlurringShadow");
                        navController.navigate(item.getItemId(), bundle, options);
                        handled = true;
                    } catch (IllegalArgumentException e) {
                        handled = false;
                    }
                }
                break;

                default: {
                    handled = NavigationUI.onNavDestinationSelected(item, navController);
                }
                break;

            }
            if (handled) {
                ViewParent parent = navigationView.getParent();
                if (parent instanceof DrawerLayout)
                    ((DrawerLayout) parent).closeDrawer(navigationView);
                else
                    try {
                        @SuppressWarnings("JavaReflectionMemberAccess")
                        BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) NavigationUI.class
                                .getMethod("findBottomSheetBehavior", View.class).invoke(null, navigationView);
                        if (bottomSheetBehavior != null)
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }

            return handled;

        });

        Ion.getDefault(this).
                configure().
                setLogging("ion-sample", Log.DEBUG);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
