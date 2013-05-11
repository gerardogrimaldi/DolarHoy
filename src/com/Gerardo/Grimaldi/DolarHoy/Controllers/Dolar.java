package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.R;

public class Dolar extends Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View myFragmentView = inflater.inflate(R.layout.dolar, null);//, container, false);

        EditText dolarCompra = (EditText) myFragmentView.findViewById(R.id.dolarCompra);
        EditText dolarVenta = (EditText) myFragmentView.findViewById(R.id.dolarVenta);


        dolarCompra.setText(getArguments().getString("DolarCompra").toString());
        dolarVenta.setText(getArguments().getString("DolarVenta").toString());

        return myFragmentView;//inflater.inflate(R.layout.dolar, null);
    }
}
