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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.fragment.parent.AvanceAlumno;
import com.app.plan_lector.fragment.parent.MyAccountParent;
import com.app.plan_lector.fragment.parent.Rank;
import com.navdrawer.SimpleSideDrawer;
import com.parse.ParseUser;

public class MainActivityParent extends AppCompatActivity implements View.OnClickListener{

	private SimpleSideDrawer mNav;
	private LinearLayout report,rank, account,logout;
	private TextView logo;
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
		ParseUser currentUser = ParseUser.getCurrentUser();
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
		mNav.setLeftBehindContentView(R.layout.sidemenu_parent);
		rank = (LinearLayout)mNav.findViewById(R.id.rank);
		report = (LinearLayout)mNav.findViewById(R.id.report);
        account = (LinearLayout)mNav.findViewById(R.id.account);
		logout = (LinearLayout)mNav.findViewById(R.id.logout);
		logout.setOnClickListener(this);
        report.setOnClickListener(this);
		rank.setOnClickListener(this);
        account.setOnClickListener(this);
	}

	private void selectItem(int position){
		switch (position){
			case 1:
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
			case 2:
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
			case 3:
				MyAccountParent cuenta = new MyAccountParent();
                Bundle bundle = new Bundle();
                cuenta.setArguments(bundle);
				FragmentManager fragmentManager5 = getSupportFragmentManager();
				fragmentManager5
						.beginTransaction()
						.replace(R.id.main_content, cuenta)
						.commit();
				logo.setText("Mi Perfil");
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
		logo.setText("Ranking");
	}



	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.report:
				selectItem(2);
				mNav.toggleLeftDrawer();
				break;
			case R.id.rank:
				selectItem(1);
				mNav.toggleLeftDrawer();
				break;
            case R.id.account:
                selectItem(3);
                mNav.toggleLeftDrawer();
                break;
			case R.id.logout:
				mNav.toggleLeftDrawer();
				ParseUser.logOut();
				startActivity(new Intent(MainActivityParent.this,Login.class));
				break;
		}
	}

}