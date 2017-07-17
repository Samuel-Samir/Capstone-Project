package samuel.example.com.soccernow.model.dataBase;


import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by samuel on 7/17/2017.
 */


@ContentProvider(authority = LeagueProvider.AUTHORITY , database =LeagueContract.RecipesDatabase.class  )
public class LeagueProvider {
    public static final String AUTHORITY = "samuel.example.com.soccernow";



    @TableEndpoint(table = LeagueContract.RecipesDatabase.LEAGUETABLE)
    public static class LeagueIngredients {

        @ContentUri(path = LeagueContract.RecipesDatabase.LEAGUETABLE,
                type = "vnd.android.cursor.dir/list")
        public static final Uri LEAGUE_URI = Uri.parse("content://" + AUTHORITY)
                .buildUpon()
                .appendPath(LeagueContract.RecipesDatabase.LEAGUETABLE)
                .build();
    }

    @TableEndpoint(table = LeagueContract.RecipesDatabase.MATCHESTABLE)
    public static class MatchesIngredients {

        @ContentUri(path = LeagueContract.RecipesDatabase.MATCHESTABLE,
                type = "vnd.android.cursor.dir/list")
        public static final Uri MATCHEST_URI = Uri.parse("content://" + AUTHORITY)
                .buildUpon()
                .appendPath(LeagueContract.RecipesDatabase.MATCHESTABLE)
                .build();
    }



}