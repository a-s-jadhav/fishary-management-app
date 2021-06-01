package com.example.fisharymanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnDashBoard = (Button)findViewById(R.id.btnDashBoard);
        Button btnDealer = (Button)findViewById(R.id.btnDealer);
        Button btnGetStock = (Button)findViewById(R.id.btnGetStock);
        Button btnSeller = (Button)findViewById(R.id.btnSeller);
        Button btnPurchase = (Button)findViewById(R.id.btnPurchase);
        Button btnLogOut = (Button)findViewById(R.id.btnLogOut);

        btnGetStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast. makeText(getApplicationContext(),"Work in prograss Lets do it",Toast. LENGTH_SHORT);
                toast. setMargin(50,50);
                toast. show();

            }
        });
    }
}