package samuel.example.com.soccernow.widget;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import samuel.example.com.soccernow.R;
import samuel.example.com.soccernow.model.dataBase.LeagueProvider;

import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_POINTS;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_POSITION;
import static samuel.example.com.soccernow.model.dataBase.LeagueContract.LeagueTableEntry.COLUMN_TEAM_NAME;

/**
 * Created by samuel on 7/18/2017.
 */

public class WidgetService extends RemoteViewsService {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new RemoteWidgetViews(getApplicationContext());
    }


}

class RemoteWidgetViews implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private Cursor mCursor;

    public RemoteWidgetViews(Context context) {
        this.mContext = context;
        Uri queryUri = LeagueProvider.LeagueIngredients.LEAGUE_URI;
        mCursor = mContext.getContentResolver().query(queryUri, null, null, null, null);
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        Uri queryUri = LeagueProvider.LeagueIngredients.LEAGUE_URI;
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = mContext.getContentResolver().query(queryUri, null, null, null, null);
    }

    @Override
    public void onDestroy() {
        mCursor.close();
    }

    @Override
    public int getCount() {
        if (mCursor == null) {
            return 0;
        } else {
            return mCursor.getCount();
        }

    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);

        if (mCursor.moveToPosition(position)) {

            String position1 = String.valueOf(mCursor.getInt(mCursor.getColumnIndex(COLUMN_POSITION)));
            String ptl = String.valueOf(mCursor.getInt(mCursor.getColumnIndex(COLUMN_POINTS)));
            String name = mCursor.getString(mCursor.getColumnIndex(COLUMN_TEAM_NAME));

            views.setTextViewText(R.id.position_wedget, position1);
            views.setTextViewText(R.id.name_wedget, name);
            views.setTextViewText(R.id.ptl_wedget, ptl);
        }
        return views;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
