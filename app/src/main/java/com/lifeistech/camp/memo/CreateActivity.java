package com.lifeistech.camp.memo;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;

import io.realm.Realm;

public class CreateActivity extends AppCompatActivity {

    public EditText titleEditText;
    public EditText contentEditText;
    //realm
    public Realm realm;
    public int weather_status;
    public String selected_color;
    //weather_id_button
    Button sun;
    Button rain;
    Button cloud;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //open realm
        realm = Realm.getDefaultInstance();

        titleEditText = (EditText)findViewById(R.id.titleEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);
        weather_status = 0;
        //weather_button
        sun = (Button)findViewById(R.id.sun);
        rain = (Button)findViewById(R.id.rain);
        cloud = (Button)findViewById(R.id.cloud);


        //============スピナー系==============//
        //Spinner 取得
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        //Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // スピナー要素の文字列を取得
                String selectedItemString = (String) parent.getItemAtPosition(position);
                Log.d("selected_color",selectedItemString);
                selected_color = selectedItemString;
            }
            //選択されていないときの処理
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    //save
    public void save(final String title,final String updateDate,final String content){
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm bgRealm){
                Memo memo = realm.createObject(Memo.class);
                memo.title = title;
                memo.updateDate = updateDate;
                memo.content = content;
                memo.wether_status_realm = weather_status;
            }

        });
    }






    public void create(View view){
        //titleを取得
        String title = titleEditText.getText().toString();

        //Date
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPANESE);
        String updateDate = sdf.format(date);

        //contentを取得
        String content = contentEditText.getText().toString();
//      check!
        check(title,updateDate,content);

        //save
        save(title,updateDate,content);
        finish();


    }

    public void check(String title,String updateDate,String content){

        Memo memo = new Memo();

        memo.title = title;
        memo.updateDate = updateDate;
        memo.content = content;


        Log.d("Memo",memo.title);
        Log.d("Memo",memo.updateDate);
        Log.d("Memo",memo.content);
    }


    public void sun(View v){
        weather_status = 1;
        Log.d("Weather_Click",weather_status+"");
        sun.setBackgroundColor(Color.RED);
        rain.setBackgroundResource(android.R.drawable.btn_default);
        cloud.setBackgroundResource(android.R.drawable.btn_default);
//        sun.setBackgroundResource(android.R.drawable.btn_dialog);　
    }
    public void cloud(View v){
        weather_status = 2;
        Log.d("Weather_Click",weather_status+"");
        cloud.setBackgroundColor(Color.RED);
        sun.setBackgroundResource(android.R.drawable.btn_default);
        rain.setBackgroundResource(android.R.drawable.btn_default);
    }
    public void rain(View v){
        weather_status = 3;
        Log.d("Weather_Click",weather_status+"");
        rain.setBackgroundColor(Color.RED);
        sun.setBackgroundResource(android.R.drawable.btn_default);
        cloud.setBackgroundResource(android.R.drawable.btn_default);


    }

}