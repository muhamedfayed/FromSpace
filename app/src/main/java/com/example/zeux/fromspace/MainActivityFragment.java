package com.example.zeux.fromspace;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private WebView webview;
    private TextView textView;
    private ImageView imageView;
    private MediaPlayer mPlayer;
    private Button button;


    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r=  inflater.inflate(R.layout.fragment_main, container, false);

        webview = (WebView) r.findViewById(R.id.webView);
        textView= (TextView) r.findViewById(R.id.net_text);
        imageView = (ImageView) r.findViewById(R.id.imageView);
        button = (Button) r.findViewById(R.id.button);

        mPlayer = MediaPlayer.create(getActivity(), R.raw.space);
        mPlayer.setLooping(true);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPlayer.isPlaying()){
                    pauseMusic();
                }else {
                    playMusic();
                }
            }
        });



        if (isNetworkAvailable(getActivity())) {
            webview.getSettings().setJavaScriptEnabled(true);
            webview.loadUrl("http://www.ustream.tv/embed/17074538?html5ui=1");
            playMusic();

        } else {
            imageView.setAlpha(1);
            textView.setAlpha(1);
            pauseMusic();
        }

        return r;

    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void pauseMusic(){
        if(mPlayer != null){
            mPlayer.pause();
            button.setText("Play music");
        }
    }
    public void playMusic(){
        if(mPlayer != null){
            mPlayer.start();
            button.setText("pause music");
        }
    }

}
