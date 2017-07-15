package samuel.example.com.soccernow.model.football.leagueTable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;

/**
 * Created by samuel on 7/12/2017.
 */

public class LeagueTableResponse {

    @SerializedName("standing")
    private List<LeagueData> leagueDataList ;

    public List<LeagueData> getLeagueDataList() {
        return leagueDataList;
    }

    public void setLeagueDataList(List<LeagueData> leagueDataList) {
        this.leagueDataList = leagueDataList;
    }
}
