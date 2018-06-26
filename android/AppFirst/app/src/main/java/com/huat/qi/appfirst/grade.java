package com.huat.qi.appfirst;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.huat.qi.appfirst.util.GradeUtils;

public class grade extends AppCompatActivity {
    private TextView grade;
    private Button again;
    private  Button  end;
    private  Intent  intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);

        //成绩显示
        grade = (TextView)findViewById(R.id.grade);
        Bundle bundle = this.getIntent().getExtras();//获得绑定字段
        String name = bundle.getString("timer");
        grade.setText(name);

        //重玩
        again = (Button)findViewById(R.id.again);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                intent = new Intent();
//                intent.setClass(grade.this,game.class);
//                startActivity(intent);
                new AsyncTask<Void,Void,Void>() {
                    String result1=null;
                    @Override
                    protected Void doInBackground(Void... params) {
                        //必须开启子线程访问网络
                        //提交数据到服务器
                        result1= GradeUtils.connect("10.13.18.183", grade.getText().toString().trim());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        if(result1==null){

                            return ;
                        }
                        if(result1.equals("welldone")){
                            startActivity(new Intent(grade.this,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(grade.this,result1,300).show();
                        }
                        super.onPostExecute(result);
                    }


                }.execute();
            }
        });

        //退出游戏
        end =  (Button)findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                intent = new Intent();
                intent.setClass(grade.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
