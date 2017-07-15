package samuel.example.com.soccernow.model.football.leagueTable;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import samuel.example.com.soccernow.model.football.leagueTable.LeagueData;

/**
 * Created by samuel on 7/12/2017.
 */

public class LeagueTableResponse implements Parcelable {

    @SerializedName("standing")
    private List<LeagueData> leagueDataList ;

    protected LeagueTableResponse(Parcel in) {
    }

    public  LeagueTableResponse (){}

    public static final Creator<LeagueTableResponse> CREATOR = new Creator<LeagueTableResponse>() {
        @Override
        public LeagueTableResponse createFromParcel(Parcel in) {
            return new LeagueTableResponse(in);
        }

        @Override
        public LeagueTableResponse[] newArray(int size) {
            return new LeagueTableResponse[size];
        }
    };

    public List<LeagueData> getLeagueDataList() {
        return leagueDataList;
    }

    public void setLeagueDataList(List<LeagueData> leagueDataList) {
        this.leagueDataList = leagueDataList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
