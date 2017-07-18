package samuel.example.com.soccernow.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.RemoteViews;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.dataBase.LeagueProvider;
import samuel.example.com.soccernow.view.ContentActivity;

/**
 * Created by samuel on 7/18/2017.
 */

public class Widget  extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

        Intent widgetServiceIntent = new Intent(context, WidgetService.class);
        views.setRemoteAdapter(R.id.widget_ingredients_list_view, widgetServiceIntent);

        views.setEmptyView(R.id.widget_ingredients_list_view, R.id.empty_widget_view);
/*
        Intent intent = new Intent(context, ContentActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        views.setOnClickPendingIntent(R.id.widget_container, pendingIntent);*/

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


