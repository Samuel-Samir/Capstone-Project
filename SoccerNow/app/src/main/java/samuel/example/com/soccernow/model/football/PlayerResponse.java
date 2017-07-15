package samuel.example.com.soccernow.model.football;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 7/15/2017.
 */

public class PlayerResponse {

    @SerializedName("players")
    private List<PlayerData> playerDataList ;

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public void setPlayerDataList(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
    }
}
