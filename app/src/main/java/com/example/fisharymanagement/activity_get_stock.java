
package com.example.fisharymanagement;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class activity_get_stock extends AppCompatActivity {

   private RecyclerView mRecyclerView;
   private getStockAdapter mAdapter;
   private Context context;
   private Button btnBackGetStock;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_stock);
        btnBackGetStock = (Button)findViewById(R.id.btnBackGetStock);
        ArrayList<FishDetails> FishDetailsList = new ArrayList<>();
        FishDetailsList.add(new FishDetails("Fish_first",400,800,400,"madanlal"));
        FishDetailsList.add(new FishDetails("Fish_second",500,700,200,"Madanlal"));
        FishDetailsList.add(new FishDetails("Fish_third",450,750,200,"Fishy Babu"));
        FishDetailsList.add(new FishDetails("Fish_fourth",400,600,200,"Fishy Babu"));
        FishDetailsList.add(new FishDetails("Fish_5",400,1000,600,"Radhe Bhai"));
        FishDetailsList.add(new FishDetails("Fish_6",400,500,100,"Radhe bhai"));
        FishDetailsList.add(new FishDetails("Fish_7",300,700,400,"Chiny Chacha"));
        FishDetailsList.add(new FishDetails("Fish_8",300,600,300,"Chiny chacha"));
        FishDetailsList.add(new FishDetails("Fish_9",200,400,200,"Elon Musk"));
        FishDetailsList.add(new FishDetails("Fish_10",100,1000,900,"Elon Musk"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManger = new LinearLayoutManager(this);

        mAdapter = new getStockAdapter(FishDetailsList,context);
        //mAdapter = new getStockAdapter(FishDetailsList, context);

        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);

        btnBackGetStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_get_stock.this,MainActivity.class));
                finish();
            }
        });

    }


}