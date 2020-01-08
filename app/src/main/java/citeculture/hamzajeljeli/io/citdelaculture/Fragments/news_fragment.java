package citeculture.hamzajeljeli.io.citdelaculture.Fragments;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import citeculture.hamzajeljeli.io.citdelaculture.Adapters.RecycleViewNewsAdapter;
import citeculture.hamzajeljeli.io.citdelaculture.Beans.InfoBean;
import citeculture.hamzajeljeli.io.citdelaculture.R;
import citeculture.hamzajeljeli.io.citdelaculture.WebServices.NewsWS;

/**
 * A simple {@link Fragment} subclass.
 */
public class news_fragment extends Fragment {

    private RecyclerView rv;
    private View v;

    public news_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rv = getView().findViewById(R.id.newsRv);
        v = view;
        new getAllNews(getContext()).execute();

    }

    private class getAllNews extends AsyncTask<Void, Void, List<InfoBean>> {
        private ProgressDialog pd;
        private Context context;

        public getAllNews(Context mContext) {
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
            return NewsWS.getNews();
        }

        @Override
        protected void onPostExecute(List<InfoBean> infoBeans) {
            if (infoBeans != null) {
                rv.setLayoutManager(new LinearLayoutManager(context));
                rv.setItemAnimator(new DefaultItemAnimator());
                RecyclerView.ItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
                rv.addItemDecoration(dividerItemDecoration);
                rv.setAdapter(new RecycleViewNewsAdapter(infoBeans, context));
            } else {
                Snackbar.make(v, Html.fromHtml(getString(R.string.hintcnx)), Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.actionbar_text))
                        .setAction("Action", null).show();
            }
            pd.dismiss();
        }
    }
}
