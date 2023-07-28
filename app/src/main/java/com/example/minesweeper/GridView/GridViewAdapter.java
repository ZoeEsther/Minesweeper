package com.example.minesweeper.GridView;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.example.minesweeper.GameActivity;
import com.example.minesweeper.MainActivity;
import com.example.minesweeper.R;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;


public class GridViewAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;

    public GridViewAdapter(Context context){
        mLayoutInflater = LayoutInflater.from(context);
    }
    // 这里可改变放置item的个数
    @Override
    public int getCount() {
        int itemNumber = GameActivity.boardWidth * GameActivity.boardHeight;
        return itemNumber;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // 声明layout_grid_item里的控件
    static class ViewHolder{
        public ImageView imageView;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null){
            view = mLayoutInflater.inflate(R.layout.layout_grid_item,null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) view.findViewById(R.id.GameSquare);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }

        PlaceBombs(i, holder.imageView);

        return view;
    }

    /**
     *  在选好游戏等级后，雷的个数以及雷的位置将被随机确定在 bombsLocation 这个数组中。
     *  该函数的功能是：将这些雷根据位置放置在游戏板上，即在适配器中就定义好哪些方块是雷。
     */
    private void PlaceBombs(int position, ImageView imageView){

        if(GameActivity.bombsLocation.contains(position)){
//            imageView.setImageResource(R.drawable.bomb);
            GameActivity.thisSquareHasBomb[position] = true;
        }
    }

}
