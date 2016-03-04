package io.dongyue.gitlabandroid.model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import io.dongyue.gitlabandroid.model.api.UserFull;
import io.dongyue.gitlabandroid.utils.ObjectUtil;

public class Account implements Comparable<Account>{

    /*public static List<Account> getAccounts(Context context) {
        List<Account> accounts = new ArrayList<>(Prefs.getAccounts(context));
        Collections.sort(accounts);
        Collections.reverse(accounts);
        return accounts;
    }*/

    @SerializedName("server_url")
    Uri mServerUrl;
    @SerializedName("authorization_header")
    String mAuthorizationHeader;
    @SerializedName("private_token")
    String mPrivateToken;
    @SerializedName("trusted_certificate")
    String mTrustedCertificate;
    @SerializedName("user")
    UserFull mUser;
    @SerializedName("last_used")
    Date mLastUsed;

    public Account() {}

    public Uri getServerUrl() {
        return mServerUrl;
    }

    public void setServerUrl(Uri url) {
        mServerUrl = url;
    }

    public String getAuthorizationHeader() {
        return mAuthorizationHeader;
    }

    public void setAuthorizationHeader(String authorizationHeader) {
        mAuthorizationHeader = authorizationHeader;
    }

    public String getPrivateToken() {
        return mPrivateToken;
    }

    public void setPrivateToken(String privateToken) {
        mPrivateToken = privateToken;
    }

    public String getTrustedCertificate() {
        return mTrustedCertificate;
    }

    public void setTrustedCertificate(String trustedCertificate) {
        mTrustedCertificate = trustedCertificate;
    }

    public UserFull getUser() {
        return mUser;
    }

    public void setUser(UserFull user) {
        mUser = user;
    }

    public Date getLastUsed() {
        return mLastUsed;
    }

    public void setLastUsed(Date lastUsed) {
        mLastUsed = lastUsed;
    }

    @Override
    public int compareTo(@NonNull Account another) {
        return mLastUsed.compareTo(another.getLastUsed());
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Account)) {
            return false;
        }

        Account account = (Account) o;
        return ObjectUtil.equals(mServerUrl, account.mServerUrl)
                && ObjectUtil.equals(mUser, account.mUser);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(mServerUrl, mUser);
    }
}
