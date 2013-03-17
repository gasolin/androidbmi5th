package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReportActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_report);
		initViews();
        showResults();
        setListensers();
	}
	
	private Button button_back;
    private TextView show_result;
    private TextView show_suggest;

    private void initViews() {
        button_back = (Button) findViewById(R.id.report_back);
        show_result = (TextView) findViewById(R.id.result);
        show_suggest = (TextView) findViewById(R.id.suggest);
    }

    private void showResults() {
        DecimalFormat nf = new DecimalFormat("0.00");

        Bundle bunde = this.getIntent().getExtras();
        double height = Double.parseDouble(bunde.getString("KEY_HEIGHT")) / 100;
        double weight = Double.parseDouble(bunde.getString("KEY_WEIGHT"));
        double BMI = weight / (height * height);
        show_result.setText(getString(R.string.bmi_result) + nf.format(BMI));

        //Give health advice
        if (BMI > 25) {
        	show_suggest.setText(R.string.advice_heavy);
        } else if (BMI < 20) {
        	show_suggest.setText(R.string.advice_light);
        } else {
        	show_suggest.setText(R.string.advice_average);
        }

    }

    //Listen for button clicks
    private void setListensers() {
        button_back.setOnClickListener(backMain);
    }

    private Button.OnClickListener backMain = new Button.OnClickListener() {
        public void onClick(View v) {
            // Close this Activity
            ReportActivity.this.finish();
        }
    };

    
}
