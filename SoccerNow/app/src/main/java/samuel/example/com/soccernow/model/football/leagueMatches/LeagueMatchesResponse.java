package samuel.example.com.soccernow.model.football.leagueMatches;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;

/**
 * Created by samuel on 7/14/2017.
 */

public class LeagueMatchesResponse {

    @SerializedName("fixtures")
    private List<MatcheData> matcheDataList  ;

    public List<MatcheData> getMatcheDataList() {
        return matcheDataList;
    }

    public void setMatcheDataList(List<MatcheData> matcheDataList) {
        this.matcheDataList = matcheDataList;
    }
}
