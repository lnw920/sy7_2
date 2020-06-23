package com.example.sy7_2;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.CancellationSignal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    public MyContentProvider(){
    }

    public static final int Table_DIR=0;
    public static final int Name_ITEM=1;
    private MyDataBase myDataBase;
    private static UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI("com.example.sy7_2.provider","contacts",Table_DIR);
        uriMatcher.addURI("com.example.sy7_2.provider","contacts",Name_ITEM);
    }


    @Override
    public boolean onCreate() {
        myDataBase = new MyDataBase(getContext(),"contacts.db",null,2);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor=null;
        SQLiteDatabase db = myDataBase.getReadableDatabase();
        switch (uriMatcher.match(uri)){
            case Table_DIR:
                cursor = db.query("contacts",projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Name_ITEM:
                String person_id = uri.getPathSegments().get(1);
                cursor = db.query("contacts",projection,"id=?",new String[]{person_id},null,null,sortOrder);
                break;
            default:
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        switch (uriMatcher.match(uri)){
            case Table_DIR:
                return "vnd.android.cursor.dir/vnd.com.example.sy7_2.provider.contacts";
            case Name_ITEM:
                return  "vnd.android.cursor.item/vnd.com.example.sy7_2.provider.contacts";
            default:
                break;
        }
        return null;
    }
}
