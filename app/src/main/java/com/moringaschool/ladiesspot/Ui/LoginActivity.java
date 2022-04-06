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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.username) TextInputLayout username;
    @BindView(R.id.password) TextInputLayout password;
    @BindView(R.id.Register) TextView register;
    @BindView(R.id.Login) Button Login;
    @BindView(R.id.arrow) Button arrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        register.setOnClickListener(this);
        Login.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == Login) {
            Intent intent = new Intent(LoginActivity.this, SalonActivity.class);
            startActivity(intent);
            finish();
        } else if (view == register) {
            Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
            startActivity(intent);
            finish();

        } else if (view == arrow) {
           onBackPressed();

        }
    }
}