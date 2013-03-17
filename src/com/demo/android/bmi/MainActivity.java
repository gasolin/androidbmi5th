package com.demo.android.bmi;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
//	private static final String TAG = "Main";
	private static final String TAG = MainActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViews();
	    setListensers();
	}

	private Button button_calc;
	private EditText num_height;
	private EditText num_weight;
	private TextView show_result;
	private TextView show_suggest;
	
	private void initViews() {
	  Log.d(TAG, "init Views");
	  button_calc = (Button) findViewById(R.id.submit);
	  num_height = (EditText) findViewById(R.id.height);
	  num_weight = (EditText) findViewById(R.id.weight);
	  show_result = (TextView) findViewById(R.id.result);
	  show_suggest = (TextView) findViewById(R.id.suggest);
	}

	// Listen for button clicks
	private void setListensers() {
		Log.d(TAG, "set Listensers");
	    button_calc.setOnClickListener(calcBMI);
    }
	
	private Button.OnClickListener calcBMI = new Button.OnClickListener() { 
//        public void onClick(View v) {
//            DecimalFormat nf = new DecimalFormat("0.00");
//            try {
//            double height = Double.parseDouble(num_height.getText().toString()) / 100;
//            double weight = Double.parseDouble(num_weight.getText().toString());
//            double BMI = weight / (height * height);
//
//            // Present result
//            show_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
//
//            // Give health advice
//            if (BMI > 25) {
//            	show_suggest.setText(R.string.advice_heavy);
//            } else if (BMI < 20) {
//            	show_suggest.setText(R.string.advice_light);
//            } else {
//            	show_suggest.setText(R.string.advice_average);
//            }
//            } catch (Exception obj) {
//        		Log.e(TAG, "error: " + err.toString());
//            	Toast.makeText(MainActivity.this, R.string.input_error, Toast.LENGTH_SHORT).show();
//            }
//        }
		
		public void onClick(View v) {
			//Switch to report page
	        Intent intent = new Intent();
	        intent.setClass(MainActivity.this, ReportActivity.class);
//	        intent.setAction("bmi.action.report");
	        Bundle bundle = new Bundle();
	        bundle.putString("KEY_HEIGHT", num_height.getText().toString());
	        bundle.putString("KEY_WEIGHT", num_weight.getText().toString());
	        intent.putExtras(bundle);
	        startActivity(intent);
		}
    };
    
//    protected static final int MENU_SETTINGS = Menu.FIRST;
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
//		menu.add(0, MENU_SETTINGS, 100, R.string.action_settings);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case R.id.action_about:
            openOptionsDialog();
            break;
	   	case R.id.action_close:
	            finish();
	            break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void openOptionsDialog() {
		AlertDialog.Builder dialog = new  AlertDialog.Builder(MainActivity.this);
	    dialog.setTitle(R.string.about_title);
	    dialog.setMessage(R.string.about_msg);
	    dialog.setPositiveButton(android.R.string.ok,
	    	new DialogInterface.OnClickListener(){
	    	    public void onClick(DialogInterface dialoginterface, int i){}
	        });
	    dialog.setNegativeButton(R.string.label_homepage,
		    	new DialogInterface.OnClickListener(){
		    	    public void onClick(DialogInterface dialoginterface, int i){
		    	    	// open browser
//		                Uri uri = Uri.parse("http://android.gasolin.idv.tw/");
		    	    	// open map
//		    	    	Uri uri = Uri.parse("geo:25.047192, 121.516981?z=17");
		    	    	// phone call
//		    	        Uri uri = Uri.parse("tel:12345678"); 
		    	    	Uri uri = Uri.parse(getString(R.string.homepage_uri));
		                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		                startActivity(intent);
		    	    }
		        });
	    dialog.show();
	};

//	private void openOptionsDialog() {
//	    Toast popup = Toast.makeText(MainActivity.this, R.string.app_name, Toast.LENGTH_SHORT);
//	    popup.show();
//	}

}
