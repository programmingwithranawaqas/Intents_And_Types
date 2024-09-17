package com.example.intentsandtypes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Animation logo_animation, slogan_animation;
    ImageView ivLogo;
    TextView tvSlogan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        ivLogo.setAnimation(logo_animation);
        tvSlogan.setAnimation(slogan_animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sPref = getSharedPreferences("InstaLogin", MODE_PRIVATE);
                String username = sPref.getString("username", "");
                if(username.isEmpty())
                {
                    Intent i = new Intent(MainActivity.this, InstaLogin.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Intent i = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(i);
                    finish();
                }

            }
        }, 5000);
    }

    private void init()
    {
        logo_animation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        slogan_animation = AnimationUtils.loadAnimation(this, R.anim.slogan_animation);
        ivLogo = findViewById(R.id.ivlogo);
        tvSlogan = findViewById(R.id.tvSlogan);
    }
}