package com.ishagurung.sunrisegrocery.home.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.admin.AdminActivity;
import com.ishagurung.sunrisegrocery.userAccount.UserAccountActivity;
import com.ishagurung.sunrisegrocery.utils.SharedPrefUtils;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ProfileFragment extends Fragment {
    TextView logOutTV;
    TextView adminAreaTV;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logOutTV = view.findViewById(R.id.logOutTV);
        adminAreaTV = view.findViewById(R.id.adminAreaTV);
        checkAdmin();
        setClickListeners();
    }



    private void checkAdmin() {
        boolean is_staff = SharedPrefUtils.getBool(getActivity(), getString(R.string.staff_key), false);
        if (is_staff)
            adminAreaTV.setVisibility(View.VISIBLE);
    }

    private void setClickListeners() {
        logOutTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPrefUtils.clear(getContext());
                Intent userAccount = new Intent(getContext(), UserAccountActivity.class);
                startActivity(userAccount);
                getActivity().finish();
            }
        });
        adminAreaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AdminActivity.class);
                startActivity(intent);
            }
        });
    }
}