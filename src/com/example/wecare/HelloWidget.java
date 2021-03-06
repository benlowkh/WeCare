package com.example.wecare;

import java.util.Random;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

public class HelloWidget extends AppWidgetProvider {

	private static final String SYNC_CLICKED = "automaticWidgetSyncButtonClick";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		RemoteViews remoteViews;
		ComponentName watchWidget;

		remoteViews = new RemoteViews(context.getPackageName(),
				R.layout.widget);
		watchWidget = new ComponentName(context, HelloWidget.class);

		remoteViews.setOnClickPendingIntent(R.id.button,
				getPendingSelfIntent(context, SYNC_CLICKED));
		appWidgetManager.updateAppWidget(watchWidget, remoteViews);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		super.onReceive(context, intent);

		if (SYNC_CLICKED.equals(intent.getAction())) {

			AppWidgetManager appWidgetManager = AppWidgetManager
					.getInstance(context);

			RemoteViews remoteViews;
			ComponentName watchWidget;

			remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.widget);
			watchWidget = new ComponentName(context, HelloWidget.class);

			remoteViews.setTextViewText(R.id.button, "TESTING");

			appWidgetManager.updateAppWidget(watchWidget, remoteViews);

		}
	}

	protected PendingIntent getPendingSelfIntent(Context context, String action) {
		Intent intent = new Intent(context, getClass());
		intent.setAction(action);
		return PendingIntent.getBroadcast(context, 0, intent, 0);
	}
}