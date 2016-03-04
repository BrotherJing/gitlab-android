package io.dongyue.gitlabandroid.model.api;

import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import io.dongyue.gitlabandroid.utils.ObjectUtil;

public class Contributor {
    @SerializedName("name")
    String mName;
    @SerializedName("email")
    String mEmail;
    @SerializedName("commits")
    int mCommits;
    @SerializedName("additions")
    int mAdditions;
    @SerializedName("deletions")
    int mDeletions;

    public Contributor() {}

    public String getName() {
        return mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public int getCommits() {
        return mCommits;
    }

    public int getAdditions() {
        return mAdditions;
    }

    public int getDeletions() {
        return mDeletions;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contributor)) {
            return false;
        }

        Contributor contributor = (Contributor) o;
        return ObjectUtil.equals(mName, contributor.mName) && ObjectUtil.equals(mEmail, contributor.mEmail);
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hash(mName, mEmail);
    }

    public static List<Contributor> groupContributors(List<Contributor> contributors) {
        Set<ContributorEntry> contributorEntries = new LinkedHashSet<>();
        for (Contributor contributor : contributors) {
            ContributorEntry contrib = null;
            for (ContributorEntry entry : contributorEntries) {
                if ((contributor.mName != null && contributor.mName.equals(entry.mContributor.mName))
                        || (contributor.mEmail != null && contributor.mEmail.equals(entry.mContributor.mEmail))) {

                    while (entry.mReplacement != null) {
                        entry = entry.mReplacement;
                    }

                    if (contrib == entry) {
                        continue;
                    }

                    if (contrib == null) {
                        contrib = entry;
                    } else {
                        contrib.mContributor.mCommits += entry.mContributor.mCommits;
                        contrib.mContributor.mAdditions += entry.mContributor.mAdditions;
                        contrib.mContributor.mDeletions += entry.mContributor.mDeletions;

                        entry.mReplacement = contrib;
                    }
                }
            }

            if (contrib == null) {
                contrib = new ContributorEntry();
                contrib.mContributor = contributor;

                contributorEntries.add(contrib);
            } else {
                contrib.mContributor.mCommits += contributor.mCommits;
                contrib.mContributor.mAdditions += contributor.mAdditions;
                contrib.mContributor.mDeletions += contributor.mDeletions;
            }
        }

        contributors = new ArrayList<>();

        for (ContributorEntry entry : contributorEntries) {
            if (entry.mReplacement == null) {
                contributors.add(entry.mContributor);
            }
        }

        return contributors;
    }

    private static class ContributorEntry {
        Contributor mContributor;
        ContributorEntry mReplacement;
    }
}
