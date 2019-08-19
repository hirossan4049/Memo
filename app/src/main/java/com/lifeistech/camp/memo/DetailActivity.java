package com.lifeistech.camp.memo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity {
    public Realm realm;

    public EditText titleText;
    public EditText contentText;
    public int weather_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        realm = Realm.getDefaultInstance();
        titleText = (EditText)findViewById(R.id.titleEditText);
        contentText=(EditText)findViewById(R.id.contentEditText);
        showData();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
    public void showData(){
        final Memo memo = realm.where(Memo.class).equalTo("updateDate",getIntent().getStringExtra("updateDate")).findFirst();

        titleText.setText(memo.title);
        contentText.setText(memo.content);
    }



    public void sun(View v){
        weather_status = 1;
        Log.d("Weather_Click",weather_status+"");
    }
    public void cloud(View v){
        weather_status = 2;
        Log.d("Weather_Click",weather_status+"");

    }
    public void rain(View v){
        weather_status = 3;
        Log.d("Weather_Click",weather_status+"");

    }





    public void update(View view){
        final Memo memo = realm.where(Memo.class).equalTo("updateDate",getIntent().getStringExtra("updateDate")).findFirst();

        //update
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                memo.title = titleText.getText().toString();
                memo.content = titleText.getText().toString();
                memo.wether_status_realm = weather_status;

            }
        });

        finish();
    }
}