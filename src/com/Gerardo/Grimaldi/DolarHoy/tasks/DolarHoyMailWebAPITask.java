package com.Gerardo.Grimaldi.DolarHoy.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.Gerardo.Grimaldi.DolarHoy.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class DolarHoyMailWebAPITask extends AsyncTask<String, Integer, String>{
	private ProgressDialog progDialog;
	private Context context;
	private MainActivity activity;
	private static final String debugTag = "DolarHoyWebAPITask";

    public DolarHoyMailWebAPITask(MainActivity activity,String... params) {
		super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
	}

	@Override
    protected void onPreExecute() {
        super.onPreExecute();
    	progDialog = ProgressDialog.show(this.activity, "Enviando...", this.context.getResources().getString(R.string.progDialog), true, false);
    }

    @Override
    protected String doInBackground(String... params) {
        //Log.d(debugTag,"Background:" + Thread.currentThread().getName());
        String result = null;
        try {
            if( DolarHoyMailHelper.sendMailFromServer(params)== true)              {
                //this.activity.alert("Mensaje enviado...");
            }

        } catch (DolarHoyMailHelper.ApiException e) {
            //this.activity.alert("No se ha enviado el mensaje por problemas en el servidor...");

        }
        return result;
    }


    @Override
    protected void onPostExecute(String result)
    {
        progDialog.dismiss();
        super.onPostExecute(result);

    }
}