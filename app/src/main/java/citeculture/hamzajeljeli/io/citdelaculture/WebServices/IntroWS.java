package citeculture.hamzajeljeli.io.citdelaculture.WebServices;

import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.net.URL;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.IntroBean;

public class IntroWS {
    public static IntroBean getIntro() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/hamzajeljeli95/citedeculture/master/intro.json");
            InputStreamReader reader = new InputStreamReader(url.openStream());
            return new Gson().fromJson(reader, IntroBean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
