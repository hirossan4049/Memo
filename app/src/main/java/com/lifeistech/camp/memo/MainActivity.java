package com.lifeistech.camp.memo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    public Realm realm;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //openREalm
        realm = Realm.getDefaultInstance();
        listView = (ListView)findViewById(R.id.listView);

        //clickで編集
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Memo memo = (Memo) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("updateDate",memo.updateDate);
                startActivity(intent);
            }
        });
        //長押しで消去
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Memo memo = (Memo)parent.getItemAtPosition(position);
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        Memo realmMemo = realm.where(Memo.class).equalTo("updateDate", memo.updateDate).findFirst();
                        realmMemo.deleteFromRealm();
                    }
                });
                setMemoList();
                Log.d(memo.updateDate,memo.title);
                return false;
            }
        });
    }

    public void setMemoList(){
        //Read Realm
        RealmResults<Memo> results = realm.where(Memo.class).findAll();
        List<Memo> items = realm.copyFromRealm(results);

        MemoAdapter adapter = new MemoAdapter(this,R.layout.layout_item_memo,items);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        setMemoList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void create(View view){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }

}
