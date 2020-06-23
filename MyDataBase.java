package com.example.sy7_2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDataBase extends SQLiteOpenHelper {

    public static final String Create_DB = "create table contacts("
        +"id integer primary key autoincrement,"
        +"name varchar(20),"
        +"phone varchar(20),"
        +"sex varchar(20))";

    private Context mContext;
    public MyDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version){
        super(context,name,factory,version);
        mContext=context;
    }

    public MyDataBase(Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_DB);
        Toast.makeText(mContext,"数据库创建成功",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
