package samuel.example.com.soccernow.model.dataBase;

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
        public static final String MATCHESTABLE = "matches";
    }
}