package com.example.intentsandtypes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    // hooks
    Button btnCallMe, btnOpenAddress, btnOpenWebsite, btnSendData;
    EditText etPhone;
    TextInputEditText tietAddress, tietUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        init();

        btnCallMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                Intent i = new Intent(Intent.ACTION_DIAL);
                if(!phone.isEmpty())
                {
                    i.setData(Uri.parse("tel:"+phone));
                }
                startActivity(i);

            }
        });
        btnOpenAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String address = tietAddress.getText().toString().trim();
                if(address.isEmpty())
                {
                    Toast.makeText(Login.this, "Address can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:(0,0)?q="+address));
                    i.setPackage("com.google.android.apps.maps");
                    startActivity(i);
                }
            }
        });

        btnOpenWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = tietUrl.getText().toString().trim();
                if(url.isEmpty())
                {
                    Toast.makeText(Login.this, "Url can't be empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(i);
                }
            }
        });

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = etPhone.getText().toString();
                String address = tietAddress.getText().toString().trim();
                String url = tietUrl.getText().toString().trim();

                Intent i = new Intent(Login.this, Dashboard.class);
                i.putExtra("key_phone", phone);
                i.putExtra("key_address", address);
                i.putExtra("key_url", url);
                startActivity(i);
                finish();
            }
        });



    }
    private void init()
    {
        btnCallMe = findViewById(R.id.btnCallMe);
        etPhone = findViewById(R.id.etPhone);
        btnOpenAddress = findViewById(R.id.btnOpenAddress);
        tietAddress= findViewById(R.id.tietAddress);
        btnOpenWebsite = findViewById(R.id.btnOpenWebsite);
        btnSendData = findViewById(R.id.btnSendData);
        tietUrl= findViewById(R.id.tietUrl);

    }
}