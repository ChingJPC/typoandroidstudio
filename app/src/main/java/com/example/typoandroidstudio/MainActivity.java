package com.example.typoandroidstudio;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.typoandroidstudio.agendamiento.AgendamientoActivity;
import com.example.typoandroidstudio.home.HomeFragmentActivity;
import com.example.typoandroidstudio.infomascota.AddMascotaActivity;
import com.example.typoandroidstudio.infomascota.IndexMascotaFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class MainActivity extends AppCompatActivity  {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fab);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        View linear = navigationView.getHeaderView(0);

        navigationView.bringToFront();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId()==R.id.nav_perfil) {
                    Intent intent = new Intent(MainActivity.this, PerfilUserActivity.class);
                    startActivity(intent);

                } else if (menuItem.getItemId()==R.id.nav_settings){
                    replaceFragment(new IndexMascotaFragment());

                }
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });



        TextView txtUser = linear.findViewById(R.id.user);
        String fullName = Datainfo.restLogin.getUser().getName() + " " +
                Datainfo.restLogin.getUser().getApellido();
        txtUser.setText(fullName);

        TextView txtEmail = linear.findViewById(R.id.email);
        txtEmail.setText(Datainfo.restLogin.getUser().getEmail());


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout,
                toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragmentActivity()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }



        bottomNavigationView.setBackground(null);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            // Funciones de la barra
            if (itemId == R.id.home) {
                replaceFragment(new HomeFragmentActivity());
            } else if (itemId == R.id.mascotas) {
                replaceFragment(new IndexMascotaFragment());

                // Funciones boton mas
            } else if (itemId == R.id.pets) {
                Intent intent = new Intent(MainActivity.this, AddMascotaActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.agendamiendo) {
                Intent intent = new Intent(MainActivity.this, AgendamientoActivity.class);
                startActivity(intent);
            }
            return true;
        });




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showBottomDialog();
            }
        });
    }
    private  void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void showBottomDialog() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bottomsheetlayout);

        LinearLayout pets = dialog.findViewById(R.id.pets);
        LinearLayout shortsLayout = dialog.findViewById(R.id.agendamiendo);
        LinearLayout liveLayout = dialog.findViewById(R.id.cooming);
        ImageView cancelButton = dialog.findViewById(R.id.cancelButton);



        pets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, AddMascotaActivity.class);
                startActivity(intent);

            }
        });

            //Agendamiento
        shortsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, AgendamientoActivity.class);
                startActivity(intent);

            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
                Toast.makeText(MainActivity.this,"¡Create successful!",Toast.LENGTH_SHORT).show();

            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);

    }

}