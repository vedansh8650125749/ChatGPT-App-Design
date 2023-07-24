package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.Calendar;

public class SessionManager {
    private static final String PREF_NAME = "UserSession";
    private static final String KEY_LOGIN_TIMESTAMP = "loginTimestamp";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLoginTimestamp() {
        long timestamp = Calendar.getInstance().getTimeInMillis();
        editor.putLong(KEY_LOGIN_TIMESTAMP, timestamp);
        editor.apply();
    }

    public long getLoginTimestamp() {
        return pref.getLong(KEY_LOGIN_TIMESTAMP, 0);
    }
}
