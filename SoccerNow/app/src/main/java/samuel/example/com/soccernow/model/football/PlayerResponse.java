package samuel.example.com.soccernow.model.football;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 7/15/2017.
 */

public class PlayerResponse implements Parcelable {

    public static final Creator<PlayerResponse> CREATOR = new Creator<PlayerResponse>() {
        @Override
        public PlayerResponse createFromParcel(Parcel in) {
            return new PlayerResponse(in);
        }

        @Override
        public PlayerResponse[] newArray(int size) {
            return new PlayerResponse[size];
        }
    };
    @SerializedName("players")
    private List<PlayerData> playerDataList;

    protected PlayerResponse(Parcel in) {
    }

    public PlayerResponse() {
    }

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }

    public void setPlayerDataList(List<PlayerData> playerDataList) {
        this.playerDataList = playerDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
