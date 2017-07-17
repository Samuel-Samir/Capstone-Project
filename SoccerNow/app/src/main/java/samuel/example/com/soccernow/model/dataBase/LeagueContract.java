package samuel.example.com.soccernow.model.dataBase;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by samuel on 7/17/2017.
 */

public class LeagueContract {


    public interface LeagueTableEntry {

        @DataType(DataType.Type.INTEGER)
        String COLUMN_POSITION = "position";
        @DataType(DataType.Type.TEXT)
        String COLUMN_TEAM_NAME = "teamName";
        @DataType(DataType.Type.TEXT)
        String COLUMN_IMAGE_URL = "crestURI";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_POINTS = "points";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_WIN = "wins";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_DRAWS = "draws";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_LOSSES = "losses";

    }

    public interface MatchesEntry {
        @DataType(DataType.Type.TEXT)
        String COLUMN_DATA = "date";
        @DataType(DataType.Type.TEXT)
        String COLUMN_STATUS = "status";
        @DataType(DataType.Type.TEXT)
        String COLUMN_HOME_TEAM = "homeTeamName";
        @DataType(DataType.Type.TEXT)
        String COLUMN_AWAY_TEAM = "awayTeamName";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_HOME_GOALS = "goalsHomeTeam";
        @DataType(DataType.Type.INTEGER)
        String COLUMN_AWAY_GOALS = "goalsAwayTeam";

    }

    @Database(version = RecipesDatabase.VERSION)
    public final class RecipesDatabase {
        public static final int VERSION = 1;
        @Table(LeagueTableEntry.class)
        public static final String LEAGUETABLE = "league";
        @Table(MatchesEntry.class)
        public static final String MATCHESTABLE  = "matches";
    }
}

    /*
    public static final String CONTENT_AUTHORITY  = "samuel.example.com.soccernow";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);
    public static final String PATH_LEAGUE_TABLE = "leagueTable";
    public static final String PATH_MATCHES = "matches";

    public static final class LeagueTableEntry implements BaseColumns {

        public static final String TABLE_NAME = "league";
        public static final String COLUMN_POSITION="position";
        public static final String COLUMN_TEAM_NAME= "teamName";
        public static final String COLUMN_IMAGE_URL = "crestURI";
        public static final String COLUMN_POINTS = "points";
        public static final String COLUMN_WIN = "wins";
        public static final String COLUMN_DRAWS = "draws";
        public static final String COLUMN_LOSSES = "losses";



        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_LEAGUE_TABLE;
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_LEAGUE_TABLE).build();
    }



    public static final class MatchesEntry implements BaseColumns{
        public static final String TABLE_NAME = "matches";
        public static final String COLUMN_DATA = "date";
        public static final String COLUMN_STATUS = "status";
        public static final String COLUMN_HOME_TEAM = "homeTeamName";
        public static final String COLUMN_AWAY_TEAM = "awayTeamName";
        public static final String COLUMN_HOME_GOALS = "goalsHomeTeam";
        public static final String COLUMN_AWAY_GOALS= "goalsAwayTeam";


        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_MATCHES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" +PATH_MATCHES;
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MATCHES).build();
    }



}*/
