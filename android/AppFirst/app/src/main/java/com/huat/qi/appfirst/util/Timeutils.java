package com.huat.qi.appfirst.util;

import android.annotation.SuppressLint;
import android.os.Message;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;
//import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by Qi on 2018/5/23.
 */

public class Timeutils {
    private static String TAG="<<<";
    private Timer mTimer = null;
    private TimerTask mTimerTask = null;
    private Handler mHandler = null;
    private static long count = 0;
    private boolean isPause = false;
    private  static  int delay = 1000; //1s
    private  static  int period = 1000; //1s
    private  static  final  int UPDATE_TEXTVIEW = 0;
    TextView  mTextView;
    Button btn;
    @SuppressLint("HandlerLeak")
    public  Timeutils(TextView mTextView){
        this.mTextView = mTextView;
        mHandler = new Handler(){

            public  void handleMessage(Message msg){
                switch (msg.what){
                    case  UPDATE_TEXTVIEW:
                        updateTextView();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public  void  puseTimer(){
        isPause = !isPause;
    }

    private   long  updateTextView(){
        int i =1000;
        long time = count*i;
        SimpleDateFormat sysTimerStr = new SimpleDateFormat("mm:ss");
        //CharSequence sysTimerStr = DateFormat.format("mm:ss",time);
       // mTextView.setText(String.valueOf(sysTimerStr));
        mTextView.setText(String.valueOf(sysTimerStr.format(time)));
        return count;
    }

    public  void  startTimer(long counttimr){
        if(mTimer==null){
            mTimer = new Timer();
        }
        if(mTimerTask == null){
            count =counttimr; // 给时间初值 ---针对暂停时
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.i(TAG,"count"+String.valueOf(count));
                    sendMessage(UPDATE_TEXTVIEW);
                    do {
                        try {
                            Log.i(TAG,"sleep()1000...");
                            Thread.sleep(1000);
                        }catch (InterruptedException e){

                        }
                    }while (isPause);
                    count++;
                }
            };
        }
        if (mTimer!=null &&mTimerTask !=null){
            mTimer.schedule(mTimerTask,delay,period);
        }
    }
    //游戏最终结束时
    public void stop(){
        if (mTimer !=null){
            mTimer.cancel();
            mTimer = null;
        }
        if (mTimerTask != null){
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }


    public  void  sendMessage(int id){
        if (mHandler != null){
            Message message = Message.obtain(mHandler,id);
            mHandler.sendMessage(message);
        }
    }

}
