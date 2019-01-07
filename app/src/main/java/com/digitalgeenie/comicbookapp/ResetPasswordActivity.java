package com.digitalgeenie.comicbookapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.digitalgeenie.comicbookapp.utils.ToastUtil;
import com.google.firebase.auth.FirebaseAuth;

import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class ResetPasswordActivity extends AppCompatActivity {

    /* Edit Text to get the registered email address */
    private EditText inputEmail;

    /*An instance of FirebaseAuth */
    private FirebaseAuth auth;

    /* Progress Bar to show the progress while processing */
    private ProgressBar progressBar;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        context = this;

        inputEmail = findViewById(R.id.email);
        Button btnReset = findViewById(R.id.buttonResetPassword);
        Button btnBack = findViewById(R.id.buttonBack);
        progressBar = findViewById(R.id.progressBar);

        /* Initializing FirebaseAuth instance*/
        auth = FirebaseAuth.getInstance();

        /* Action on Clicking back button */
        btnBack.setOnClickListener(v -> {
            if (isConnected(context)) {
                finish();
            }
        });

        /*Action to perform while clicking the Reset Password button */
        btnReset.setOnClickListener(v -> {
            if (isConnected(context)) {
                /* Getting email from the EditText*/
                String email = inputEmail.getText().toString().trim();

                /*Check if the user has entered registered email */
                if (TextUtils.isEmpty(email)) {
                    ToastUtil.shortToast("Enter your registered email id", context);
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                /* Sending the password to the registered email */
                auth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                ToastUtil.shortToast("We have sent you instructions to reset your password!", context);
                            } else {
                                ToastUtil.shortToast("Failed to send reset password!", context);
                            }

                            progressBar.setVisibility(View.GONE);
                        });
            }
        });
    }
}
