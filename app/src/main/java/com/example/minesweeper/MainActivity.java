package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /*private TextView name;*/
    private Button exitGame;
    private Button logGame;
    private Button registerGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logGame = findViewById(R.id.btmLogGame);
        registerGame = findViewById(R.id.btmRegisterGame);
        exitGame = findViewById(R.id.btmExitGame);

        logGame.setOnClickListener(view -> {
            Intent intent1 = new Intent(MainActivity.this, LogActivity.class);
            startActivity(intent1);
            MainActivity.this.finish();
        });


        registerGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent2);
                MainActivity.this.finish();
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.finish();
                System.exit(0);
            }
        });
    }


}