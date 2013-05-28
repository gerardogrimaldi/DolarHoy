package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.R;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyMailWebAPITask;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyWebAPITask;

import java.math.BigDecimal;

public class Contacto extends Fragment {
    Button btnEnviar;
    private DolarHoyMailWebAPITask DhTask;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View myFragmentView = inflater.inflate(R.layout.contacto, null);//, container, false);

        //valores a setear
        final EditText nombre = (EditText) myFragmentView.findViewById(R.id.editNombre);
        final EditText email = (EditText) myFragmentView.findViewById(R.id.editEmail);
        final EditText comment = (EditText) myFragmentView.findViewById(R.id.editComment);

        final String[] params = new String[3];
//        String sNombre = nombre.getText().toString();
//        String sEmail = email.getText().toString();
//        String sComment = comment.getText().toString();
        params[0] = nombre.getText().toString();
        params[1] = email.getText().toString();
        params[2] = comment.getText().toString();


        //accion del boton
        btnEnviar = (Button) myFragmentView.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick
                (View  v) {
                sendMail();
            }
            private void sendMail() {

                DhTask = new DolarHoyMailWebAPITask(params);
                DhTask.execute();

            }});

        //dolarVenta.setText(getArguments().getString("result"));

        return myFragmentView;//inflater.inflate(R.layout.dolar, null);
        //return inflater.inflate(R.layout.calculadora, null);
	}

}
