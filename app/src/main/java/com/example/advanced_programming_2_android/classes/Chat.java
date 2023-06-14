package com.example.advanced_programming_2_android.classes;

import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

public class Chat {
    private Uri profilePic;
    private String displayName;
    private String lastMessage;
    private String timestamp;

    public Chat(String profilePic, String displayName, String lastMessage, String timestamp) {
        this.profilePic = Uri.parse(profilePic);
        this.displayName = displayName;
        this.lastMessage = lastMessage;
        this.timestamp = timestamp;
    }

    public Uri getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = Uri.parse(profilePic);;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "profilePic=" + profilePic +
                ", displayName=" + displayName +
                ", lastMessage=" + lastMessage +
                ", timestamp=" + timestamp +
                '}';
    }
}
