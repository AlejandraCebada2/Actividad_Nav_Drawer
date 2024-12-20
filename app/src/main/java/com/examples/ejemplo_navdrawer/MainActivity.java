package com.examples.ejemplo_navdrawer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            // Obtén el nombre de usuario
            String username = getIntent().getStringExtra("USERNAME");

            // Crea el fragmento y pasa el nombre de usuario
            homeFragment fragment = new homeFragment();
            Bundle args = new Bundle();
            args.putString("USERNAME", username); // Pasa el nombre de usuario
            fragment.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int val = item.getItemId();
        if (val == R.id.nav_home) {
            homeFragment fragment = new homeFragment();
            // Si es homeFragment, también pasa el nombre de usuario
            String username = getIntent().getStringExtra("USERNAME");
            Bundle args = new Bundle();
            args.putString("USERNAME", username);
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        } else if (val == R.id.nav_setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NotesFragment()).commit();
        } else if (val == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BMIFeedbackFragment()).commit();
        } else if (val == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new WeatherFragment()).commit();
        } else if (val == R.id.nav_bmi) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BMIFragment()).commit();
        } else if (val == R.id.nav_admin) { // Nuevo caso para AdminActivity
            Intent intent = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(intent);
        } else if (val == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class); // Cambiar a WelcomeActivity
            startActivity(intent);
            finish(); // Cierra MainActivity
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
