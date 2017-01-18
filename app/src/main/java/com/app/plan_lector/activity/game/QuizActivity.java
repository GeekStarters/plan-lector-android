package com.app.plan_lector.activity.game;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.app.plan_lector.R;
import com.app.plan_lector.model.Question;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener{
	List<Question> quesList;
	int score=0;
	int qid=0;
	int tope;
	String valor;
	Question currentQ;
	TextView txtQuestion,logo;
	RadioButton rda, rdb, rdc, rdd;
	Button butNext;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
		}
		setToolbar();
		txtQuestion=(TextView)findViewById(R.id.textView1);
		rda=(RadioButton)findViewById(R.id.radio0);
		rdb=(RadioButton)findViewById(R.id.radio1);
		rdc=(RadioButton)findViewById(R.id.radio2);
		rdd = (RadioButton)findViewById(R.id.radio3);
		butNext=(Button)findViewById(R.id.button1);
		butNext.setOnClickListener(this);
		valor = getIntent().getStringExtra("RESUL");
		quesList = new ArrayList<>();
		final ParseQuery<ParseObject> query = ParseQuery.getQuery("Quiz");
		query.whereEqualTo("ebook_id",valor);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			@Override
			public void done(final ParseObject object, ParseException e) {
				tope = object.getInt("questions");
				ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Questions");
				query1.whereEqualTo("quiz_id",object.getObjectId());
				query1.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> objects, ParseException e) {
						for(ParseObject parseObject:objects){
							Question q = new Question();
							q.setID(parseObject.getObjectId());
							q.setQUESTION(parseObject.getString("question"));
							q.setANSWER(parseObject.getString("answer"));
							q.setOPTA(parseObject.getString("option1"));
							q.setOPTB(parseObject.getString("option2"));
							q.setOPTC(parseObject.getString("option3"));
							q.setOPTD(parseObject.getString("option4"));
							quesList.add(q);
						}
						currentQ=quesList.get(qid);
						setQuestionView();
					}
				});
			}
		});
	}

	private void setQuestionView()
	{
		txtQuestion.setText(currentQ.getQUESTION());
		rda.setText(currentQ.getOPTA());
		rdb.setText(currentQ.getOPTB());
		rdc.setText(currentQ.getOPTC());
		rdd.setText(currentQ.getOPTD());
		qid++;
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
		logo.setText("Quiz");
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.button1:
				RadioGroup grp=(RadioGroup)findViewById(R.id.radioGroup1);
				RadioButton answer=(RadioButton)findViewById(grp.getCheckedRadioButtonId());
				Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
				if(currentQ.getANSWER().equals(answer.getText()))
				{
					score++;
					Log.d("score", "Tu puntaje"+score);
				}
				if(qid<tope){
					currentQ=quesList.get(qid);
					setQuestionView();
				}else{
					Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
					Bundle b = new Bundle();
					b.putInt("score", score);
					b.putInt("tope",tope);//Your score
					intent.putExtras(b); //Put your score to your next Intent
					startActivity(intent);
					finish();
				}
				break;
		}
	}
}
