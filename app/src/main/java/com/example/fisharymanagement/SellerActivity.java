package com.example.fisharymanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SellerActivity extends AppCompatActivity {
    private RecyclerView mSellRecyclerView;
    private SellerAdapter mSellAdapter;
    private Context context;
    private Button btnBackSeller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        btnBackSeller = findViewById(R.id.btnBackSeller);

        ArrayList<SellerDetails> SellerDetailsList = new ArrayList<>();
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));
        SellerDetailsList.add(new SellerDetails("Madanlal","9730512714"));



        mSellRecyclerView = findViewById(R.id.recyclerViewSeller);
        mSellRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(this);

        mSellAdapter = new SellerAdapter(SellerDetailsList,context);


        mSellRecyclerView.setLayoutManager(mLayoutManger);
        mSellRecyclerView.setAdapter(mSellAdapter);

        btnBackSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SellerActivity.this,Dashboard.class));
                finish();
            }
        });

    }
}