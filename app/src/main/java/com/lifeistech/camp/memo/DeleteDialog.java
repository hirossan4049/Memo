package com.lifeistech.camp.memo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class DeleteDialog extends DialogFragment {

    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setTitle("警告！");
        dialogBuilder.setMessage("本当に単語を消去しますか？");
        dialogBuilder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Context context;
                CharSequence text;
                Toast toast = Toast.makeText(getActivity(),"OKを押しました",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        dialogBuilder.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return dialogBuilder.create();
    }
}
