package com.example.fisharymanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.textfield.TextInputEditText;

import java.text.BreakIterator;
import java.util.ArrayList;

public class DealerAdapter extends RecyclerView.Adapter<DealerAdapter.MyViewHolder> {
    private ArrayList<DealerDetails> mDealerList;
    private final Context context;


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class MyViewHolder extends RecyclerView.ViewHolder {



        static TextView tvDealerNameHere;
        static TextView tvDealerPhone;
        private Context context;

        public MyViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            context = view.getContext();
            tvDealerNameHere = (TextView) view.findViewById(R.id.tvDealerNameHere);
            tvDealerPhone = (TextView) view.findViewById(R.id.tvDealerPhone);
        }

    }


    public DealerAdapter(ArrayList<DealerDetails> DealerDetailsList, Context context) {
        mDealerList = DealerDetailsList;
        this.context = context;
    }
    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.dealer_card, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DealerDetails currentDealer = mDealerList.get(position);

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        MyViewHolder.tvDealerNameHere.setText(currentDealer.getMtvDealerNameHere());
        MyViewHolder.tvDealerPhone.setText("+91 "+ currentDealer.getMtvDealerPhone());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {return  mDealerList.size();
    }

}
