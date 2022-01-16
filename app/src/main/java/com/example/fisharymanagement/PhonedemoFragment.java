package com.example.fisharymanagement;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PhonedemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PhonedemoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    // Create the object of TextView and PieChart class
    TextView tvR, tvPython, tvCPP, tvJava;
    PieChart pieChart;

    private View rootView;


    public PhonedemoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PhonedemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PhonedemoFragment newInstance(String param1, String param2) {
        PhonedemoFragment fragment = new PhonedemoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Link those objects with their respective
        rootView = inflater.inflate(R.layout.fragment_phonedemo,container,false);
        // Link those objects with their respective
        // id's that we have given in .XML file
        final TextView tvR = (TextView) rootView.findViewById(R.id.tvR);
        final TextView tvPython = (TextView) rootView.findViewById(R.id.tvPython);
        final TextView tvCPP = (TextView) rootView.findViewById(R.id.tvCPP);
        final TextView tvJava = (TextView) rootView.findViewById(R.id.tvJava);
        pieChart = rootView.findViewById(R.id.piechart);
        // Set the percentage of language used
        tvR.setText("40");
        tvPython.setText("30");
        tvCPP.setText("5");
        tvJava.setText("25");
        setData();
        return rootView;
    }
    private void setData()
    {


        // Set the data and color to the pie chart
        pieChart.addPieSlice(
                new PieModel(
                        "Madanalal",
                        Integer.parseInt(String.valueOf(40)),
                        Color.parseColor("#FFA726")));
        pieChart.addPieSlice(
                new PieModel(
                        "Indian",
                        Integer.parseInt(String.valueOf(30)),
                        Color.parseColor("#66BB6A")));
        pieChart.addPieSlice(
                new PieModel(
                        "Albino",
                        Integer.parseInt(String.valueOf(5)),
                        Color.parseColor("#EF5350")));
        pieChart.addPieSlice(
                new PieModel(
                        "Pardeshi",
                        Integer.parseInt(String.valueOf(25)),
                        Color.parseColor("#29B6F6")));

        // To animate the pie chart
        pieChart.startAnimation();
    }
}