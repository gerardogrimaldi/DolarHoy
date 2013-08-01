package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.R;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyMailHelper;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyMailWebAPITask;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyWebAPITask;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Contacto extends Fragment {
    Button btnEnviar;
    MainActivity activity;
    private ProgressDialog progDialog;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View myFragmentView = inflater.inflate(R.layout.contacto, null);//, container, false);
        activity = (MainActivity) getActivity();

        //valores a setear
        final EditText nombre = (EditText) myFragmentView.findViewById(R.id.editNombre);
        final EditText email = (EditText) myFragmentView.findViewById(R.id.editEmail);
        final EditText comment = (EditText) myFragmentView.findViewById(R.id.editComment);

        //accion del boton
        btnEnviar = (Button) myFragmentView.findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new Button.OnClickListener() {
            public void onClick (View  v) {
                sendMail();
            }
            private void sendMail() {

                String sNombre = nombre.getText().toString();
                String sEmail = email.getText().toString();
                String sComment = comment.getText().toString();

                if (sNombre.matches("") || sEmail.matches("") || sComment.matches("")){
                    activity = (MainActivity)getActivity();
                    activity.alert("Ingrese todos los campos para enviar...");
                } else {
                    try {
                        progDialog = ProgressDialog.show(activity, "Enviando...", getResources().getString(R.string.progDialogMail), true, false);
                        activity.alert("Enviando...");
                        final String[] params = new String[3];
                        params[0] = sNombre.replaceAll("[^A-Za-z0-9@.]", "");
                        params[1] = sEmail.replaceAll("[^A-Za-z0-9@.]", "");
                        params[2] = sComment.replaceAll("[^A-Za-z0-9@.]", "");

                        if(DolarHoyMailHelper.sendMailFromServer(params))              {
                            progDialog.dismiss();
                            activity.alert("Mensaje enviado...");
                            nombre.setText("");
                            email.setText("");
                            comment.setText("");
                        }
                    } catch (DolarHoyMailHelper.ApiException e) {
                        progDialog.dismiss();
                        activity.alert("Error al enviar el mensaje...");
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
            }});

        return myFragmentView;
    }
}