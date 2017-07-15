package samuel.example.com.soccernow.model.football.leagueMatches;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/14/2017.
 */

public class MatcheData {

    @SerializedName("date")
    private String  date;
    @SerializedName("status")
    private String status;
    @SerializedName("homeTeamName")
    private String homeTeamName;
    @SerializedName("awayTeamName")
    private String awayTeamName;
    @SerializedName("result")
    private MatchResult result ;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }
}






