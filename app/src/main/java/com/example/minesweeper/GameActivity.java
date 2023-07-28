package com.example.minesweeper;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minesweeper.GridView.CheckSquare;
import com.example.minesweeper.GridView.GetBoundaryArr;
import com.example.minesweeper.GridView.GridViewAdapter;
import com.example.minesweeper.GridView.ProductBombs;

import java.util.ArrayList;
import java.util.Arrays;

public class GameActivity extends AppCompatActivity {

    public static int boardWidth =0, boardHeight = 0, bombs = 0;
    public static ArrayList bombsLocation;
    public static boolean[] thisSquareHasBomb;
    public static boolean[] guessThisSquareIsBomb;
    public static boolean[] thisSquareHasTraversed;
    public static int[] Upboundary;
    public static int[] Downboundary;
    public static int[] Leftboundary;
    public static int[] Rightboundary;
    public static int[] Corner;
    public static GridView mGv;
    private String gamelevel;
    private Chronometer chronometer;
    private String name;



    /**   ##################################################
     *    # 选好游戏级别后，                                  #
     *    #   step1：画游戏板                               #
     *    #   step2：在游戏板上产生雷，并标记相应的位置          #
     *    #   step3：点击游戏方块，响应监听事件和游戏逻辑        #
     *    ###############################################
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);



        getParam(); // 传参数
        placeParam(); // 放置参数
        AboutChronometer(); // 计时器开始计时

        InitBooleanArr(boardWidth,boardHeight,bombs); // 产生三个布尔数组、一个雷位置的数组。
        InitIntArr(boardWidth,boardHeight);// 产生边界数组，上 下 左 右 角。

        drawBoard(boardWidth,boardHeight);  // 画游戏板，放置雷。




    }
    /**
     * 计时器
     */
    private void AboutChronometer(){
        chronometer = (Chronometer) findViewById(R.id.GameTime);
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 从主活动中传入“gamelevel、boardWidth 、boardHeight 、bombs”；
     * 更改游戏界面上的游戏等级说明；
     */
    private void getParam(){
        Intent intent = getIntent();
        gamelevel = intent.getStringExtra("gamelevel");
        boardWidth = intent.getIntExtra("boardWidth",boardWidth);
        boardHeight = intent.getIntExtra("boardHeight",boardHeight);
        bombs = intent.getIntExtra("bombs",bombs);

    }

    // 将游戏参数放入相应的 textview 中
    private void placeParam(){
        DynamicSetText_String(gamelevel,R.id.Game_level);
        DynamicSetText_IntAdd(boardWidth,boardHeight,R.id.grid_number);
        DynamicSetText_Int2Str(bombs,R.id.mine_number);
    }
    // 动态更改String文本
    private void DynamicSetText_String(String string , int viewId){
        TextView RefreshTextObject = (TextView)findViewById(viewId);
        RefreshTextObject.setText(string);
    }
    // 将两个int数变成string，相连，动态更改对应的textview;
    private void DynamicSetText_IntAdd(int data1 ,int data2, int viewId) {
        StringBuilder gridSize = new StringBuilder(String.valueOf(data1)).append("*").append(data2);
        TextView RefreshTextObject = (TextView)findViewById(viewId);
        RefreshTextObject.setText(gridSize);
    }
    // int 转 String，动态更改对应的textview;
    private void DynamicSetText_Int2Str(int data, int viewId) {
        String str = String.valueOf(data);
        TextView RefreshTextObject = (TextView)findViewById(viewId);
        RefreshTextObject.setText(str);
    }
    // 初始化thisSquareHasBomb[]\guessThisSquareIsBomb[]\thisSquareHasTraversed[]。产生雷数组。
    private void InitBooleanArr(int W, int H,int B){
        thisSquareHasBomb = new boolean[(W * H)];
        guessThisSquareIsBomb = new boolean[(W * H)];
        thisSquareHasTraversed = new boolean[(W * H)];

        ProductBombs productBombs = new ProductBombs();
        bombsLocation = productBombs.getRandomArray(B);

//        System.out.println(bombsLocation);
    }
    // 初始化边界数组。
    private void InitIntArr(int W,int H){
        Upboundary = new int[W-2];
        Downboundary = new int[W-2];
        Leftboundary = new int[H-2];
        Rightboundary = new int[H-2];
        Corner = new int[4];

        GetBoundaryArr getBoundaryArr = new GetBoundaryArr();
        getBoundaryArr.getBounaryLocation(Upboundary,Downboundary,Leftboundary,Rightboundary,Corner,W,H);
    }

    // 在GameActivity 中画网格,产生雷数组，放置雷。
    public void drawBoard(int Width, int Height){

        mGv =  findViewById(R.id.grid_gameboard);  // 画游戏板。
//        LinearLayout.LayoutParams linearParams2 = (LinearLayout.LayoutParams)mGv.getLayoutParams();
//        linearParams2.width = Width*68;
//        linearParams2.height = Height*80;
//        mGv.setLayoutParams(linearParams2);
        mGv.setNumColumns(Width);
        mGv.setAdapter(new GridViewAdapter(GameActivity.this));

        ItemClickListener(mGv);

    }

    private void ItemClickListener(GridView gridView) {

        CheckSquare cs = new CheckSquare();

        // 点击监听事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (!GameActivity.guessThisSquareIsBomb[i]) {

                    if (GameActivity.thisSquareHasBomb[i]) {
                        LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                        ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                        imageView.setImageResource(R.drawable.reveal);
                        GameOver(i);
                    } else {
                        // 检查周围8个块
                        cs. expandCheckBombs(i);
                        if(cs.IsGameSuccess()){
                            GameSuccess();
                        }
                    }

                } else {
                    // 取消猜测，让块返回原样
                    LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                    ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                    imageView.setImageResource(R.drawable.question);
//                    Toast.makeText(view.getContext(), "之前猜测是雷，但现在觉得不是" + i, Toast.LENGTH_SHORT).show();
                    GameActivity.guessThisSquareIsBomb[i] = false;
                    GameActivity.thisSquareHasTraversed[i] = false;
                }
            }
        });

        // 长按监听事件
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                if (!GameActivity.thisSquareHasTraversed[i]){
                    LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                    ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                    imageView.setImageResource(R.drawable.flag);
//                    Toast.makeText(view.getContext(), "我觉得它是雷"+i,Toast.LENGTH_SHORT).show();
                    GameActivity.guessThisSquareIsBomb[i] = true;
                    GameActivity.thisSquareHasTraversed[i] = true;
                }

                if(cs.IsGameSuccess()){
                    GameSuccess();
                }

                return true;
            }
        });

    }
    // 游戏失败
    private void GameOver(int position){
        // 显示所有雷
        Over_showAllBombs(position);
        chronometer.stop();

        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        // 弹出提示框
        AlertDialog.Builder GameOverBuilder = new AlertDialog.Builder(this);
        GameOverBuilder.setTitle("Game Over");
        GameOverBuilder.setIcon(R.drawable.fail);
        GameOverBuilder.setMessage("挑战失败！ 用时: "+chronometer.getText().toString()+"。"+ "是否要再次发起挑战？");
        GameOverBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this,SelectActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                GameActivity.this.finish();
            }
        });
        GameOverBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                startActivity(intent);
                GameActivity.this.finish();
            }
        });
        GameOverBuilder.show();
    }

    // 游戏成功
    private void GameSuccess(){
        //显示所有雷
        Success_showAllBombs();
        chronometer.stop();

        Intent intent = this.getIntent();
        name = intent.getStringExtra("name");
        // 弹出提示框
        AlertDialog.Builder CongratulationsBuilder = new AlertDialog.Builder(this);
        CongratulationsBuilder.setTitle("Congratulations");
        CongratulationsBuilder.setIcon(R.drawable.pass);
        CongratulationsBuilder.setMessage("恭喜您挑战成功！ 用时: "+chronometer.getText().toString()+"。" + "是否要再次发起挑战？");
        CongratulationsBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this,SelectActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
                GameActivity.this.finish();
            }
        });
        CongratulationsBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(GameActivity.this,MainActivity.class);
                startActivity(intent);
                GameActivity.this.finish();
            }
        });
        CongratulationsBuilder.show();
    }

    // 显示所有雷，扫雷时踩到的雷标红
    private void Over_showAllBombs(int position){
        for (int i = 0; i < thisSquareHasBomb.length; i++) {
            if(thisSquareHasBomb[i] && i != position){
                LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                imageView.setImageResource(R.drawable.bomb);
            }
            if(guessThisSquareIsBomb[i] && !thisSquareHasBomb[i]){
                LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                imageView.setImageResource(R.drawable.wrong);
            }
        }
    }
    private void Success_showAllBombs(){
        for (int i = 0; i < thisSquareHasBomb.length; i++) {
            if(thisSquareHasBomb[i]){
                LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
                ImageView imageView = (ImageView) linearLayout.getChildAt(0);
                imageView.setImageResource(R.drawable.bomb);
            }
        }
    }

}
