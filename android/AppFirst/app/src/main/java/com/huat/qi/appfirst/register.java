package com.huat.qi.appfirst;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.huat.qi.appfirst.control.PlayerDao;

public class register extends AppCompatActivity {
    private EditText name;
    private EditText password;
    private Button register;                       //注册按钮
    private Button backbtn;                  //返回按钮
    private PlayerDao playerDao;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        addListenerRegister();
        back();
    }
    //组件注册
    public void init(){
        name = (EditText)findViewById(R.id.name);
        password = (EditText)findViewById(R.id.password);
        register = (Button)findViewById(R.id.register);
        backbtn = (Button)findViewById(R.id.backbtn);
    }
    //注册按钮的监听
    public void  addListenerRegister(){
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playerDao = new PlayerDao(register.this);
                Log.e("添加","状态");
                if(playerDao.addPlayer(name.getText().toString(),password.getText().toString())&&name.getText().toString()!=null&&password.getText().toString()!=null){
                    Toast.makeText(register.this,"操作成功！",Toast.LENGTH_LONG).show();
                    Log.e("添加","成功");
                }else{
                    Toast.makeText(register.this,"注册失败，请重新注册！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    //返回按钮
    public void back(){
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
