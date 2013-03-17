package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends Activity {
//	private static final String TAG = "Report";
	private static final String TAG = ReportActivity.class.getSimpleName();

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
    	if (Debug.On) Log.d(TAG, "init Views");
        button_back = (Button) findViewById(R.id.report_back);
        show_result = (TextView) findViewById(R.id.result);
        show_suggest = (TextView) findViewById(R.id.suggest);
    }

    private void showResults() {
        DecimalFormat nf = new DecimalFormat("0.00");
        try {
	        Bundle bunde = this.getIntent().getExtras();
	        double height = Double.parseDouble(bunde.getString("KEY_HEIGHT")) / 100;
	        double weight = Double.parseDouble(bunde.getString("KEY_WEIGHT"));
	        double BMI = weight / (height * height);
	        show_result.setText(getString(R.string.bmi_result) + nf.format(BMI));
	
	        //Give health advice
	        if (BMI > 25) {
	        	showNotification(BMI);
	        	show_suggest.setText(R.string.advice_heavy);
	        } else if (BMI < 20) {
	        	show_suggest.setText(R.string.advice_light);
	        } else {
	        	show_suggest.setText(R.string.advice_average);
	        }
        } catch (Exception err) {
        	if (Debug.On) Log.e(TAG, "error: " + err.toString());
        	Toast.makeText(ReportActivity.this, R.string.input_error, Toast.LENGTH_SHORT).show();
        }
    }

    //Listen for button clicks
    private void setListensers() {
    	if (Debug.On) Log.d(TAG, "set Listensers");
        button_back.setOnClickListener(backMain);
    }

    private Button.OnClickListener backMain = new Button.OnClickListener() {
        public void onClick(View v) {
            // Close this Activity
            ReportActivity.this.finish();
        }
    };

    protected void showNotification (double BMI) {
        NotificationManager barManager = 
    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            
//        Notification barMsg = new Notification(
//        	R.drawable.ic_launcher, 
//            	"歐歐，你過重囉！",
//            	System.currentTimeMillis()
//         );

        PendingIntent contentIntent = PendingIntent.getActivity(
            this,
            0,
            new Intent(this, MainActivity.class),
            PendingIntent.FLAG_UPDATE_CURRENT);
        		
//        barMsg.setLatestEventInfo(
//            ReportActivity.this,
//            "您的 BMI 值過高",
//            "通知監督人",
//            contentIntent
//        );
//        barManager.notify(0, barMsg);
        
        Notification.Builder barMsg = new Notification.Builder(this)
        .setTicker("歐歐，你過重囉！")
        .setContentTitle("您的 BMI 值過高")
        .setContentText("通知監督人")
        .setSmallIcon(android.R.drawable.stat_sys_warning)
        .setContentIntent(contentIntent);
        barManager.notify(0, barMsg.build());
    }
    
}
