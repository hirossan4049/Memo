package com.lifeistech.camp.memo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;

public class CreateActivity extends AppCompatActivity {

    public EditText titleEditText;
    public EditText contentEditText;
    //realm
    public Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        //open realm
        realm = Realm.getDefaultInstance();

        titleEditText = (EditText)findViewById(R.id.titleEditText);
        contentEditText = (EditText)findViewById(R.id.contentEditText);
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

}