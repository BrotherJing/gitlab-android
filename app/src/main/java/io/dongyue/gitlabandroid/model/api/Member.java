package io.dongyue.gitlabandroid.model.api;

import com.google.gson.annotations.SerializedName;

public class Member extends UserBasic {
    @SerializedName("access_level")
    int mAccessLevel;

    public Member() {}

    public int getAccessLevel() {
        return mAccessLevel;
    }

    public static int getAccessLevel(String accessLevel) {
        switch (accessLevel.toLowerCase()) {
            case "guest":
                return 10;
            case "reporter":
                return 20;
            case "developer":
                return 30;
            case "master":
                return 40;
            case "owner":
                return 50;
        }

        throw new IllegalStateException("No known code for this access level");
    }

    public static String getAccessLevel(int accessLevel) {
        switch (accessLevel) {
            case 10:
                return "Guest";
            case 20:
                return "Reporter";
            case 30:
                return "Developer";
            case 40:
                return "Master";
            case 50:
                return "Owner";
        }

        return "Unknown";
    }

    @Override
    public String toString() {
        return getUsername();
    }
}
