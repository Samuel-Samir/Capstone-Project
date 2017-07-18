package samuel.example.com.soccernow.model.football.leagueMatches;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by samuel on 7/14/2017.
 */

public class LeagueMatchesResponse implements Parcelable {

    public static final Creator<LeagueMatchesResponse> CREATOR = new Creator<LeagueMatchesResponse>() {
        @Override
        public LeagueMatchesResponse createFromParcel(Parcel in) {
            return new LeagueMatchesResponse(in);
        }

        @Override
        public LeagueMatchesResponse[] newArray(int size) {
            return new LeagueMatchesResponse[size];
        }
    };
    @SerializedName("fixtures")
    private List<MatcheData> matcheDataList;

    public LeagueMatchesResponse() {
    }

    protected LeagueMatchesResponse(Parcel in) {
    }

    public List<MatcheData> getMatcheDataList() {
        return matcheDataList;
    }

    public void setMatcheDataList(List<MatcheData> matcheDataList) {
        this.matcheDataList = matcheDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
