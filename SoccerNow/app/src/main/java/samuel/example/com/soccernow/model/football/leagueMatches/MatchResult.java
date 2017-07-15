package samuel.example.com.soccernow.model.football.leagueMatches;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/14/2017.
 */

public class MatchResult {


    @SerializedName("goalsHomeTeam")
    private int goalsHomeTeam ;
    @SerializedName("goalsAwayTeam")
    private int goalsAwayTeam ;

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public void setGoalsHomeTeam(int goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public void setGoalsAwayTeam(int goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }
}
