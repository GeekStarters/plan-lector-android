package com.app.plan_lector.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.student.MainActivityStudent;
import com.app.plan_lector.activity.teacher.MainActivityTeacher;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button login;
    Intent i;
    private EditText user,contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        init();
    }

    private void init() {
        user= (EditText)findViewById(R.id.user);
        contra=(EditText)findViewById(R.id.contra);
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                validarSesion(user.getText()+"",contra.getText()+"");
                break;
        }
    }

    private void validarSesion(final String username, final String password) {

        ParseUser.logInInBackground(username, password, new LogInCallback() {

            @Override
            public void done(ParseUser user, ParseException e) {


                if(user != null ){
                    String email = user.getEmail();
                    String idrol = user.getString("role_id");

                    switch (idrol){
                        case "il0Li2XGT5":
                            i=new Intent(Login.this,MainActivityParent.class);
                            break;
                        case "izykI0EPGl":
                            i=new Intent(Login.this,MainActivityTeacher.class);
                            break;
                        case "sFmdvOV202":
                            i=new Intent(Login.this,MainActivityStudent.class);
                            i.putExtra("var",1);
                            break;
                    }

                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(getApplicationContext(),"Ese Usuario no exite. Por favor registrese",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });



    }
}