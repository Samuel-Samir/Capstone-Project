package samuel.example.com.soccernow.model.football.leagueTable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/15/2017.
 */

public class TeamHref {

    @SerializedName("team")
    private Href team;

    public Href getTeam() {
        return team;
    }

    public void setTeam(Href team) {
        this.team = team;
    }
}
