package com.app.plan_lector.activity.game;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.plan_lector.R;

public class ResultActivity extends AppCompatActivity {
	TextView logo, resultado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		}
		setContentView(R.layout.activity_result);
		setToolbar();
		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		int tope  = b.getInt("tope");
		float step = 5/tope;
		float res = (score/tope)*10;
		//get rating bar object
		resultado = (TextView)findViewById(R.id.resul);
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1);
		TextView t=(TextView)findViewById(R.id.textResult);
		resultado.setText(score+"/"+tope);
		Log.e("Resultado",res+"");
		Log.e("Steps ",bar.getStepSize()+"");
		if(res>=0&&res<=2.0){
			if(res<=1.0){
				bar.setRating(0.5f);
			}else {
				bar.setRating(1.0f);
			}
			t.setText("Oops! Intentalo nuevamente!");
		}else{
			if(res>2.0&&res<=4.0){
				if(res<=3.0){
					bar.setRating(1.5f);
				}else {
					bar.setRating(2.0f);
				}
				t.setText("Oops! Puedes hacerlo mejor!");
			}else {
				if(res>4.0&&res<=6.0){
					if(res<=5.0){
						bar.setRating(2.5f);
					}else {
						bar.setRating(3.0f);
					}
					t.setText("Hmmmm.. Buen intento");
				}else{
					if(res>6.0&&res<=8.0){
						if(res<=7.0){
							bar.setRating(3.5f);
						}else {
							bar.setRating(4.0f);
						}
						t.setText("Hmmmm.. Muy buen intento");
					}else{
						if(res<=9.0){
							bar.setRating(4.5f);
						}else {
							bar.setRating(5.0f);
						}
						if(res==10.0){
							t.setText("¡Excelente! ¡Felicidades!");
						}else{
							t.setText("Excelente intento");
						}
					}
				}
			}
		}
		Log.e("Rating",bar.getRating()+"");
		//bar.setRating(res);
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
		logo.setText("Resultados");
	}
}
