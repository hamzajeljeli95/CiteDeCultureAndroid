package citeculture.hamzajeljeli.io.citdelaculture.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import java.util.List;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.InfoBean;
import citeculture.hamzajeljeli.io.citdelaculture.R;
import citeculture.hamzajeljeli.io.citdelaculture.detailEvenement;

public class RecycleViewEventsAdapter extends RecyclerView.Adapter<RecycleViewEventsAdapter.ViewHolder> {


    private List<InfoBean> infoBeans;
    private Context mContext;

    public RecycleViewEventsAdapter(List<InfoBean> infoBeans, Context mContext) {
        this.infoBeans = infoBeans;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sample_rvelement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final InfoBean infoBean = infoBeans.get(i);
        viewHolder.title.setText(infoBean.getTitle());
        viewHolder.content_preview.setText(infoBean.getContent().substring(0, 160) + "...");
        viewHolder.dt.setText(infoBean.getDt());
        Glide.with(mContext).load(infoBean.getBanner()).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                viewHolder.banner.setBackground(resource);
            }
        });
        viewHolder.myCurrView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, detailEvenement.class);
                i.putExtra("infos", infoBean);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return infoBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView content_preview;
        public final TextView dt;
        public final LinearLayout banner;
        public final View myCurrView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content_preview = itemView.findViewById(R.id.content_preview);
            banner = itemView.findViewById(R.id.banner);
            dt = itemView.findViewById(R.id.dt);
            myCurrView = itemView;
        }
    }
}
