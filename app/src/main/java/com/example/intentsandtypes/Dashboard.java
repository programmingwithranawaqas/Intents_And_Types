package com.example.intentsandtypes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Dashboard extends AppCompatActivity {

    TextView tvResult;
    FloatingActionButton fabAdd;
    Button btnStartQuiz;

    ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvResult = findViewById(R.id.tvResult);
        fabAdd = findViewById(R.id.fabAdd);
        btnStartQuiz = findViewById(R.id.btnStartQuiz);

        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Dashboard.this, Quiz.class);

                launcher.launch(i);
            }
        });

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result->{
                    if(result.getResultCode() == RESULT_OK)
                    {
                        Intent i = result.getData();
                        tvResult.setText("Option Selected : "+i.getStringExtra("result"));

                    }
                    if(result.getResultCode()==RESULT_CANCELED)
                    {
                        Toast.makeText(this, "Start Quiz and select one option to continue", Toast.LENGTH_SHORT).show();
                    }
                });



        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dashboard.this, Login.class));
                finish();
            }
        });

        Intent i = getIntent();
        if(i!=null && i.getStringExtra("key_phone") != null)
        {
          String text = i.getStringExtra("key_phone") + "\n"+i.getStringExtra("key_address")+"\n"+i.getStringExtra("key_url");
          tvResult.setText(text);
        }

    }
}