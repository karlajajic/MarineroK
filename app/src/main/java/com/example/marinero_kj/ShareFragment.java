package com.example.marinero_kj;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

public class ShareFragment extends Fragment {

    EditText emailText;
    EditText sendText;

    ImageButton sendButton;

    public static ShareFragment newInstance() {
        ShareFragment fragment = new ShareFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);

        emailText=view.findViewById(R.id.share_fragment_email);
        sendText=view.findViewById(R.id.share_fragment_text);


        sendButton=view.findViewById(R.id.share_fragment_sendBtn);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[] {emailText.getText().toString()});
                intent.putExtra(Intent.EXTRA_SUBJECT, "MARINERO!");
                intent.putExtra(Intent.EXTRA_TEXT, sendText.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }
}
