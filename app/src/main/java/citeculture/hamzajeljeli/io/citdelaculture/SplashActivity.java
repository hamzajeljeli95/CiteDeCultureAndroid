package citeculture.hamzajeljeli.io.citdelaculture;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import citeculture.hamzajeljeli.io.citdelaculture.Others.Util;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ProgressBar b = findViewById(R.id.progressBarInit);
        b.setVisibility(View.VISIBLE);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Util.isConnectivityAvailable(getBaseContext())) {
                    startActivity(new Intent(getBaseContext(), MainActivity.class));
                    finish();
                } else {
                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(SplashActivity.this);
                    dlgAlert.setMessage(R.string.chkinternet);
                    dlgAlert.setTitle(R.string.err);
                    dlgAlert.setPositiveButton(R.string.o, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            System.exit(0);
                        }
                    });
                    dlgAlert.setCancelable(false);
                    dlgAlert.create().show();
                }
            }
        }, 2000);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}
