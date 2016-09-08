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

    private void validarSesion(String s, String s1) {
         if(s.equals("student")){
             i=new Intent(Login.this,MainActivityStudent.class);
         }else{
             if(s.equals("teacher")){
                 i=new Intent(Login.this,MainActivityTeacher.class);
             }else{
                 if(s.equals("parent")){
                     i=new Intent(Login.this,MainActivityParent.class);
                 }else{
                     Toast.makeText(this,"Usuario o password invalido",Toast.LENGTH_LONG).show();
                 }
             }
         }
         startActivity(i);
         finish();
    }
}