package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.R;

public class Tarjeta extends Fragment {
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View myFragmentView = inflater.inflate(R.layout.tarjeta, null);//, container, false);

        EditText dolarTarjeta = (EditText) myFragmentView.findViewById(R.id.dolarTarjeta);

        dolarTarjeta.setText(getArguments().getString("dolarTarjeta"));

        return myFragmentView;//inflater.inflate(R.layout.dolar, null);
	}
}
