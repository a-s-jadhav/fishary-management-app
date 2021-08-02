package com.example.fisharymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

/*
Firebase is On
Project Email : teamlastyearproject2022@gmail.com
Pass : project@2022
link of the firebase database : https://fishary-management-default-rtdb.asia-southeast1.firebasedatabase.app/
Database type : Realtime Database
Database design not yet done, we will complete it soon
 */

public class Dashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.maindashboard);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_draw_open1,R.string.navigation_draw_close1);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //by pressing ctrl+o


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings :
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new SettingsClass()).commit();
                break;
            case R.id.editprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentcontainer, new EditProfileClass()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {        //for navigation drawer for profile
       if(drawerLayout.isDrawerOpen(GravityCompat.START)){
           drawerLayout.closeDrawer(GravityCompat.START);
       }
       else {
           super.onBackPressed();
       }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    //to add menu for dashboard
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboardmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  //for menu item
       switch(item.getItemId()){
           case R.id.dealer :
               Toast.makeText(getApplicationContext(),"dealer",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),DealerActivity.class));
               return true;

           case R.id.seller :
               Toast.makeText(getApplicationContext(),"seller",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),SellerActivity.class));
               return true;
           case R.id.purchase :
               Toast.makeText(getApplicationContext(),"purchase",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),PurchaseActivity.class));
               return true;
           case R.id.amountpay :
               Toast.makeText(getApplicationContext(),"amount need to pay",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),PayAmountActivity.class));
               return true;
           case R.id.amountreceive :
               Toast.makeText(getApplicationContext(),"amount need to receive",Toast.LENGTH_SHORT).show();
               startActivity(new Intent(getApplicationContext(),ReceiveAmountActivity.class));
               return true;
           default :
               return super.onOptionsItemSelected(item);
       }

    }
}