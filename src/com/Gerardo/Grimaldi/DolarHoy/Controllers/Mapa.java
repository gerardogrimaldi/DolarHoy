package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Gerardo.Grimaldi.DolarHoy.R;

import java.net.URL;

public class Mapa extends Fragment {
    String URL = "https://www.google.com/maps/ms?msa=0&msid=215331291065026830178.0004c000ecf2673162b9a&ie=UTF8&t=m&ll=-34.602623,-58.374825&spn=0.009714,0.01384&z=16&output=embed";
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View myFragmentView = inflater.inflate(R.layout.mapa, null);//, container, false);
        WebView webView = (WebView)  myFragmentView.findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(URL);
        return myFragmentView;
//        return inflater.inflate(R.layout.mapa, null);
	}
}
