package com.demo.android.bmi;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class NewWidgetProvider extends AppWidgetProvider {
	@Override
	  public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
	    // TODO Auto-generated method stub
	    super.onUpdate(context, appWidgetManager, appWidgetIds);

	    RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.new_widget);

	    Intent intent = new Intent();
	    intent.setAction(Intent.ACTION_INSERT);
	    intent.setClass(context, MainActivity.class);

	    PendingIntent pendingIntent;
	    pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

	    views.setOnClickPendingIntent(R.id.imgbtn1, pendingIntent);
	    appWidgetManager.updateAppWidget(appWidgetIds, views);
	  }
}
