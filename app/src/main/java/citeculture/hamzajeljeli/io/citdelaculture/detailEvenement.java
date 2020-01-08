package citeculture.hamzajeljeli.io.citdelaculture;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.InfoBean;
import citeculture.hamzajeljeli.io.citdelaculture.Others.Util;

public class detailEvenement extends AppCompatActivity {

    private InfoBean myInfoBean;
    private LinearLayout bannerImg;
    private TextView dt, text, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_evenement);
        myInfoBean = (InfoBean) getIntent().getSerializableExtra("infos");
        bannerImg = findViewById(R.id.banner);
        dt = findViewById(R.id.dt);
        text = findViewById(R.id.introText);
        title = findViewById(R.id.title);
        Glide.with(this).load(myInfoBean.getBanner()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                bannerImg.setBackground(resource);
            }
        });
        dt.setText(myInfoBean.getDt());
        text.setText(myInfoBean.getContent());
        title.setText(myInfoBean.getTitle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mInflater = getMenuInflater();
        mInflater.inflate(R.menu.event_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share1) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, myInfoBean.getTitle());
            sharingIntent.putExtra(Intent.EXTRA_TEXT, myInfoBean.getContent() + "\n\n" + myInfoBean.getDt());
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
        }
        if (id == R.id.addToCalBtn) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra(CalendarContract.Events.TITLE, myInfoBean.getTitle());
            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, Util.toTimeStamp(myInfoBean.getDt()).getTime());
            //intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endDateMillis);
            intent.putExtra(CalendarContract.Events.ALL_DAY, false);
            intent.putExtra(CalendarContract.Events.DESCRIPTION, myInfoBean.getContent());
            intent.putExtra(CalendarContract.Events.EVENT_LOCATION, getString(R.string.banner));
            startActivity(Intent.createChooser(intent, getString(R.string.addToCal)));
        }
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //setContentView(R.layout.activity_dossier_list);
    }
}
