package com.example.minesweeper.GridView;


import android.widget.GridView;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

/**
 *  产生雷。!!! 注意这些随机数不能重复!!!
 *  step1: 在 0 ~ (boardWidth*boardHeight) 之间随机选取 bombs 个数作为雷点 ;
 *  step2: 然后将这些数字对应于网格上 ;
 *  step3: 并将这些按钮标为thisSquareHasBomb=true.
 */
public class ProductBombs {

    /**
     * 从（0~网格数）中生成一个随机数组
     * @return bombLocations
     */
    public ArrayList getRandomArray(int bombs){

        Random random = new Random();

        ArrayList<Integer> arrayNum = new ArrayList<>();
        int randomValue;
        while(arrayNum.size() < bombs){
            randomValue = random.nextInt((GameActivity.boardWidth * GameActivity.boardHeight));
            if(!arrayNum.contains(randomValue)){
                arrayNum.add(randomValue);
            }
        }
//        System.out.println(arrayNum);
//        if(arrayNum.stream().distinct().count()-arrayNum.size()<0){
//            System.out.println("有重复");
//        }else{
//            System.out.println("无重复");
//        }
        return arrayNum;
    }

    }


