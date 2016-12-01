package com.app.plan_lector;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by Gabriela Mejia on 19/4/2016.
 */
public class App extends Application {

    SharedPreferences preferences= null;

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.Parse_APPID))
                .clientKey(getString(R.string.Parse_CLIENTID))
                .build()
        );
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
