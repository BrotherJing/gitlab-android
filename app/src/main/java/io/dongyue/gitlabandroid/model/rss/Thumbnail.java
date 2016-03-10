package io.dongyue.gitlabandroid.model.rss;

import android.net.Uri;

import org.parceler.Parcel;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Parcel
@Root(strict = false)
public class Thumbnail {
    @Attribute(name = "url", required = true)
    Uri mUrl;

    public Thumbnail() {}

    public Uri getUrl() {
        return mUrl;
    }
}
