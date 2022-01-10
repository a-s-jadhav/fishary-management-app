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

public class DealerActivity extends AppCompatActivity {
    private RecyclerView mDealRecyclerView;
    private DealerAdapter mDealAdapter;
    private Context context;
    private Button btnBackDealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer);
        btnBackDealer = findViewById(R.id.btnBackdealer);

        ArrayList<DealerDetails> DealerDetailsList = new ArrayList<>();
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));
        DealerDetailsList.add(new DealerDetails("Elon Musk","9921541083"));



        mDealRecyclerView = findViewById(R.id.recyclerViewDealer);
        mDealRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(this);

        mDealAdapter = new DealerAdapter(DealerDetailsList,context);


        mDealRecyclerView.setLayoutManager(mLayoutManger);
        mDealRecyclerView.setAdapter(mDealAdapter);

        btnBackDealer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DealerActivity.this,Dashboard.class));
                finish();
            }
        });
    }
}