package samuel.example.com.soccernow.model;

/**
 * Created by samuel on 7/16/2017.
 */


public class FriendlyMessage {

    private String text;
    private String name;
    private String photoUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
