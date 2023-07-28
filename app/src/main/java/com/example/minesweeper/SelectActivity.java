package com.example.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    private RadioButton beginner,intermediate,advanced;
    private Button startGame;
    private ImageView backMain;
    private String gamelevel;
    private TextView welcome;
    int boardWidth = 0, boardHeight = 0, bombs = 0;
    private Button music;
    private String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        beginner = findViewById(R.id.btnBeginner);
        intermediate = findViewById(R.id.btnIntermediate);
        advanced = findViewById(R.id.btnAdvanced);
        startGame = findViewById(R.id.btmStartGame);
        backMain = findViewById(R.id.igvBackMain);
        music = findViewById(R.id.musicchoice);
        welcome = findViewById(R.id.welcome);
        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        welcome.setText("Hello,"+name);
        setListeners();

        startGame.setOnClickListener(view -> {
            if(gamelevel == null){
                Toast.makeText(this, "请选择游戏等级！", Toast.LENGTH_LONG).show();
            }else {
                Intent intent1 = new Intent(SelectActivity.this, GameActivity.class);
                intent1.putExtra("name", name);
                intent1.putExtra("gamelevel", gamelevel);
                intent1.putExtra("boardWidth", boardWidth);
                intent1.putExtra("boardHeight", boardHeight);
                intent1.putExtra("bombs", bombs);

                startActivity(intent1);
                SelectActivity.this.finish();
            }
        });
        backMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this,MainActivity.class);
                startActivity(intent);
                SelectActivity.this.finish();

            }
        });
        music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectActivity.this , MusicBox.class);
                intent.putExtra("name", name);
                startActivity(intent);
                SelectActivity.this.finish();
            }

        });
    }



    // 对三个单选按钮设置监听，并赋予不同的 boardWidth \ boardHeight \ bombs .
    private void setListeners(){
        SelectActivity.OnClick onClick = new SelectActivity.OnClick();
        beginner.setOnClickListener(onClick);
        intermediate.setOnClickListener(onClick);
        advanced.setOnClickListener(onClick);

    }
    private class OnClick implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnBeginner:
                    gamelevel = "初级"; // 雷数占比 0.1
                    boardWidth = 15;
                    boardHeight = 20;
                    bombs = 30;
                    break;
                case R.id.btnIntermediate:
                    gamelevel = "中级"; // 雷数占比 0.17
                    boardWidth = 15;
                    boardHeight = 20;
                    bombs =50 ;
                    break;
                case R.id.btnAdvanced:
                    gamelevel = "高级"; // 雷数占比0.28
                    boardWidth = 15;
                    boardHeight = 20;
                    bombs =80;
                    break;
            }
        }
    }
}