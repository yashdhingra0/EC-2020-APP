package com.example.ec_2020_app.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;


public class IncomingSms extends BroadcastReceiver {
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");
                for (int i = 0; i < pdusObj.length; i++) {
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();
                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();
                    mListener.messageReceived(message);
                }
            }
        } catch (Exception e) {

        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }

    public static void removeListenr(){
        mListener = null;
    }
}

