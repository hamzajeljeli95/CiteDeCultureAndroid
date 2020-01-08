package citeculture.hamzajeljeli.io.citdelaculture.WebServices;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.InfoBean;

public class GalleryWS {
    public static List<InfoBean> getGallery() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/hamzajeljeli95/citedeculture/master/gallery.json");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            return new Gson().fromJson(reader, new TypeToken<List<InfoBean>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
