package com.example.sy7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    static final  String path="content://com.example.sy7_2.provider/contacts";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button qurey_data = (Button)findViewById(R.id.query);
        qurey_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor =null;
                Uri uri =Uri.parse(path);
                cursor = getContentResolver().query(uri,null,null,null,null);
                if (cursor != null){
                    while(cursor.moveToNext()){
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String sex = cursor.getString(cursor.getColumnIndex("sex"));
                        String phone = cursor.getString(cursor.getColumnIndex("phone"));
                    }
                    cursor.close();
                }
            }
        });
    }
}
