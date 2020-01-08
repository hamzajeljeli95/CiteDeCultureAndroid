package citeculture.hamzajeljeli.io.citdelaculture;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

public class detailActualite extends AppCompatActivity {

    private InfoBean myInfoBean;
    private LinearLayout bannerImg;
    private TextView dt, text, title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_actualite);
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
        mInflater.inflate(R.menu.news_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share2) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, myInfoBean.getTitle());
            sharingIntent.putExtra(Intent.EXTRA_TEXT, myInfoBean.getTitle() + "\n\n" + myInfoBean.getContent() + "\n\n" + myInfoBean.getDt());
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share_via)));
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
