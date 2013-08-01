package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.R;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Calculadora extends Fragment {
    Button btnCalcular;
    BigDecimal aCalcularValue;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View myFragmentView = inflater.inflate(R.layout.calculadora, null);//, container, false);
        //valores del servidor
        final BigDecimal dV = new BigDecimal(getArguments().getString("dolarVenta"));
        final BigDecimal dBV = new BigDecimal(getArguments().getString("dolarBlueVenta"));
        final BigDecimal dT = new BigDecimal(getArguments().getString("dolarTarjeta"));
        final BigDecimal eV = new BigDecimal(getArguments().getString("euroVenta"));
        final BigDecimal rV = new BigDecimal(getArguments().getString("realVenta"));

        //valores a setear
        final EditText dolarVenta = (EditText) myFragmentView.findViewById(R.id.dolarVenta);
        final EditText dolarBlueVenta = (EditText) myFragmentView.findViewById(R.id.dolarBlueVenta);
        final EditText dolarTarjeta= (EditText) myFragmentView.findViewById(R.id.dolarTarjeta);
        final EditText realVenta = (EditText) myFragmentView.findViewById(R.id.realVenta);
        final EditText euroVenta = (EditText) myFragmentView.findViewById(R.id.euroVenta);

        //seteo por default
        dolarVenta.setText("0");
        dolarBlueVenta.setText("0");
        dolarTarjeta.setText("0");
        realVenta.setText("0");
        euroVenta.setText("0");

        final EditText aCalcular = (EditText) myFragmentView.findViewById(R.id.aCalcular);

        //accion del boton
        btnCalcular = (Button) myFragmentView.findViewById(R.id.btnCalcular);
        btnCalcular.setOnClickListener(new Button.OnClickListener() {
            public MainActivity activity;

            public void onClick
                (View  v) {
                calculate();
            }
            private void calculate() {
                String saCalcular = aCalcular.getText().toString();
                if (saCalcular.matches("0") || saCalcular.matches("")){
                    activity = (MainActivity)getActivity();
                    this.activity.alert("Ingrese un valor...");
                }
                else
                {
                    try {
                        aCalcularValue = new BigDecimal(aCalcular.getText().toString());
                        dolarVenta.setText(aCalcularValue.multiply(dV).setScale(2, RoundingMode.CEILING).toString());
                        dolarBlueVenta.setText(aCalcularValue.multiply(dBV).setScale(2, RoundingMode.CEILING).toString());
                        dolarTarjeta.setText(aCalcularValue.multiply(dT).setScale(2, RoundingMode.CEILING).toString());
                        realVenta.setText(aCalcularValue.multiply(rV).setScale(2, RoundingMode.CEILING).toString());
                        euroVenta.setText(aCalcularValue.multiply(eV).setScale(2, RoundingMode.CEILING).toString());

                    } catch(NumberFormatException e) {
                        this.activity.alert("El valor no se puede procesar, intentelo de nuevo...");
                    }
                }
            }});

        //dolarVenta.setText(getArguments().getString("result"));

        return myFragmentView;//inflater.inflate(R.layout.dolar, null);
        //return inflater.inflate(R.layout.calculadora, null);
    }
}
