package com.app.plan_lector.activity.student;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.RatingBar;
import android.widget.TextView;

import com.app.plan_lector.R;

public class ResultActivity extends AppCompatActivity {
	TextView logo, resultado;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		setToolbar();
		//get rating bar object
		resultado = (TextView)findViewById(R.id.resul);
		RatingBar bar=(RatingBar)findViewById(R.id.ratingBar1); 
		bar.setNumStars(5);
		bar.setStepSize(0.5f);
		//get text view
		TextView t=(TextView)findViewById(R.id.textResult);
		//get score
		Bundle b = getIntent().getExtras();
		int score= b.getInt("score");
		resultado.setText(""+score);
		bar.setRating(score);
		switch (score)
		{
		case 1:
		case 2: t.setText("Oops! Intentalo nuevamente!");
		break;
		case 3:
		case 4:t.setText("Hmmmm.. Muy buen intento");
		break;
		case 5:t.setText("¡Excelente! ¡Felicidades!");
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
		logo.setText("Resultados");
	}
}
