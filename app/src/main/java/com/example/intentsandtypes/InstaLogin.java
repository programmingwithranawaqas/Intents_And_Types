package com.example.intentsandtypes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class InstaLogin extends AppCompatActivity {

    Button btnLogin;
    TextInputEditText etUsername, etPassword;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_insta_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString();
                if(username.isEmpty() || password.isEmpty() )
                {
                    Toast.makeText(InstaLogin.this, "Something is missing", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(username.equals("admin") || password.equals("admin") )
                {
                    SharedPreferences.Editor editor = sPref.edit();
                    editor.putString("username", username);
                    editor.apply();

                    startActivity(new Intent(InstaLogin.this, Dashboard.class));
                    finish();
                }

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        etUsername.setText(sPref.getString("key_username",""));
        etPassword.setText(sPref.getString("key_password",""));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit = sPref.edit();

        edit.putString("key_username", etUsername.getText().toString());
        edit.putString("key_password", etPassword.getText().toString());

        edit.apply();

    }

    private void init()
    {
        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.tietUsername);
        etPassword = findViewById(R.id.tietPassword);
        sPref = getSharedPreferences("InstaLogin", MODE_PRIVATE);
    }
}