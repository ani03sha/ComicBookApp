package com.digitalgeenie.comicbookapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.digitalgeenie.comicbookapp.utils.ToastUtil;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.digitalgeenie.comicbookapp.constants.AppConstants.NO_INTERNET;
import static com.digitalgeenie.comicbookapp.utils.network.Connectivity.isConnected;

public class PhoneAuthActivity extends AppCompatActivity {


    private static final String TAG = PhoneAuthActivity.class.getSimpleName();
    @BindView(R.id.select_country)
    protected CountryCodePicker country;
    private EditText mPhoneNumberField, mVerificationField;
    private FirebaseAuth mAuth;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_auth);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String phoneCode = sharedPreferences.getString("selfCountryCode", "");

        mPhoneNumberField = findViewById(R.id.field_phone_number);
        mPhoneNumberField.setText(String.format("+91%s", phoneCode));
        mVerificationField = findViewById(R.id.field_verification_code);

        Button mStartButton = findViewById(R.id.button_start_verification);
        Button mVerifyButton = findViewById(R.id.button_verify_phone);
        Button mResendButton = findViewById(R.id.button_resend);

        ButterKnife.bind(this);

        country.setOnCountryChangeListener(() -> mPhoneNumberField.setText(country.getSelectedCountryCodeWithPlus()));

        mStartButton.setOnClickListener(view -> {

            if (isConnected(this)) {
                Toast.makeText(PhoneAuthActivity.this, "Sending OTP", Toast.LENGTH_SHORT).show();
                ProgressDialog pDialog = new ProgressDialog(PhoneAuthActivity.this);
                if (!validatePhoneNumber()) {
                    return;
                }
                pDialog.setMessage("Please wait...");
                pDialog.setCancelable(false);
                pDialog.show();
                startPhoneNumberVerification(mPhoneNumberField.getText().toString());
                if (pDialog.isShowing())
                    pDialog.dismiss();
            } else {
                ToastUtil.longToast(NO_INTERNET, this);
            }
        });

        mVerifyButton.setOnClickListener(view -> {

            if (isConnected(this)) {
                Toast.makeText(PhoneAuthActivity.this, "Verifying OTP", Toast.LENGTH_SHORT).show();
                String code = mVerificationField.getText().toString();
                if (TextUtils.isEmpty(code)) {
                    mVerificationField.setError("Cannot be empty.");
                    return;
                }
                verifyPhoneNumberWithCode(mVerificationId, code);
            } else {
                ToastUtil.longToast(NO_INTERNET, this);
            }
        });

        mResendButton.setOnClickListener(view -> {

            if (isConnected(this)) {
                Toast.makeText(this, "Sending OTP again", Toast.LENGTH_SHORT).show();
                resendVerificationCode(mPhoneNumberField.getText().toString(), mResendToken);
            } else {
                ToastUtil.longToast(NO_INTERNET, this);
            }
        });

        mAuth = FirebaseAuth.getInstance();
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Log.d(TAG, "onVerificationCompleted:" + credential);
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Log.w(TAG, "onVerificationFailed", e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    mPhoneNumberField.setError("Invalid phone number.");
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Snackbar.make(findViewById(android.R.id.content), "Quota exceeded.",
                            Snackbar.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Log.d(TAG, "onCodeSent:" + verificationId);
                mVerificationId = verificationId;
                mResendToken = token;
            }
        };
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "signInWithCredential:success");
                        Toast.makeText(PhoneAuthActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(PhoneAuthActivity.this, HomeActivity.class));
                        finish();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            mVerificationField.setError("Invalid code.");
                        }
                    }
                });
    }


    private void startPhoneNumberVerification(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks,         // OnVerificationStateChangedCallbacks
                token);             // ForceResendingToken from callbacks
    }

    private boolean validatePhoneNumber() {
        String phoneNumber = mPhoneNumberField.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)) {
            mPhoneNumberField.setError("Invalid phone number.");
            return false;
        }
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(PhoneAuthActivity.this, LoginActivity.class));
            finish();
        }
    }
}
