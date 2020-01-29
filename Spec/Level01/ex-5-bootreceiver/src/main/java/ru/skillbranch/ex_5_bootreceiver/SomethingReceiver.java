package ru.skillbranch.ex_5_bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class SomethingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(
                context,
                "Something received",
                Toast.LENGTH_SHORT
        ).show();
    }
}
