package com.example.telefarm2.fragment.Products;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.telefarm2.R;
import com.example.telefarm2.ui.FruitsActivity;
import com.example.telefarm2.ui.LeavesActivity;
import com.example.telefarm2.ui.NutsActivity;
import com.example.telefarm2.ui.VegetablesActivity;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //intent to Fruits activity
        view.findViewById(R.id.product_fruits).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), FruitsActivity.class));
        });
        //intent to Vegetables activity
        view.findViewById(R.id.product_vegetables).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), VegetablesActivity.class));
        });
        //intent to Leaves activity
        view.findViewById(R.id.product_leaves).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), LeavesActivity.class));
        });
        //intent to nuts activity
        view.findViewById(R.id.product_nuts).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), NutsActivity.class));
        });




    }
}