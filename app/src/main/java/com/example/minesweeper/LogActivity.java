package com.example.minesweeper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LogActivity extends AppCompatActivity implements View.OnClickListener {
    private DBOpenHelper mDBOpenHelper;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;
    private ImageView mTvLoginactivityRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.btmLogStartGame);
        mEtLoginactivityUsername = findViewById(R.id.Logusername);
        mEtLoginactivityPassword = findViewById(R.id.LogPassword);
        mTvLoginactivityRegister = findViewById(R.id.igvBackMain2);
        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this);
        mTvLoginactivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.igvBackMain2:
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                LogActivity.this.finish();
                break;
            case R.id.btmLogStartGame:
                String name = mEtLoginactivityUsername.getText().toString().trim();
                String password = mEtLoginactivityPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = false;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (name.equals(user.getName()) && password.equals(user.getPassword())) {
                            match = true;
                            break;
                        } else {
                            match = false;
                        }
                    }
                    if (match) {
                        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                        Intent intent2 = new Intent(this, SelectActivity.class);
                        intent2.putExtra("name",name);
                        startActivity(intent2);
                        LogActivity.this.finish();//销毁此Activity
                    } else {
                        Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}