package com.example.minesweeper;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private DBOpenHelper mDBOpenHelper;
    private Button mBtRegisteractivityRegister;
    private ImageView mIvRegisteractivityBack;
    private EditText mEtRegisteractivityUsername;
    private EditText mEtRegisteractivityPassword1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);
    }

    private void initView(){
        mBtRegisteractivityRegister = findViewById(R.id.btmRegisterStartGame);
        mIvRegisteractivityBack = findViewById(R.id.igvBackMain3);
        mEtRegisteractivityUsername = findViewById(R.id.Registerusername);
        mEtRegisteractivityPassword1 = findViewById(R.id.RegisterPassword);



        mIvRegisteractivityBack.setOnClickListener(this);

        mBtRegisteractivityRegister.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.igvBackMain3: //返回登录页面
                Intent intent1 = new Intent(this, MainActivity.class);
                startActivity(intent1);
                RegisterActivity.this.finish();
                break;

            case R.id.btmRegisterStartGame:    //注册按钮
                //获取用户输入的用户名、密码、验证码
                String username = mEtRegisteractivityUsername.getText().toString().trim();
                String password1 = mEtRegisteractivityPassword1.getText().toString().trim();

                //注册验证
                if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password1)  ) {
                    ArrayList<User> data = mDBOpenHelper.getAllData();
                    boolean match = true;
                    for (int i = 0; i < data.size(); i++) {
                        User user = data.get(i);
                        if (username.equals(user.getName())) {
                            match = false;
                            break;
                        } else {
                            match = true;
                        }
                    }
                    if (match) {
                        //将用户名和密码加入到数据库中
                        mDBOpenHelper.add(username, password1);
                        Intent intent2 = new Intent(this, LogActivity.class);
                        startActivity(intent2);
                        RegisterActivity.this.finish();
                        Toast.makeText(this, "验证通过，注册成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "该用户已存在，请重新输入", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(this, "未完善信息，注册失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}

