package com.example.telefarm2.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.disklrucache.DiskLruCache;
import com.example.telefarm2.R;
import com.example.telefarm2.fragment.Products.ProductsAdapter;
import com.example.telefarm2.model.fruits;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class FruitsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private ProductsAdapter adapter;
    private List<fruits> list;
    private FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruits);

        recyclerView=findViewById(R.id.recycler_fruits);
        progressBar=findViewById(R.id.progress_fruits);
        firestore=FirebaseFirestore.getInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        adapter = new ProductsAdapter(this,list);
        recyclerView.setAdapter(adapter);
        getDate();
    }

    private void getDate(){
        progressBar.setVisibility(View.VISIBLE);
        firestore.collection("Fruits")
                .orderBy("time",Query.Direction.DESCENDING)
                .addSnapshotListener((value ,error)->{
                    if(error == null){
                        if(value== null){
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(this,"Value is Null",Toast.LENGTH_SHORT).show();
                        }else {
                            for (DocumentChange documentChange : value.getDocumentChanges()){
                                fruits FruitsModel= documentChange.getDocument().toObject(fruits.class);
                                list.add(FruitsModel);
                                adapter.notifyDataSetChanged();
                            }
                            progressBar.setVisibility(View.GONE);
                        }


                    }else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                });
    }
}