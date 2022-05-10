package com.ishagurung.sunrisegrocery.userAccount;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ishagurung.sunrisegrocery.R;
import com.ishagurung.sunrisegrocery.userAccount.fragments.LoginFragment;
import com.ishagurung.sunrisegrocery.userAccount.fragments.RegisterFragment;

public class UserAccountActivity extends AppCompatActivity implements View.OnClickListener {
    TextView register, signUpRegister;
    boolean isRegister = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        register = findViewById(R.id.register);
        signUpRegister = findViewById(R.id.signUpRegister);
        register.setOnClickListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.formContainerFL, new LoginFragment()).commit();
    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            if (!isRegister) {

                getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new RegisterFragment()).commit();

            } else {
                getSupportFragmentManager().beginTransaction().replace(R.id.formContainerFL, new LoginFragment()).commit();
            }
            changeTexts();
            isRegister = !isRegister;
        }
    }

    private void changeTexts() {
        if (!isRegister) {
            register.setText("Login");
            signUpRegister.setText("Already Have an Account? ");
        }
        else {
            register.setText("Register");
            signUpRegister.setText("New to Sunrise Grocery? ");
        }
    }
}