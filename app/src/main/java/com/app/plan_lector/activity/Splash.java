package com.app.plan_lector.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.plan_lector.R;
import com.crashlytics.android.Crashlytics;
import com.parse.ParseObject;
import com.parse.ParseUser;

import io.fabric.sdk.android.Fabric;

public class Splash extends Activity {

    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.splash);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        new MiTareaAsincrona().execute();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void tareaLarga() {
        try {
            Thread.sleep(200);
        } catch(InterruptedException e) {}
    }

    private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {

            for(int i=1; i<=10; i++) {
                tareaLarga();

                publishProgress(i*10);

                if(isCancelled())
                    break;
            }

            return true;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(Boolean result) {

            ParseUser currentUser = ParseUser.getCurrentUser();

            if(currentUser == null){
                i=new Intent(Splash.this,Login.class);
            }else {
                //String email = currentUser.getEmail();
                String idrol = currentUser.getString("role_id");

                switch (idrol){
                    case "il0Li2XGT5":
                        i=new Intent(Splash.this,MainActivityParent.class);
                        break;
                    case "izykI0EPGl":
                        i=new Intent(Splash.this,MainActivityTeacher.class);
                        break;
                    case "sFmdvOV202":
                        i=new Intent(Splash.this,MainActivityStudent.class);
                        break;
                }
            }
            startActivity(i);
            finish();

        }
    }

}