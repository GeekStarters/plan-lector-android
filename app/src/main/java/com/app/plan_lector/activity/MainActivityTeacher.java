package com.app.plan_lector.activity;

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
import com.app.plan_lector.fragment.teacher.AvanceAlumno;
import com.app.plan_lector.fragment.teacher.BookListStudent;
import com.app.plan_lector.fragment.teacher.BookListTeacher;
import com.app.plan_lector.fragment.teacher.Library;
import com.app.plan_lector.fragment.teacher.MyAccountTeacher;
import com.app.plan_lector.fragment.teacher.Rank;
import com.navdrawer.SimpleSideDrawer;

public class MainActivityTeacher extends AppCompatActivity implements View.OnClickListener{

	private SimpleSideDrawer mNav;
	private LinearLayout books,report,games,rank, account,logout;
	private TextView logo;
    private ImageView home;
	private Activity context;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		}
		context=this;
		setContentView(R.layout.activity_main);
		setToolbar();
		init();
		selectItem(1);
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
		mNav.setLeftBehindContentView(R.layout.sidemenu_teacher);
        home = (ImageView)mNav.findViewById(R.id.logo);
		books = (LinearLayout)mNav.findViewById(R.id.books);
		games = (LinearLayout)mNav.findViewById(R.id.student_book);
		rank = (LinearLayout)mNav.findViewById(R.id.rank);
		report = (LinearLayout)mNav.findViewById(R.id.progress);
        account = (LinearLayout)mNav.findViewById(R.id.account);
		logout = (LinearLayout)mNav.findViewById(R.id.logout);
		logout.setOnClickListener(this);
        home.setOnClickListener(this);
        report.setOnClickListener(this);
		games.setOnClickListener(this);
		rank.setOnClickListener(this);
		books.setOnClickListener(this);
        account.setOnClickListener(this);
	}

	private void selectItem(int position){
		switch (position){
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
				BookListStudent games = new BookListStudent();
				Bundle bun = new Bundle();
				games.setArguments(bun);
				FragmentManager fragmentManager2 = getSupportFragmentManager();
				fragmentManager2
						.beginTransaction()
						.replace(R.id.main_content, games)
						.commit();
				logo.setText("Biblioteca Estudiantes");
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
				AvanceAlumno cartilla = new AvanceAlumno();
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
				MyAccountTeacher cuenta = new MyAccountTeacher();
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
                BookListTeacher libro = new BookListTeacher();
                Bundle b = new Bundle();
                libro.setArguments(b);
                FragmentManager fragmentManager6 = getSupportFragmentManager();
                fragmentManager6
                        .beginTransaction()
                        .replace(R.id.main_content, libro)
                        .commit();
                logo.setText("Mi Biblioteca");
                break;
		}

	}


	private void setToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		logo = (TextView)toolbar.findViewById(R.id.logo);
		final ActionBar ab = getSupportActionBar();
		if (ab != null) {
			ab.setHomeAsUpIndicator(R.drawable.ic_menu);
			ab.setDisplayHomeAsUpEnabled(true);
		}
		logo.setText("Mi Biblioteca");
	}



	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.progress:
				selectItem(4);
				mNav.toggleLeftDrawer();
				break;
			case R.id.books:
				selectItem(6);
				mNav.toggleLeftDrawer();
				break;
			case R.id.rank:
				selectItem(3);
				mNav.toggleLeftDrawer();
				break;
			case R.id.student_book:
				selectItem(2);
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
				startActivity(new Intent(MainActivityTeacher.this,Login.class));
				break;
		}
	}

}