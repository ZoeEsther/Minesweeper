package com.example.minesweeper.GridView;


import static com.example.minesweeper.GameActivity.Corner;
import static com.example.minesweeper.GameActivity.mGv;

import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.R;

import java.util.Arrays;

public class CheckSquare {

    // 检查当前块周围的8个块，返回当前块周围的雷数
    private int checksquare(int i){
        int count=0;
        int w = GameActivity.boardWidth;
        int h = GameActivity.boardHeight;
        if(i>=0 && i<(w*h)){
            if(IsCornerSquare(i)){
                if(GameActivity.Corner[0]==i){
                    if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i+w+1]) {count=count+1;}
                }else if(GameActivity.Corner[1]==i){
                    if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i+w-1]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
                }else if(GameActivity.Corner[2]==i){
                    if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i-w+1]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
                }else{
                    if(GameActivity.thisSquareHasBomb[i-w-1]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                    if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                }
            }else if(IsUpboundarySquare(i)){
                if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w+1]) {count=count+1;}
            }else if(IsDownboundarySquare(i)){
                if(GameActivity.thisSquareHasBomb[i-w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
            }else if(IsLeftboundarySquare(i)){
                if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w+1]) {count=count+1;}
            }else if(IsRightboundarySquare(i)){
                if(GameActivity.thisSquareHasBomb[i-w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
            }else{
                if(GameActivity.thisSquareHasBomb[i-w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-w+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w-1]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w]) {count=count+1;}
                if(GameActivity.thisSquareHasBomb[i+w+1]) {count=count+1;}
            }
        }
        return count;
    }
    /**
     * 如果当前所在块周围8个块的雷数为0，则进行递归扩展查雷
     * @param p 游戏块的位置
     */
    public void expandCheckBombs(int p){

        int count=-1;
        int w = GameActivity.boardWidth;

        if(p>=0 && p<(GameActivity.boardWidth*GameActivity.boardHeight)){
            if(!GameActivity.thisSquareHasTraversed[p] && !GameActivity.guessThisSquareIsBomb[p]){
                count = checksquare(p);
                ChangeImageBackgroud(count,p);
                GameActivity.thisSquareHasTraversed[p] = true;
            }
        }

        if (count == 0 ){
            if (!IsLeftboundarySquare(p) && !IsRightboundarySquare(p)
                && !IsUpboundarySquare(p) && !IsDownboundarySquare(p)
                && !IsCornerSquare(p))
            {
                expandCheckBombs(p-w-1);
                expandCheckBombs(p-w);
                expandCheckBombs(p-w+1);
                expandCheckBombs(p-1);
                expandCheckBombs(p+1);
                expandCheckBombs(p+w-1);
                expandCheckBombs(p+w);
                expandCheckBombs(p+w+1);
            }
            else if(IsLeftboundarySquare(p)){
                expandCheckBombs(p-w);
                expandCheckBombs(p-w+1);
                expandCheckBombs(p+1);
                expandCheckBombs(p+w);
                expandCheckBombs(p+w+1);
            }
            else if(IsRightboundarySquare(p)){
                expandCheckBombs(p-w-1);
                expandCheckBombs(p-w);
                expandCheckBombs(p-1);
                expandCheckBombs(p+w-1);
                expandCheckBombs(p+w);
            }
            else if(IsUpboundarySquare(p)){
                expandCheckBombs(p-1);
                expandCheckBombs(p+1);
                expandCheckBombs(p+w-1);
                expandCheckBombs(p+w);
                expandCheckBombs(p+w+1);
            }
            else if(IsDownboundarySquare(p)){
                expandCheckBombs(p-w-1);
                expandCheckBombs(p-w);
                expandCheckBombs(p-w+1);
                expandCheckBombs(p-1);
                expandCheckBombs(p+1);
            }
            else if(p==Corner[0]){
                expandCheckBombs(p+1);
                expandCheckBombs(p+w);
                expandCheckBombs(p+w+1);
            }
            else if(p==Corner[1]){
                expandCheckBombs(p-1);
                expandCheckBombs(p+w-1);
                expandCheckBombs(p+w);
            }
            else if(p==Corner[2]){
                expandCheckBombs(p-w);
                expandCheckBombs(p-w+1);
                expandCheckBombs(p+1);
            }
            else if(p==Corner[3]){
                expandCheckBombs(p-w-1);
                expandCheckBombs(p-w);
                expandCheckBombs(p-1);
            }
        }
    }

    // 改变已经遍历过的游戏块的背景图
    private void ChangeImageBackgroud(int count,int i){

        LinearLayout linearLayout = (LinearLayout) mGv.getChildAt(i);
        ImageView imageView = (ImageView) linearLayout.getChildAt(0);

        switch (count){
            case 0: {imageView.setImageResource(R.drawable.p0);break;}
            case 1: {imageView.setImageResource(R.drawable.p1);break;}
            case 2: {imageView.setImageResource(R.drawable.p2);break;}
            case 3: {imageView.setImageResource(R.drawable.p3);break;}
            case 4: {imageView.setImageResource(R.drawable.p4);break;}
            case 5: {imageView.setImageResource(R.drawable.p5);break;}
            case 6: {imageView.setImageResource(R.drawable.p6);break;}
            case 7: {imageView.setImageResource(R.drawable.p7);break;}
            case 8: {imageView.setImageResource(R.drawable.p8);break;}
        }
    }

    // 判断是否为上边界块
    private boolean IsUpboundarySquare(int i){
        return Arrays.stream(GameActivity.Upboundary).anyMatch(x -> x == i);
    }
    // 判断是否为下边界块
    private boolean IsDownboundarySquare(int i){
        return Arrays.stream(GameActivity.Downboundary).anyMatch(x -> x == i);
    }
    // 判断是否为左边界块
    private boolean IsLeftboundarySquare(int i){
        return Arrays.stream(GameActivity.Leftboundary).anyMatch(x -> x == i);
    }
    // 判断是否为右边界块
    private boolean IsRightboundarySquare(int i){
        return Arrays.stream(GameActivity.Rightboundary).anyMatch(x -> x == i);
    }
    // 判断是否为边界角
    private boolean IsCornerSquare(int i){
        return Arrays.stream(GameActivity.Corner).anyMatch(x -> x == i);
    }

    //判断游戏是否成功
    public boolean IsGameSuccess(){
        int TraverNumber=0;

        for (int i = 0; i < GameActivity.thisSquareHasTraversed.length; i++) {
            if(GameActivity.thisSquareHasTraversed[i]){
                TraverNumber++;
            }
        }

        if(TraverNumber == (GameActivity.boardHeight*GameActivity.boardWidth)){
            if (Arrays.equals(GameActivity.thisSquareHasBomb,GameActivity.guessThisSquareIsBomb)){
                System.out.println("游戏成功");
                return true;
            }
        }
        return false;
    }

}
