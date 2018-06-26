package com.huat.qi.appfirst.control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.huat.qi.appfirst.db.PlayerDBHelper;
import com.huat.qi.appfirst.model.Player;

/**
 * Created by Qi on 2018/5/10.
 */

public class PlayerDao {
    //声明各个部分                  ------未实例化
    private PlayerDBHelper helper;
    private SQLiteDatabase db;
    public  PlayerDao(Context context){
        helper = new PlayerDBHelper(context);    // 实例化辅助类
        db = helper.getWritableDatabase();
    }
    public boolean addPlayer(String name,String password){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("Uname", name);
        cv.put("Upassword", password);
        db.insert("player", null, cv);
        Log.e("添加","状态");
//        db.execSQL("insert into player (_id,Uname,Upassword) values (?,?,?)",
//                new Object[] {"", name, password });
        db.close();
        return true;
    }
    public boolean deletePlyer(String name,String password){
        return false;
    }
    public boolean updatePlayer(){
        return false;
    }
    public boolean queryPlayer(String name,String password){
        Cursor cursor = db.rawQuery("select *  from player where Uname = ? and Upassword = ?",
                new String[] { name ,password });
        boolean result = cursor.moveToNext();
        cursor.close();
        db.close();
        return result;
    }
}
