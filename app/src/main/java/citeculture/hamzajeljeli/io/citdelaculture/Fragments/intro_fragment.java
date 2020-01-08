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
import android.widget.TextView;

import com.bumptech.glide.Glide;

import citeculture.hamzajeljeli.io.citdelaculture.Beans.IntroBean;
import citeculture.hamzajeljeli.io.citdelaculture.R;
import citeculture.hamzajeljeli.io.citdelaculture.WebServices.IntroWS;

public class intro_fragment extends Fragment {

    TextView introText;
    ImageView banner;
    View v;

    public intro_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        introText = getView().findViewById(R.id.introText);
        banner = getView().findViewById(R.id.img);
        v = view;
        new getInformations(getContext()).execute();

    }

    private class getInformations extends AsyncTask<Void, Void, IntroBean> {
        private ProgressDialog pd;

        public getInformations(Context mContext) {
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
        protected IntroBean doInBackground(Void... voids) {
            return IntroWS.getIntro();
        }

        @Override
        protected void onPostExecute(IntroBean introBean) {
            if (introBean != null) {
                Glide.with(getContext()).load(introBean.getBanner()).into(banner);
                introText.setText(introBean.getContent());
            } else {
                Snackbar.make(v, Html.fromHtml(getString(R.string.hintcnx)), Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.actionbar_text))
                        .setAction("Action", null).show();
            }
            pd.dismiss();
        }
    }

}
