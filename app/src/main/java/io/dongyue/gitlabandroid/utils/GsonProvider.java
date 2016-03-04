package io.dongyue.gitlabandroid.utils;

import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.util.Date;

import io.dongyue.gitlabandroid.model.Account;

/**
 * Get a properly configured Gson object
 * Created by Jawn on 12/4/2015.
 */
public final class GsonProvider {

    private static Gson sGson;

    private GsonProvider() {}

    public static Gson getInstance() {
        if (sGson == null) {
            sGson = createInstance(null);
        }
        return sGson;
    }

    public static Gson createInstance(Account account) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        gsonBuilder.registerTypeAdapter(Uri.class, new UriSerializer(account));
        return gsonBuilder.create();
    }


    private static class UriSerializer implements JsonSerializer<Uri>, JsonDeserializer<Uri> {
        private final Account mAccount;

        public UriSerializer(Account account) {
            mAccount = account;
        }

        @Override
        public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            String uriString = null;
            if (!json.isJsonNull()) {
                uriString = json.getAsString();
            }

            return ConversionUtil.toUri(mAccount, uriString);
        }

        @Override
        public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
            String uriString = ConversionUtil.fromUri(src);
            if (uriString == null) {
                return JsonNull.INSTANCE;
            }

            return new JsonPrimitive(uriString);
        }
    }
}
