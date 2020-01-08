package citeculture.hamzajeljeli.io.citdelaculture.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.InfoBean;
import citeculture.hamzajeljeli.io.citdelaculture.R;
import citeculture.hamzajeljeli.io.citdelaculture.WebServices.EventsWS;
import citeculture.hamzajeljeli.io.citdelaculture.WebServices.GalleryWS;

/**
 * A simple {@link Fragment} subclass.
 */
public class gallery_fragment extends Fragment {

    CarouselView carouselView;
    List<InfoBean> myItems;
    View v;
    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Glide.with(getContext()).load(myItems.get(position).getBanner()).into(imageView);
        }

    };

    public gallery_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        carouselView = (CarouselView) getView().findViewById(R.id.carouselView);
        myItems = new ArrayList<InfoBean>();
        v = view;
        new getAll(getContext()).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery_fragment, container, false);
    }

    private class getAll extends AsyncTask<Void, Void, List<InfoBean>> {
        private ProgressDialog pd;
        private Context context;

        public getAll(Context mContext) {
            context = mContext;
            pd = new ProgressDialog(mContext);
            pd.setTitle(getString(R.string.please_wait));
            pd.setMessage(getString(R.string.loading));
            pd.setIndeterminate(false);
            pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pd.setCancelable(false);
        }

        @Override
        protected void onPreExecute() {
            pd.show();
        }

        @Override
        protected void onCancelled() {
            pd.dismiss();
        }

        @Override
        protected List<InfoBean> doInBackground(Void... voids) {
            return GalleryWS.getGallery();
        }

        @Override
        protected void onPostExecute(List<InfoBean> infoBeans) {
            if (infoBeans != null) {
                myItems = infoBeans;
                carouselView.setImageListener(imageListener);
                carouselView.setPageCount(myItems.size());
            } else {
                Snackbar.make(v, Html.fromHtml(getString(R.string.hintcnx)), Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.actionbar_text))
                        .setAction("Action", null).show();
            }
            pd.dismiss();
        }
    }
}
