package com.app.plan_lector.activity;

import android.app.Activity;
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
import android.widget.Toast;

import com.app.plan_lector.R;
import com.app.plan_lector.fragment.Biblioteca;
import com.app.plan_lector.fragment.Games;
import com.app.plan_lector.fragment.MyAccount;
import com.app.plan_lector.fragment.Rank;
import com.app.plan_lector.fragment.Report;
import com.avast.android.dialogs.fragment.ListDialogFragment;
import com.avast.android.dialogs.iface.IListDialogListener;
import com.navdrawer.SimpleSideDrawer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

	private SimpleSideDrawer mNav;
	private LinearLayout books,report,games,rank, account;
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
		mNav.setLeftBehindContentView(R.layout.activity_behind_left_simple);

		books = (LinearLayout)mNav.findViewById(R.id.books);
		games = (LinearLayout)mNav.findViewById(R.id.games);
		rank = (LinearLayout)mNav.findViewById(R.id.rank);
		report = (LinearLayout)mNav.findViewById(R.id.report);
        account = (LinearLayout)mNav.findViewById(R.id.account);
		report.setOnClickListener(this);
		games.setOnClickListener(this);
		rank.setOnClickListener(this);
		books.setOnClickListener(this);
        account.setOnClickListener(this);
	}

	private void selectItem(int position){
		switch (position){
			case 1:
				Biblioteca biblio = new Biblioteca();
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
				Games juego = new Games();
                Bundle bun = new Bundle();
                juego.setArguments(bun);
				FragmentManager fragmentManager2 = getSupportFragmentManager();
				fragmentManager2
						.beginTransaction()
						.replace(R.id.main_content, juego)
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
				MyAccount cuenta = new MyAccount();
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
		logo.setText("Mi Biblioteca");
	}



	@Override
	public void onClick(View view) {
		switch (view.getId()){
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
			case R.id.games:
				selectItem(2);
				mNav.toggleLeftDrawer();
				break;
            case R.id.account:
                selectItem(5);
                mNav.toggleLeftDrawer();
                break;
		}
	}

}