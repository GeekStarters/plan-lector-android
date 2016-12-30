package com.app.plan_lector.activity.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.activity.Login;
import com.app.plan_lector.fragment.ResumenBook;
import com.app.plan_lector.fragment.student.Games;
import com.app.plan_lector.fragment.student.Library;
import com.app.plan_lector.fragment.student.MyAccountStudent;
import com.app.plan_lector.fragment.student.Rank;
import com.app.plan_lector.fragment.student.Report;
import com.navdrawer.SimpleSideDrawer;
import com.parse.ParseUser;

public class MainActivityStudent extends AppCompatActivity implements View.OnClickListener {

    private SimpleSideDrawer mNav;
    private LinearLayout books, report, games, rank, account, logout;
    private TextView logo;
    private ImageView home;
    private Activity context;

    public MainActivityStudent() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
        }
        context = this;
        setContentView(R.layout.activity_main);
        ParseUser currentUser = ParseUser.getCurrentUser();
        setToolbar();
        init();
        selectItem(getIntent().getIntExtra("var",1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mNav.toggleLeftDrawer();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void init() {
        mNav = new SimpleSideDrawer(this);
        mNav.setLeftBehindContentView(R.layout.sidemenu_student);
        home = (ImageView) mNav.findViewById(R.id.logo);
        books = (LinearLayout) mNav.findViewById(R.id.books);
        rank = (LinearLayout) mNav.findViewById(R.id.rank);
        report = (LinearLayout) mNav.findViewById(R.id.report);
        account = (LinearLayout) mNav.findViewById(R.id.account);
        logout = (LinearLayout) mNav.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        home.setOnClickListener(this);
        report.setOnClickListener(this);
        rank.setOnClickListener(this);
        books.setOnClickListener(this);
        account.setOnClickListener(this);
    }

    public void selectItem(int position) {
        switch (position) {
            case 1:
                Library biblio = new Library();
                Bundle bu = new Bundle();
                biblio.setArguments(bu);
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, biblio)
                        .commit();
                logo.setText("Mi Biblioteca");
                break;
            case 2:
                Games games = new Games();
                Bundle bun = new Bundle();
                bun.putString("RESUL",getIntent().getStringExtra("descrip"));
                games.setArguments(bun);
                FragmentManager fragmentManager2 = getSupportFragmentManager();
                fragmentManager2
                        .beginTransaction()
                        .replace(R.id.main_content, games)
                        .commit();
                logo.setText("Juegos");
                break;
            case 3:
                Rank ranking = new Rank();
                Bundle bund = new Bundle();
                ranking.setArguments(bund);
                FragmentManager fragmentManager3 = getSupportFragmentManager();
                fragmentManager3
                        .beginTransaction()
                        .replace(R.id.main_content, ranking)
                        .commit();
                logo.setText("Ranking");
                break;
            case 4:
                Report cartilla = new Report();
                Bundle bundl = new Bundle();
                cartilla.setArguments(bundl);
                FragmentManager fragmentManager4 = getSupportFragmentManager();
                fragmentManager4
                        .beginTransaction()
                        .replace(R.id.main_content, cartilla)
                        .commit();
                logo.setText("Cartilla");
                break;
            case 5:
                MyAccountStudent cuenta = new MyAccountStudent();
                Bundle bundle = new Bundle();
                cuenta.setArguments(bundle);
                FragmentManager fragmentManager5 = getSupportFragmentManager();
                fragmentManager5
                        .beginTransaction()
                        .replace(R.id.main_content, cuenta)
                        .commit();
                logo.setText("Mi Perfil");
                break;
            case 6:
                ResumenBook resumen = new ResumenBook();
                Bundle bundle6 = new Bundle();
                bundle6.putString("RESUL",getIntent().getStringExtra("descrip"));
                resumen.setArguments(bundle6);
                FragmentManager fragmentManager6 = getSupportFragmentManager();
                fragmentManager6
                        .beginTransaction()
                        .replace(R.id.main_content, resumen)
                        .commit();
                logo.setText("Resumen");
                break;
        }

    }


    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        logo = (TextView) toolbar.findViewById(R.id.logo);
        final ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setHomeAsUpIndicator(R.drawable.ic_menu);
            ab.setDisplayHomeAsUpEnabled(true);
        }
        logo.setText("Mi Biblioteca");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.report:
                selectItem(4);
                mNav.toggleLeftDrawer();
                break;
            case R.id.books:
                selectItem(1);
                mNav.toggleLeftDrawer();
                break;
            case R.id.rank:
                selectItem(3);
                mNav.toggleLeftDrawer();
                break;
            case R.id.account:
                selectItem(5);
                mNav.toggleLeftDrawer();
                break;
            case R.id.logo:
                selectItem(1);
                mNav.toggleLeftDrawer();
                break;
            case R.id.logout:
                mNav.toggleLeftDrawer();
                ParseUser.logOut();
                startActivity(new Intent(MainActivityStudent.this, Login.class));
                break;
        }
    }


}