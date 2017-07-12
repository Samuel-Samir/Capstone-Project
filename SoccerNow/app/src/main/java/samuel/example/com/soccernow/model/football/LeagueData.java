package samuel.example.com.soccernow.model.football;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/12/2017.
 */

public class LeagueData {

    @SerializedName("position")
    private int position ;

    @SerializedName("teamName")
    private String teamName;

    @SerializedName("crestURI")
    private String crestURI;

    @SerializedName("playedGames")
    private int playedGames ;

    @SerializedName("points")
    private int points;

    @SerializedName("wins")
    private int wins;

    @SerializedName("draws")
    private int draws;

    @SerializedName("losses")
    private int losses;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCrestURI() {
        return crestURI;
    }

    public void setCrestURI(String crestURI) {
        this.crestURI = crestURI;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public void setPlayedGames(int playedGames) {
        this.playedGames = playedGames;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }
}

