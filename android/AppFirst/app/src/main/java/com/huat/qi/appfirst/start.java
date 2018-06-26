package com.huat.qi.appfirst;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class start extends AppCompatActivity{
    private TextView player;             //玩家名
    private Button  start;               //开始
    private Button  end;                //返回
    private Intent  intent;             //跳转的意图

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        getPlayer();
        startonClick();
    }
    public void startonClick(){
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(start.this,"准备进入游戏！" , Toast.LENGTH_SHORT).show();
                intent = new Intent(start.this,game.class);
                enterHome();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(start.this,"返回登录界面！" , Toast.LENGTH_SHORT).show();
                intent = new Intent(start.this,MainActivity.class);
                enterHome();
            }
        });
    }
    //组件初始化
    public  void init(){
        player = (TextView)findViewById(R.id.player);
        start = (Button)findViewById(R.id.start);
        end = (Button)findViewById(R.id.end);
    }
    //接收主界面传来的玩家名字
    public void getPlayer(){
        Bundle bundle = this.getIntent().getExtras();//获得绑定字段
        String name = bundle.getString("user_name");
        player.setText(name);
    }
 // 延迟进入游戏界面           -----等待提示显示完后进入
    private void enterHome(){
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                startActivity(intent);
                finish();
            }
        };
        time.schedule(tk, 2000);
    }
}
