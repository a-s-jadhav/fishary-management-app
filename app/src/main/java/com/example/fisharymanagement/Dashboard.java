package com.example.fisharymanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
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
    SharedPreferences sp;
    MeowBottomNavigation bottomNavigation;


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
        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_baseline_phone_android_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_home_black_24dp));
        bottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_dashboard_black_24dp));

        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
                Fragment fragment = null;
                //Condition check
                switch (item.getId()){
                    case 1:
                        //phonedemo
                        fragment = new PhonedemoFragment();
                        break;
                    case 2:
                        //homedemo
                        fragment = new HomeDemoFragment();
                        break;
                    case 3:
                        //dash but temo home
                        fragment = new EditProfileClass();
                        break;

                }
                //Load fragment
                loadFragment(fragment);
            }
        });
        //set notifaction count
        bottomNavigation.setCount(1,"10");
        bottomNavigation.show(2,true);
        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(),"You Clicked "+ item.getId(),Toast.LENGTH_SHORT).show();
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //display toast
                Toast.makeText(getApplicationContext(),"You reseleec"+item.getId(),Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void loadFragment(Fragment fragment){
        //replace fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentcontainer,fragment)
                .commit();

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
            case R.id.getStock:
                Toast.makeText(getApplicationContext(),"Stocks",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),activity_get_stock.class));
            default :
                return super.onOptionsItemSelected(item);
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
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    //to add menu for dashboard
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboardmenu,menu);
        return true;
    }
    //Need to remove this after the confirmation

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

 */
}