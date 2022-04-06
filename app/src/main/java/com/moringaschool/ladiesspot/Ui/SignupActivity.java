package com.moringaschool.ladiesspot.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.moringaschool.ladiesspot.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity  implements View.OnClickListener {
    @BindView(R.id.Name) TextInputLayout fullName;
    @BindView(R.id.Username) TextInputLayout username;
    @BindView(R.id.email) TextInputLayout email;
    @BindView(R.id.number) TextInputLayout number;
    @BindView(R.id.Password) TextInputLayout password;
    @BindView(R.id.confirmpassword) TextInputLayout Confirmpassword;
    @BindView(R.id.signup) Button signup;
    @BindView(R.id.login) TextView login;
    @BindView(R.id.arrow) TextView arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == signup) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else if (view == login) {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();

        } else if (view == arrow) {
            onBackPressed();
        }
    }
}
