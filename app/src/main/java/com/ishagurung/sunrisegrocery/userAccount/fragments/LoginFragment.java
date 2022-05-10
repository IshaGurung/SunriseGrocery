package com.ishagurung.sunrisegrocery.userAccount.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ishagurung.sunrisegrocery.home.MainActivity;
import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.api.ApiClient;
import com.ishagurung.sunrisegrocery.api.response.LoginResponse;
import com.ishagurung.sunrisegrocery.utils.SharedPrefUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment implements View.OnClickListener{

    EditText emailEditText, passwordEditText;
    Button loginBtn;
    ProgressBar circularProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.emailEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        loginBtn = view.findViewById(R.id.loginBtn);
        circularProgress = view.findViewById(R.id.circularProgress);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == loginBtn) {
            String email, password;
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            if (email.isEmpty() && password.isEmpty())
                Toast.makeText(getContext(), "Email or Password is Empty!", Toast.LENGTH_LONG).show();
            else {
                ProgressDialog progressDialog = ProgressDialog.show(getContext(), "",
                        "Logging In. Please wait...", false);
                Call<LoginResponse> loginResponseCall = ApiClient.getClient().login(email, password);
                loginResponseCall.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        progressDialog.dismiss();
                        LoginResponse loginResponse = response.body();
                        if(response.isSuccessful()){
                            if(loginResponse.getError()){
                                System.out.println("222222221222222222222 my error  is: "+ loginResponse.getError());
                                Toast.makeText(getContext(), loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            }else {

                                Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_LONG).show();
                                SharedPrefUtils.setBoolean(getActivity(), getString(R.string.isLogged), true);
                                SharedPrefUtils.setString(getActivity(), getString(R.string.name_key), loginResponse.getName());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.email_id), loginResponse.getEmail());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.created_key), loginResponse.getCreatedAt());
                                SharedPrefUtils.setString(getActivity(), getString(R.string.api_key), loginResponse.getApiKey());
                                SharedPrefUtils.setBoolean(getActivity(), getString(R.string.staff_key), loginResponse.getIs_staff());
                                getActivity().startActivity(new Intent(getContext(), MainActivity.class));
                                getActivity().finish();

                            }

                        }                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        progressDialog.dismiss();
                    }
                });
            }
        }

    }
    public void toggleProgress(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }
}