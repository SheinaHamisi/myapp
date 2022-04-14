package com.moringaschool.ladiesspot.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.moringaschool.ladiesspot.R;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity  implements View.OnClickListener {
    @BindView(R.id.Name) TextInputLayout fullName;
    @BindView(R.id.email) TextInputLayout mEmail;
    @BindView(R.id.number) TextInputLayout mNumber;
    @BindView(R.id.Password) TextInputLayout mPassword;
    @BindView(R.id.confirmpassword) TextInputLayout mConfirmpassword;
    @BindView(R.id.signup) Button signup;
    @BindView(R.id.login) TextView login;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String mName;
    public static final String TAG = SignupActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view == signup) {
            createNewUser();
        } else if (view == login) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        }
    }

    private void createNewUser() {
        final String name = fullName.getEditText().getText().toString().trim();
        final String email = mEmail.getEditText().getText().toString().trim();
        final String number = mNumber.getEditText().getText().toString().trim();
        String password = mPassword.getEditText().getText().toString().trim();
        String confirmPassword = mConfirmpassword.getEditText().getText().toString().trim();

        boolean validEmail = isValidEmail(email);
        boolean validName = isValidName(name);
        boolean validPassword = isValidPassword(password, confirmPassword);
        if (!validEmail || !validName || !validPassword) return;


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {

                    if (task.isSuccessful()) {
                        createFirebaseUserProfile(Objects.requireNonNull(task.getResult().getUser()));
                        Log.d(TAG, "Authentication successful");
                    } else {
                        Toast.makeText(SignupActivity.this, "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
       //Creating user with firebase
    private void createFirebaseUserProfile(FirebaseUser user) {
        UserProfileChangeRequest addProfileName = new UserProfileChangeRequest.Builder()
                .setDisplayName(mName)
                .build();

        user.updateProfile(addProfileName)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete( Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignupActivity.this, "Sign up process was successful", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
//Checking whether the password inputted is valid according to the parameters set
    private boolean isValidPassword(String password, String confirmPassword) {
        if (password.length() < 6) {
            mPassword.setError("Please create a password containing at least 6 characters");
            return false;
        } else if (!password.equals(confirmPassword)) {
            mPassword.setError("Passwords do not match");
            return false;
        }
        return true;
    }

    //Checking whether the Name inputted is valid according to the parameters set
    private boolean isValidName(String name) {
        if (name.equals("")) {
            fullName.setError("Please enter your name");
            return false;
        }
        return true;
    }
    //Checking whether the Email inputted is valid according to the parameters set
    private boolean isValidEmail(String email) {
        boolean isGoodEmail =
                (email != null && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
        if (!isGoodEmail) {
            mEmail.setError("Please enter a valid email address");
            return false;
        }
        return isGoodEmail;
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
