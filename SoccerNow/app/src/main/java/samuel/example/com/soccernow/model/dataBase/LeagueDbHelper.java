package samuel.example.com.soccernow.model.dataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by samuel on 7/17/2017.
 */

public class LeagueDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "football.db";
    Context context ;

    public LeagueDbHelper(Context context){
        super(context , DATABASE_NAME , null , DATABASE_VERSION);
        this.context = context ;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_LEAGUE_TABLE = "CREATE TABLE " + LeagueContract.LeagueTableEntry.TABLE_NAME + " ("+
                LeagueContract.LeagueTableEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LeagueContract.LeagueTableEntry.COLUMN_POSITION +" INTEGER NOT NULL,"+
                LeagueContract.LeagueTableEntry.COLUMN_TEAM_NAME + " TEXT NOT NULL, "+
                LeagueContract.LeagueTableEntry.COLUMN_IMAGE_URL + " TEXT NOT NULL, " +
                LeagueContract.LeagueTableEntry.COLUMN_POINTS + " INTEGER NOT NULL, " +
                LeagueContract.LeagueTableEntry.COLUMN_WIN +" INTEGER NOT NULL,"+
                LeagueContract.LeagueTableEntry.COLUMN_DRAWS + " INTEGER NOT NULL, "+
                LeagueContract.LeagueTableEntry.COLUMN_LOSSES + " INTEGER NOT NULL " +
                ");";

        final String SQL_CREATE_MATCHED_TABLE = "CREATE TABLE " + LeagueContract.LeagueTableEntry.TABLE_NAME + " ("+
                LeagueContract.MatchesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LeagueContract.MatchesEntry.COLUMN_DATA +" TEXT NOT NULL,"+
                LeagueContract.MatchesEntry.COLUMN_STATUS + " TEXT NOT NULL, "+
                LeagueContract.MatchesEntry.COLUMN_HOME_TEAM + " TEXT NOT NULL, " +
                LeagueContract.MatchesEntry.COLUMN_AWAY_TEAM + " TEXT NOT NULL, " +
                LeagueContract.MatchesEntry.COLUMN_HOME_GOALS +" INTEGER NOT NULL,"+
                LeagueContract.MatchesEntry.COLUMN_AWAY_GOALS + " INTEGER NOT NULL, "+
                ");";

        Log.e("1" , SQL_CREATE_LEAGUE_TABLE);
        Log.e("2 : " , SQL_CREATE_MATCHED_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_LEAGUE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_MATCHED_TABLE);
        Log.e("4 : " , String.valueOf(sqLiteDatabase.isOpen()));

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LeagueContract.LeagueTableEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + LeagueContract.MatchesEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
