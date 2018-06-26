package com.huat.qi.appfirst.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Qi on 2018/5/10.
 */

public class PlayerDBHelper extends SQLiteOpenHelper {
    //（1）设置数据库名称
    private static final String  db_name = "player.db";
    //（2）设置数据表名称
//    private static final String  tb_name = "player";
    //(3) 设置数据库版本号
    private static final int database_version = 1;
    public PlayerDBHelper(Context context){
        super(context,db_name,null,database_version);
    }
    public PlayerDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createPlayertable = "CREATE TABLE player("+
                "_id integer primary key autoincrement,"
                +"Uname varchar(30) not null,"
                +"Upassword varchar(10) not null);";
        sqLiteDatabase.execSQL(createPlayertable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public SQLiteDatabase getConnection(){
        SQLiteDatabase db = getWritableDatabase();
        return db;
    }
    public  void  close(SQLiteDatabase db){
        db.close();
    }
}
