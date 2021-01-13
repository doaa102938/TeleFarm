package com.example.telefarm2.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.telefarm2.R;
import com.example.telefarm2.ui.SettingActivity;
import com.example.telefarm2.ui.SplashActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    private TextView username;
    private CircleImageView userimage;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firestore;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userimage = view.findViewById(R.id.profile_image);
        username = view.findViewById(R.id.user_name_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        getuserdata();



        //intent to setting activity
        view.findViewById(R.id.setting).setOnClickListener(v -> {
            startActivity(new Intent(getActivity(), SettingActivity.class));

        });
        //onclick to log out
        view.findViewById(R.id.btn_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();

            }

            private void logout() {
                if (getActivity() != null)
                    new AlertDialog.Builder(getActivity())
                            .setTitle("Logout")
                            .setMessage("Are you sure want to logout ??")
                            .setIcon(R.drawable.ic_error)
                            .setPositiveButton("Yes", (dialog, which) -> {
                                getActivity().getSharedPreferences("login", Context.MODE_PRIVATE)
                                        .edit()
                                        .clear()
                                        .apply();
                                firebaseAuth.signOut();
                                startActivity(new Intent(getActivity(), SplashActivity.class));


                            })
                            .setNegativeButton("Cancle", null)
                            .create().show();
            }
        });
    }

    private void getuserdata() {

        if (getActivity() != null) {
            if (firebaseAuth.getCurrentUser() !=null){

            String UID = firebaseAuth.getCurrentUser().getUid();
            DocumentReference DB = firestore.collection("users")
                    .document(UID);
            DB.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    DocumentSnapshot snapshot = task.getResult();
                    assert snapshot != null;
                    String nameDB = snapshot.getString("name");
                    String imageDB = snapshot.getString("image");
                    String emailDB = snapshot.getString("email");
                    String phoneDB = snapshot.getString("phone");

                    username.setText(nameDB);
                    assert imageDB != null;


                }else{
                    Toast.makeText(getActivity(),"Error in Task "+task.getException().getMessage()  ,Toast.LENGTH_SHORT).show();
                }

            });
        }
    }
}}