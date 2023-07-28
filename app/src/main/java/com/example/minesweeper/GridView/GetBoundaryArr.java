package com.example.minesweeper.GridView;

import com.example.minesweeper.GameActivity;

/**
 * 功能：产生游戏板边界位置的数组；上 下 左 右 四角
 */
public class GetBoundaryArr {

    // 上边界位置数组
    private void getUpboundary(int[] up ,int w){
        for(int x=0;x<(w-2);x++){
            up[x]=x+1;
        }
    }
    // 四角位置数组,左上 右上 左下 右下
    private void getCorner(int[] corn,int w,int h){
        corn[0]=0;
        corn[1]= w-1;
        corn[2]=w*(h-1);
        corn[3]=w*h-1;
    }
    // 下边界位置数组
    private void getDownboundary(int[] down, int w,int h){
        for(int x=0;x<(w-2);x++){
            down[x]=w*(h-1)+x+1;
        }
    }
    // 左边界位置数组
    private void getLeftboundary(int[] left, int w,int h){
        for(int x=0;x<(h-2);x++){
            left[x]=w*(x+1);
        }
    }
    // 右边界位置数组
    private void   getRightboundary(int[] right,int w, int h){
        for(int x=0;x<(h-2);x++){
            right[x]=w*(x+2)-1;
        }
    }

    public void getBounaryLocation(int[] Up, int[] Down , int[] Left, int[] Right, int[] Corn, int W,int H){
        getUpboundary(Up ,W);
        getDownboundary(Down,W,H);
        getLeftboundary(Left,W,H);
        getRightboundary(Right,W,H);
        getCorner(Corn,W,H);
    }


}
