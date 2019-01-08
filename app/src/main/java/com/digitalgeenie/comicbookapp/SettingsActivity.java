package com.digitalgeenie.comicbookapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.digitalgeenie.comicbookapp.utils.ToastUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static com.digitalgeenie.comicbookapp.utils.navigationdrawer.NavigationDrawerUtil.setupDrawerContent;
import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class SettingsActivity extends AppCompatActivity {

    private Button changeEmail;
    private Button changePassword;
    private Button sendEmail;
    private Button remove;

    private EditText oldEmail, newEmail, password, newPassword;
    private ProgressBar progressBar;
    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        mDrawer.addDrawerListener(drawerToggle);
        NavigationView nvDrawer = findViewById(R.id.nvView);
        setupDrawerContent(mDrawer, this, nvDrawer);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = firebaseAuth -> {
            FirebaseUser user1 = firebaseAuth.getCurrentUser();
            if (user1 == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                finish();
            }
        };

        Button btnChangeEmail = findViewById(R.id.change_email_button);
        Button btnChangePassword = findViewById(R.id.change_password_button);
        Button btnSendResetEmail = findViewById(R.id.sending_pass_reset_button);
        Button btnRemoveUser = findViewById(R.id.remove_user_button);
        changeEmail = findViewById(R.id.changeEmail);
        changePassword = findViewById(R.id.changePass);
        sendEmail = findViewById(R.id.send);
        remove = findViewById(R.id.remove);
        Button signOut = findViewById(R.id.sign_out);
        Button back = findViewById(R.id.btn_back);

        oldEmail = findViewById(R.id.old_email);
        newEmail = findViewById(R.id.new_email);
        password = findViewById(R.id.password);
        newPassword = findViewById(R.id.newPassword);

        oldEmail.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        password.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        changeEmail.setVisibility(View.GONE);
        changePassword.setVisibility(View.GONE);
        sendEmail.setVisibility(View.GONE);
        remove.setVisibility(View.GONE);

        progressBar = findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }

        btnChangeEmail.setOnClickListener(v -> {
            if (isConnected(this)) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.VISIBLE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.GONE);
                changeEmail.setVisibility(View.VISIBLE);
                changePassword.setVisibility(View.GONE);
                sendEmail.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        });

        changeEmail.setOnClickListener(v -> {
            if (isConnected(this)) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null && !newEmail.getText().toString().trim().equals("")) {
                    user.updateEmail(newEmail.getText().toString().trim())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    ToastUtil.longToast("Email address is updated. Please sign in with new email id!", this);
                                    signOut();
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    ToastUtil.longToast("Failed to update email!", this);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                } else if (newEmail.getText().toString().trim().equals("")) {
                    newEmail.setError("Enter email");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        btnChangePassword.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                oldEmail.setVisibility(View.GONE);
                newEmail.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.VISIBLE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.VISIBLE);
                sendEmail.setVisibility(View.GONE);
                remove.setVisibility(View.GONE);
            }
        });

        changePassword.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null && !newPassword.getText().toString().trim().equals("")) {
                    if (newPassword.getText().toString().trim().length() < 6) {
                        newPassword.setError("Password too short, enter minimum 6 characters");
                        progressBar.setVisibility(View.GONE);
                    } else {
                        user.updatePassword(newPassword.getText().toString().trim())
                                .addOnCompleteListener(task -> {
                                    if (task.isSuccessful()) {
                                        ToastUtil.longToast("Password is updated, sign in with new password!", this);
                                        signOut();
                                        progressBar.setVisibility(View.GONE);
                                    } else {
                                        ToastUtil.longToast("Failed to update password!", this);
                                        progressBar.setVisibility(View.GONE);
                                    }
                                });
                    }
                } else if (newPassword.getText().toString().trim().equals("")) {
                    newPassword.setError("Enter password");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        btnSendResetEmail.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                oldEmail.setVisibility(View.VISIBLE);
                newEmail.setVisibility(View.GONE);
                password.setVisibility(View.GONE);
                newPassword.setVisibility(View.GONE);
                changeEmail.setVisibility(View.GONE);
                changePassword.setVisibility(View.GONE);
                sendEmail.setVisibility(View.VISIBLE);
                remove.setVisibility(View.GONE);
            }
        });

        sendEmail.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                progressBar.setVisibility(View.VISIBLE);
                if (!oldEmail.getText().toString().trim().equals("")) {
                    auth.sendPasswordResetEmail(oldEmail.getText().toString().trim())
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    ToastUtil.longToast("Reset password email is sent!", this);
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    ToastUtil.longToast("Failed to send reset email!", this);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                } else {
                    oldEmail.setError("Enter email");
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

        btnRemoveUser.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                progressBar.setVisibility(View.VISIBLE);
                if (user != null) {
                    user.delete()
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    ToastUtil.longToast("Your profile is deleted:( Create a account now!", this);
                                    startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
                                    finish();
                                    progressBar.setVisibility(View.GONE);
                                } else {
                                    ToastUtil.longToast("Failed to delete your account!", this);
                                    progressBar.setVisibility(View.GONE);
                                }
                            });
                }
            }
        });

        signOut.setOnClickListener(v -> {
            if (isConnected(SettingsActivity.this)) {
                signOut();
            }
        });

        back.setOnClickListener(v -> finish());

    }

    //sign out method
    public void signOut() {
        if (isConnected(SettingsActivity.this)) {
            auth.signOut();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }
}
