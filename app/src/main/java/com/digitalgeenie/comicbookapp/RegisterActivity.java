package com.digitalgeenie.comicbookapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.digitalgeenie.comicbookapp.utils.ToastUtil;
import com.google.firebase.auth.FirebaseAuth;

import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class RegisterActivity extends AppCompatActivity {

    /* Class variables*/
    private EditText inputEmail, inputPassword;
    private ProgressBar progressBar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        final Context context = this;

        inputEmail = findViewById(R.id.editTextEmail);
        inputPassword = findViewById(R.id.editTextPassword);
        TextView textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(view -> finish());
        Button buttonRegister = findViewById(R.id.buttonRegister);
        Button buttonResetPassword = findViewById(R.id.buttonResetPassword);
        progressBar = findViewById(R.id.progress_bar);

        buttonRegister.setOnClickListener(view -> {
            if (isConnected(RegisterActivity.this)) {

                String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    ToastUtil.shortToast("Enter email", context);
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    ToastUtil.shortToast("Enter Password", context);
                    return;
                }

                if (password.length() < 6) {
                    ToastUtil.shortToast("Password too short, enter minimum 6 characters!", context);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener((Activity) context, task -> {
                            progressBar.setVisibility(View.GONE);
                            if (!task.isSuccessful()) {
                                ToastUtil.shortToast("Authentication failed.", context);
                            } else {
                                startActivity(new Intent(context, HomeActivity.class));
                            }
                        });
            }
        });

        buttonResetPassword.setOnClickListener(view -> {
            if (isConnected(context)) {
                startActivity(new Intent(context, ResetPasswordActivity.class));
            }
        });
    }
}
