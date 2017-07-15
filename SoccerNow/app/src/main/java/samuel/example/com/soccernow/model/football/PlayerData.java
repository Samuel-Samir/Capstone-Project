package samuel.example.com.soccernow.model.football;

import com.google.gson.annotations.SerializedName;

/**
 * Created by samuel on 7/15/2017.
 */

public class PlayerData {
    @SerializedName("name")
    private String name;
    @SerializedName("position")
    private String position;
    @SerializedName("jerseyNumber")
    private int jerseyNumber;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("nationality")
    private String nationality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}

