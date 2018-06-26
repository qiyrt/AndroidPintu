package com.huat.qi.appfirst;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.huat.qi.appfirst.control.PlayerDao;
import com.huat.qi.appfirst.db.PlayerDBHelper;

//目前已有用户   12    12
public class MainActivity extends AppCompatActivity {
    private EditText user_name,user_password;   //用户名  密码
    private Button login ;                       //登录按钮
    private CheckBox  rem_password;             //记住密码
    private Button  createUser;                 //注册用户
    private PlayerDao p;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        remember();
        login();
        createNewUser();
    }
    public void init(){
        //一、获取界面的各个组件
        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        login = (Button) findViewById(R.id.login);
        rem_password = (CheckBox) findViewById(R.id.rem_password);
        createUser = (Button)findViewById(R.id.createUser);
    }
    public  void remember(){
        //二、记住密码的监听机制
        //5、启动时读取SharedPreferences的内容  data文件
        SharedPreferences preferences = getSharedPreferences("data",MODE_PRIVATE);
        boolean isRemember =  preferences.getBoolean("remember",false);
        if (isRemember){
            Log.d("TAG","remember");
            String user = preferences.getString("user_name","");
            String password = preferences.getString("user_password","");
            user_name.setText(user);
            user_password.setText(password);
            rem_password.setChecked(true);
        }else{
            rem_password.setChecked(false);
        }

        //点击记住密码后的机制
        rem_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1、创建 存储数据 访问模式   edit  作为局部的全局变量  以便后面的方法获取
                SharedPreferences.Editor editor = getSharedPreferences("data",MODE_PRIVATE).edit();
                if (rem_password.isChecked()) {
                    //下次登录自动记住密码  sharedPreferences 处理
                    String  user = user_name.getText().toString();
                    String  password = user_password.getText().toString();
                    //2、存放数据
                    editor.putString("user_name",user);
                    editor.putString("user_password",password);
                    editor.putBoolean("remember",true);
                    //3、提交
                    editor.commit();
                    //4、信息提示
                    Toast.makeText(MainActivity.this,"记住密码已生效",Toast.LENGTH_LONG).show();
                } else {
                    //没有被选中的时候   不再使用记住密码功能
                    editor.clear();
                    editor.commit();
                    Toast.makeText(MainActivity.this,"取消记住密码已生效",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public  void login(){
        //三、登录按钮监听机制
//        final DBHelper help =new DBHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                PlayerDBHelper helper = new PlayerDBHelper(getApplicationContext());
//

                p = new PlayerDao(MainActivity.this);
                //2、如果有这个人  则跳转界面
                if(p.queryPlayer(user_name.getText().toString(),user_password.getText().toString())&&user_name.getText().toString()!=null&&user_password.getText().toString()!=null){
                    Log.e("数据库","查询成功");
//                if(true){
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this,start.class);
                    //得到各项值
                    String  user = user_name.getText().toString();
                    String  password = user_password.getText().toString();
                    //传递多个数据用Bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("user_name",user);
                    bundle.putString("user_password",password);
                    //将bundle对象assign给Intent
                    intent.putExtras(bundle);
//                    helper.close();
                    startActivity(intent);

                }else{
                    //3、无此人， 提示登录失败
                    Toast.makeText(MainActivity.this,"不存在此人，请重新输入！",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
   //注册用户
    public void createNewUser(){
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this,register.class);
                startActivity(intent);
            }
        });
    }
}
