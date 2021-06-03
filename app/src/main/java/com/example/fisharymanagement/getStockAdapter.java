package com.example.fisharymanagement;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class getStockAdapter extends RecyclerView.Adapter<com.example.fisharymanagement.getStockAdapter.ViewHolder> {

    private ArrayList<FishDetails> mFishList;
    private final Context context;
    TextInputEditText deadQuantityTextView;
    TextInputEditText deadReasonTextView;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvFishName;
        public TextView tvQuantity;
        public int tvTotalPur;
        public int tvSoldQuantity;
        public String tvDealerName;
        private Context context;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            context = view.getContext();
            tvFishName = (TextView) view.findViewById(R.id.tvFishName);
            tvQuantity = (TextView) view.findViewById(R.id.tvQuantity);

            //after clicking the button dead on the get stock screen on any next card in the recyclerView
            view.findViewById(R.id.btnDead).setOnClickListener(new View.OnClickListener() {
                @SuppressLint("ShowToast")
                @Override
                public void onClick(View v) {
                    //defination for the dynamic dialog box
                    AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());
                    View view=LayoutInflater.from(context).inflate(R.layout.dead_card_dialog,null);
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
            view.findViewById(R.id.btnSold).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(),detail_stock.class);
                    Bundle extras = new Bundle();
                    extras.putString("FishName", tvFishName.getText().toString() );
                    extras.putString("Quantity", tvQuantity.getText().toString());
                    extras.putInt("tvTotalPur",tvTotalPur);
                    extras.putInt("SoldQuantity",tvSoldQuantity);
                    extras.putString("DealerName",tvDealerName);
                    intent.putExtras(extras);
                    v.getContext().startActivity(intent);
                }
            });
        }

    }


    public getStockAdapter(ArrayList<FishDetails> FishDetailsList, Context context) {
        mFishList = FishDetailsList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.getstock_card, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        FishDetails currentFish = mFishList.get(position);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvFishName.setText(currentFish.getMtvFishName());
        viewHolder.tvQuantity.setText(currentFish.getMtvQuantity()+" KG");
        viewHolder.tvTotalPur = (currentFish.getMtvTotalPur());
        viewHolder.tvSoldQuantity = (currentFish.getMtvSoldQuantity());
        viewHolder.tvDealerName = (currentFish.getMtvDealerName());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return  mFishList.size();
    }

}
