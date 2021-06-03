package com.example.fisharymanagement;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class detail_stock extends AppCompatActivity {
    private TextView txtProgress, tvFishName, tvTotalPur , tvSoldQuantity , tvDealerName;
    private Button btnDead,btnBackDetails;
    private ProgressBar progressBar;
    private int pStatus = 0 , percentage = 0;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stock);

        txtProgress = (TextView) findViewById(R.id.txtProgress);
        tvFishName = (TextView) findViewById(R.id.tvFishName);
        tvTotalPur = (TextView) findViewById(R.id.tvTotalPur);
        tvSoldQuantity = (TextView) findViewById(R.id.tvSoldQuantity);
        tvDealerName = (TextView) findViewById(R.id.tvDealerName);
        btnDead = (Button) findViewById(R.id.btnDeadDetails);
        btnBackDetails = (Button)findViewById(R.id.btnBackDetails);
        progressBar = (ProgressBar) findViewById(R.id.ProgressBar);
        Bundle extras = getIntent().getExtras();
        tvFishName.setText(extras.getString("FishName"));
        tvTotalPur.setText(extras.getInt("tvTotalPur")+" KG");
        tvSoldQuantity.setText(extras.getInt("SoldQuantity")+" KG");
        tvDealerName.setText(extras.getString("DealerName"));
        percentage = (((extras.getInt("tvTotalPur")-extras.getInt("SoldQuantity"))*100)/extras.getInt("tvTotalPur"));

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pStatus <= percentage) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(pStatus);
                            txtProgress.setText(pStatus + " %");
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    pStatus++;
                }
            }
        }).start();

        btnBackDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(detail_stock.this,activity_get_stock.class));
                finish();
            }
        });

        btnDead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                Context context = v.getContext();
                View view= LayoutInflater.from(context).inflate(R.layout.dead_card_dialog,null);
                TextInputEditText deadQuantityTextView = view.findViewById(R.id.deadQuantity);
                TextInputEditText deadReasonTextView  = view.findViewById(R.id.deadReason);
                builder.setView(view);

                //code for the positive button
                builder.setPositiveButton("Dead", (dialog, which) -> {
                    if ((TextUtils.isEmpty(deadQuantityTextView.getText())) && (TextUtils.isEmpty(deadReasonTextView.getText()))) {
                        Toast.makeText(context,"Complete the formality", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(context,deadQuantityTextView.getText().toString() + "KG is Dead because of" + deadReasonTextView.getText().toString(),Toast.LENGTH_LONG);
                    }
                });
                //code for the negative button
                builder.setNegativeButton("back", (dialog, which) -> dialog.dismiss());

                //output line for the calling
                final AlertDialog alertDialog=builder.create();
                alertDialog.show();

                //designing for buttons
                //positive
                Button button_positive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                button_positive.setBackgroundColor(Color.GRAY);
                button_positive.setPadding(20, 5, 20, 5);
                button_positive.setTextColor(Color.BLACK);
                //negative
                Button button_negative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                button_negative.setBackgroundColor(Color.GRAY);
                button_negative.setPadding(20, 5, 20, 5);
                button_negative.setTextColor(Color.BLACK);
            }


        });
    }
}