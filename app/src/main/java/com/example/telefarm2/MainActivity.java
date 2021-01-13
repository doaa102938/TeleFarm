package com.example.telefarm2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    //view
    private FragmentContainerView  nav;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;


@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find view
       nav=findViewById(R.id.nav_host_fragment);
       bottomNavigationView=findViewById(R.id.bottom_navigation);
       navController= Navigation.findNavController(this,R.id.nav_host_fragment);


       bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

          switch (item.getItemId()){
              case R.id.nav_home :
                 navController.navigate(R.id.homeFragment);
                  return true;
                  case R.id.nav_sreach :
                 navController.navigate(R.id.searchFragment);
                  return true;
                  case R.id.nav_shopping_cart :
                  navController.navigate(R.id.shoppingCartFragment);

                  return true;
                  case R.id.nav_favorite :
                  navController.navigate(R.id.favoriteFragment);
                  return true;
                  case R.id.nav_profile :
                  navController.navigate(R.id.profileFragment);
                  return true;



          }

           return false;
       });
    }
}