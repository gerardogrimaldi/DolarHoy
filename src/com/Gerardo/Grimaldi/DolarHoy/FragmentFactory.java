package com.Gerardo.Grimaldi.DolarHoy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public final class FragmentFactory extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";
    private static String Content;

    public static FragmentFactory newInstance(String content) {
        FragmentFactory fragment = new FragmentFactory();
        Content = content;
        return fragment;
    }


    private View mContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b) {
        View view = null;
        switch (Content){
            case "Dolar":
                view = inflater.inflate(R.layout.dolar, container, false);
            break;
            case "Blue":
                view = inflater.inflate(R.layout.blue, container, false);
                break;
            case "Tarejeta":
                view = inflater.inflate(R.layout.tarjeta, container, false);
                break;
            case "Calculardora":
                view  = inflater.inflate(R.layout.calculadora, container, false);
                break;
            case "Mapa":
                view = inflater.inflate(R.layout.mapa, container, false);
                break;
            case "Conacto":
                view = inflater.inflate(R.layout.contacto, container, false);
                break;
            case "Acerca de...":
                view = inflater.inflate(R.layout.about, container, false);
                break;
        }

        mContent = view;//!!! this is important
        //((TextView) view.findViewById(R.id.last_view_time)).setText(time);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getClass(view);
        } */
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*TextView text = new TextView(getActivity());
        text.setGravity(Gravity.CENTER);
        text.setText(mContent);
        text.setTextSize(10 * getResources().getDisplayMetrics().density);
        text.setPadding(10, 10, 10, 10);

        LinearLayout layout = new LinearLayout(R.layout.about);
        layout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);


        return layout;
    }*/

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }
}


