package com.ishagurung.sunrisegrocery.userAccount.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.api.ApiClient;
import com.ishagurung.sunrisegrocery.api.response.RegisterResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterFragment extends Fragment {
    EditText emailEditText, nameEditText, passwordEditText, confirmPasswordEditText;
    Button registerBtn;
    ProgressBar circularProgress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailEditText = view.findViewById(R.id.emailEditText);
        nameEditText = view.findViewById(R.id.nameEditText);
        passwordEditText = view.findViewById(R.id.passwordEditText);
        confirmPasswordEditText = view.findViewById(R.id.confirmPasswordEditText);
        circularProgress = view.findViewById(R.id.circularProgress);
        registerBtn = view.findViewById(R.id.registerBtn);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    toggleLoading(true);
                    Call<RegisterResponse> registerCall = ApiClient.getClient().register(nameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());
                    registerCall.enqueue(new Callback<RegisterResponse>() {
                        @Override
                        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                            RegisterResponse registerResponse = response.body();
                            toggleLoading(false);
                            if (response.isSuccessful()) {
                                Toast.makeText(getActivity(), registerResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                if (!registerResponse.getError()) {
                                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterResponse> call, Throwable t) {
                            toggleLoading(false);
                            Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

    }
    void toggleLoading(Boolean toogle) {
        if (toogle)
            circularProgress.setVisibility(View.VISIBLE);
        else
            circularProgress.setVisibility(View.GONE);
    }

    public boolean validate() {
        boolean validate = true;
        if (emailEditText.getText().toString().isEmpty()
                || passwordEditText.getText().toString().isEmpty()
                || confirmPasswordEditText.getText().toString().isEmpty()
                || nameEditText.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "None of the above fields can be empty", Toast.LENGTH_SHORT).show();
            validate = false;
        } else if (!passwordEditText.getText().toString().equals(confirmPasswordEditText.getText().toString())) {
            confirmPasswordEditText.setError("Password does not match please check!!");
            validate = false;
        }
        return validate;
    }

}