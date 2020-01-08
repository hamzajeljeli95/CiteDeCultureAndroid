package citeculture.hamzajeljeli.io.citdelaculture.Others;

import android.content.Context;
import android.net.ConnectivityManager;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Util {

    public static int getRequestDelay() {
        return 2000;
    }

    public static boolean isConnectivityAvailable(Context mContext) {
        boolean resp = false;
        try {
            ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(CONNECTIVITY_SERVICE);
            boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                    .isConnectedOrConnecting();
            boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                    .isConnectedOrConnecting();
            if (!is3g && !isWifi) {
                resp = false;
            } else {
                resp = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp = false;
        }
        return resp;
    }

    public static Timestamp toTimeStamp(String dateTime) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
            Date parsedDate = dateFormat.parse(dateTime);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
