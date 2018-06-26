package com.huat.qi.appfirst;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.huat.qi.appfirst.util.Timeutils;

import java.util.Random;

public class game extends AppCompatActivity implements View.OnClickListener {
    private Button stop ;
    private Button go_on ;
    private Button  go_back ;
    private TextView timer;

    //图片
    private ImageButton  ib_00;
    private ImageButton  ib_01;
    private ImageButton  ib_02;
    private ImageButton  ib_10;
    private ImageButton  ib_11;
    private ImageButton  ib_12;
    private ImageButton  ib_20;
    private ImageButton  ib_21;
    private ImageButton  ib_22;



    private int imageX = 3;
    private int imageY = 3;
    private int imgCount = imageX * imageY;
    private int length = imgCount;
    private int blankSwap = length - 1;
    private int blankImgId = R.id.ib_02_02;

    private boolean timeSwitch=true;

    //时间
    Timeutils timeutils;
    private int time;
    private  long  pausetime;


//    private Handler mHandler=new Handler(){
//        public void handleMessage(Message msg) {
//            if(msg.what==1){
//                String time=(String) msg.obj;
//                timer.setText(time);
//            }
//        };
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        init();
        Random();
        //计时开始
        recordTimer();
        gameonClick();

        //线程---------------------------------------------------------
//        new Thread(){public void run() {
//            time=0;
//            while(timeSwitch==true){
//                String strTime=time+"";
//                Message msg = Message.obtain();
//                msg.what=1;
//                msg.obj=strTime;
//                mHandler.sendMessage(msg);
//                try {
//                    Thread.sleep(1000);
//                    time++;
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//        }.start();

    }
    public  void  init(){
        stop = (Button)findViewById(R.id.stop);
        go_on = (Button)findViewById(R.id.go_on);
        go_back = (Button)findViewById(R.id.go_back);
        timer = (TextView)findViewById(R.id.timer);

        ib_00 = (ImageButton) findViewById(R.id.ib_00_00);
        ib_01 = (ImageButton) findViewById(R.id.ib_00_01);
        ib_02 = (ImageButton) findViewById(R.id.ib_00_02);
        ib_10 = (ImageButton) findViewById(R.id.ib_01_00);
        ib_11 = (ImageButton) findViewById(R.id.ib_01_01);
        ib_12 = (ImageButton) findViewById(R.id.ib_01_02);
        ib_20 = (ImageButton) findViewById(R.id.ib_02_00);
        ib_21 = (ImageButton) findViewById(R.id.ib_02_01);
        ib_22 = (ImageButton) findViewById(R.id.ib_02_02);

        ib_00.setOnClickListener(this);
        ib_01.setOnClickListener(this);
        ib_02.setOnClickListener(this);
        ib_10.setOnClickListener(this);
        ib_11.setOnClickListener(this);
        ib_12.setOnClickListener(this);
        ib_20.setOnClickListener(this);
        ib_21.setOnClickListener(this);
        ib_22.setOnClickListener(this);
    }
   public  void  gameonClick(){
        //停止
       stop.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //1、计时暂停
//               //2、游戏界面不可操作
               timeutils.puseTimer();
               disableClick();
               Toast.makeText(game.this,"暂停游戏！" , Toast.LENGTH_SHORT).show();
           }
       });
       //继续
       go_on.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //1、计时继续
               //2、游戏界面可操作
               timeutils.puseTimer();
               Click();
               Toast.makeText(game.this,"继续游戏！" , Toast.LENGTH_SHORT).show();
           }
       });
       //返回---退出游戏
       go_back.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //1、计时结束
               timeutils.stop();
               //2、获取成绩、保存成绩
                   // time.getText().toString();
               //3、返回开始界面
               Intent intent = new Intent();
               intent.setClass(game.this,MainActivity.class);
               startActivity(intent);

               Toast.makeText(game.this,"返回登录！" , Toast.LENGTH_SHORT).show();
           }
       });
   }
    //计时器
    public void recordTimer(){
        //当进入这个界面后----------该函数立即启用
        //当暂停按钮按下后，时间停止
        //当继续按钮按下后，时间继续计时
        timeutils = new Timeutils(timer);
        timeutils.startTimer(0);

    }

    //游戏结束
   // public void Gameover(){
     //   Toast.makeText(game.this,"GameOver！",Toast.LENGTH_LONG).show();
        //获取时间
       // time.getText().toString();
        //将时间作为成绩存到数据库中

    //}


    // 声明一个图片数组的下标,随机排列这个数组
    private int[] imageIndex = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
    private int[] imageId = { R.mipmap.img_liuyifei_00x00,
            R.mipmap.img_liuyifei_00x01, R.mipmap.img_liuyifei_00x02,
            R.mipmap.img_liuyifei_01x00, R.mipmap.img_liuyifei_01x01,
            R.mipmap.img_liuyifei_01x02, R.mipmap.img_liuyifei_02x00,
            R.mipmap.img_liuyifei_02x01, R.mipmap.img_liuyifei_02x02, };

    // 打乱顺序
    private void Random() {
        int ran1, ran2;
        for (int i = 0; i < 20; i++) {
            ran1 = new Random().nextInt(length - 1);
            do {
                ran2 = new Random().nextInt(length - 1);
                if (ran1 != ran2) {
                    break;
                }
            } while (true);
            Swap(ran1, ran2);
        }
        // 随机排序
        ib_00.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[0]]));
        ib_01.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[1]]));
        ib_02.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[2]]));
        ib_10.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[3]]));
        ib_11.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[4]]));
        ib_12.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[5]]));
        ib_20.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[6]]));
        ib_21.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[7]]));
        ib_22.setImageDrawable(getResources().getDrawable(
                imageId[imageIndex[8]]));

        // ib_00.getDrawable().equals(R.id.ib_02_02);
    }
   //交换
   private void Swap(int ran1, int ran2) {
       int temp = imageIndex[ran1];
       imageIndex[ran1] = imageIndex[ran2];
       imageIndex[ran2] = temp;
   }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_00_00:
                move(R.id.ib_00_00, 0);
                break;
            case R.id.ib_00_01:
                move(R.id.ib_00_01, 1);
                break;
            case R.id.ib_00_02:
                move(R.id.ib_00_02, 2);
                break;
            case R.id.ib_01_00:
                move(R.id.ib_01_00, 3);
                break;
            case R.id.ib_01_01:
                move(R.id.ib_01_01, 4);
                break;
            case R.id.ib_01_02:
                move(R.id.ib_01_02, 5);
                break;
            case R.id.ib_02_00:
                move(R.id.ib_02_00, 6);
                break;
            case R.id.ib_02_01:
                move(R.id.ib_02_01, 7);
                break;
            case R.id.ib_02_02:
                move(R.id.ib_02_02, 8);
                break;
        }
    }


    public void move(int imageBtnId, int site) {
        // 相邻图片坐标
        int sizeX = site / imageX;
        int sizeY = site % imageY;

        // 空白图片坐标
        int blackX = blankSwap / imageX;
        int blackY = blankSwap % imageY;

        int x = Math.abs(sizeX - blackX);
        int y = Math.abs(sizeY - blackY);

        // 满足条件
        if ((x == 0 && y == 1) || (x == 1 && y == 0)) {
            // 点击图片的id
            ImageButton clickButton = (ImageButton) findViewById(imageBtnId);
            // 当前点击的图片设置为不可见状态
            clickButton.setVisibility(View.INVISIBLE);
            // 空白图片的id
            ImageButton blankButton = (ImageButton) findViewById(blankImgId);
            // 用点击图片覆盖空白图片
            blankButton.setImageDrawable(getResources().getDrawable(
                    imageId[imageIndex[site]]));
            // 将之前空白图片设置为可见
            blankButton.setVisibility(View.VISIBLE);
            // 交换标记
            Swap(site, blankSwap);
            // 让上面能够重新运算空白的位置
            blankSwap = site;
            // 交换两者的ID
            blankImgId = imageBtnId;
            GameOver();
        }
    }


    private void GameOver() {
        boolean loop = true;
        for (int i = 0; i < 9; i++) {
            if (imageIndex[i] != i) {
                loop = false;
            }
        }
        if (loop) {
            timeSwitch=false;
            ib_00.setClickable(false);
            ib_01.setClickable(false);
            ib_02.setClickable(false);
            ib_10.setClickable(false);
            ib_11.setClickable(false);
            ib_12.setClickable(false);
            ib_20.setClickable(false);
            ib_21.setClickable(false);
            ib_22.setClickable(false);
            ib_22.setImageDrawable(getResources().getDrawable(imageId[8]));
            ib_22.setVisibility(View.VISIBLE);
            timeutils.stop();
            AlertDialog.Builder builder = new AlertDialog.Builder(game.this);
            builder.setMessage("恭喜，拼图成功了！所用时间为：" + timer.getText().toString()).setPositiveButton("确定", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    timeutils.stop();

                    Intent intent = new Intent();
                    intent.setClass(game.this,grade.class);
                    //得到各项值
                    String  gradeTime = timer.getText().toString();
                    //传递多个数据用Bundle
                    Bundle bundle = new Bundle();
                    bundle.putString("timer",gradeTime);
                    //将bundle对象assign给Intent
                    intent.putExtras(bundle);
                    startActivity(intent);
                    dialog.dismiss();
                }
            });
            builder.create();
            builder.show();

        }
    }
    public  void  disableClick(){
        ib_00.setClickable(false);
        ib_01.setClickable(false);
        ib_02.setClickable(false);
        ib_10.setClickable(false);
        ib_11.setClickable(false);
        ib_12.setClickable(false);
        ib_20.setClickable(false);
        ib_21.setClickable(false);
        ib_22.setClickable(false);
    }
    public  void  Click(){
        ib_00.setClickable(true);
        ib_01.setClickable(true);
        ib_02.setClickable(true);
        ib_10.setClickable(true);
        ib_11.setClickable(true);
        ib_12.setClickable(true);
        ib_20.setClickable(true);
        ib_21.setClickable(true);
        ib_22.setClickable(true);
    }
}
