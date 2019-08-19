package com.lifeistech.camp.memo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class MemoAdapter extends ArrayAdapter<Memo> {

    private LayoutInflater layoutinflater;

    MemoAdapter(Context context, int textViewResourceId, List<Memo> objects) {
        super(context, textViewResourceId, objects);
        layoutinflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Memo memo = getItem(position);

        if (convertView == null) {
            convertView = layoutinflater.inflate(R.layout.layout_item_memo, null);
        }
        final Memo memo1 = getItem(position);

        TextView titleText = (TextView)convertView.findViewById(R.id.titleText);
        TextView contentText = (TextView)convertView.findViewById(R.id.contentText);
        ImageView imageView = (ImageView)convertView.findViewById(R.id.weatherstatus_imageView);
        LinearLayout linearLayout = (LinearLayout)convertView.findViewById(R.id.linearLayout);
        CheckBox checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);


        titleText.setText(memo.title);
        contentText.setText(memo.content);


        //画像
        if(memo.wether_status_realm == 0){
            imageView.setImageResource(R.drawable.no_file);
        }else if(memo.wether_status_realm == 1){
            imageView.setImageResource(R.drawable.sun);
        }else if(memo.wether_status_realm == 2){
            imageView.setImageResource(R.drawable.cloud);
        }else if(memo.wether_status_realm == 3){
            imageView.setImageResource(R.drawable.ame);
        }

        return convertView;
    }




}
