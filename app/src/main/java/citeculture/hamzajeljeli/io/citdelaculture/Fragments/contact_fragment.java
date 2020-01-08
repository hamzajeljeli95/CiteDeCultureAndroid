package citeculture.hamzajeljeli.io.citdelaculture.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import citeculture.hamzajeljeli.io.citdelaculture.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class contact_fragment extends Fragment {

    EditText subject, content;
    Button sendBtn;

    public contact_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        subject = getView().findViewById(R.id.subject);
        content = getView().findViewById(R.id.contentMail);
        sendBtn = getView().findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((subject.getText().toString().equals("") == false) && (content.getText().toString().equals("") == false)) {
                    Intent email = new Intent(Intent.ACTION_SEND);
                    email.putExtra(Intent.EXTRA_EMAIL, "hamzajeljeli@outlook.fr");
                    email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    email.putExtra(Intent.EXTRA_TEXT, content.getText().toString());
                    email.setType("message/rfc822");
                    startActivity(Intent.createChooser(email, getString(R.string.hint1)));
                } else {
                    Snackbar.make(view, Html.fromHtml(getString(R.string.hint)), Snackbar.LENGTH_SHORT).setActionTextColor(getResources().getColor(R.color.actionbar_text))
                            .setAction("Action", null).show();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_fragment, container, false);
    }

}
