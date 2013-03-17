package com.demo.android.bmi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Pref extends PreferenceActivity {
	public static final String PREF = "BMI_PREF";
    public static final String PREF_HEIGHT = "BMI_HEIGHT";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      addPreferencesFromResource(R.xml.pref);
    }
    
    public static String getHeight(Context context){
    	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        return pref.getString(PREF_HEIGHT, "");
    }
    
    public static void setHeight(Context context, String height){
    	SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
//    	SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    	Editor editor = pref.edit();
		editor.putString(PREF_HEIGHT, height);
		editor.commit();
		return;
    }
}
