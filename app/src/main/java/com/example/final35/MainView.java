package com.example.final35;

import android.app.Fragment;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.final35.ui.dashboard.DashboardFragment;
import com.example.final35.ui.home.HomeFragment;
import com.example.final35.ui.notifications.NotificationsFragment;
import com.example.final35.ui.personinfo.PersonInfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainView extends AppCompatActivity {
    final HomeFragment fragment1 = new HomeFragment();
    final DashboardFragment fragment2 = new DashboardFragment();
    final NotificationsFragment fragment3 = new NotificationsFragment();
    final PersonInfoFragment fragment4 =new PersonInfoFragment();
    final FragmentManager fm = getSupportFragmentManager();
    private Fragment activty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_person)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fm.beginTransaction().add(R.id.nav_host_fragment,fragment4,"4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.nav_host_fragment,fragment1, "1").commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(fragment2).hide(fragment3).hide(fragment4).
                            show(fragment1).commit();
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(fragment1).hide(fragment3).hide(fragment4)
                            .show(fragment2).commit();
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(fragment1).hide(fragment2).hide(fragment4)
                            .show(fragment3).commit();
                    return true;
                case R.id.navigation_person:
                    fm.beginTransaction().hide(fragment1).hide(fragment2).hide(fragment3)
                            .show(fragment4).commit();
                    return true;
            }
            return false;
        }
    };
}
