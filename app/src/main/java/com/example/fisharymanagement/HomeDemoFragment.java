package com.example.fisharymanagement;

import android.os.Bundle;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeDemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeDemoFragment extends Fragment {

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;

    // variable for view with name rootView
    private View rootView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeDemoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeDemoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeDemoFragment newInstance(String param1, String param2) {
        HomeDemoFragment fragment = new HomeDemoFragment();
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
                             Bundle inState) {
        rootView = inflater.inflate(R.layout.fragment_home_demo,container,false);
        final TextView wishGreet = (TextView) rootView.findViewById(R.id.wishGreet);
        String TTT = getTimeFromAndroid();
        wishGreet.setText(TTT);
        return rootView;
    }
    private String getTimeFromAndroid() {
        Calendar c = Calendar.getInstance();
        String TTT = "Happy to have you";
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);
        if (timeOfDay >= 0 && timeOfDay < 12) {
            TTT = "Good Morning";
        } else if (timeOfDay >= 12 && timeOfDay < 16) {
            TTT="Good Afternoon";
        } else if (timeOfDay >= 16 && timeOfDay < 21) {
            TTT="Good Evening";
        } else if (timeOfDay >= 21 && timeOfDay < 24) {
            TTT="Good Night";
        }
        return TTT;
    }
}