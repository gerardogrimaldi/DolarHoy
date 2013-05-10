package com.Gerardo.Grimaldi.DolarHoy.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import com.Gerardo.Grimaldi.DolarHoy.AppStart;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.Gerardo.Grimaldi.DolarHoy.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class DolarHoyWebAPITask extends AsyncTask<String, Integer, String>
{
	private ProgressDialog progDialog;
	private Context context;
	private AppStart activity;
	private static final String debugTag = "DolarHoyWebAPITask";

    public DolarHoyWebAPITask(AppStart activity) {
		super();
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
	}

	@Override
    protected void onPreExecute() {
        super.onPreExecute(); 
    	progDialog = ProgressDialog.show(this.activity, "Actualizando...", this.context.getResources().getString(R.string.progDialog), true, false);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
        	Log.d(debugTag,"Background:" + Thread.currentThread().getName());
            String result = DolarHoyHelper.downloadFromServer(params);
            return result;
        } catch (Exception e) {
            return new String();
        }
    }
    
    @Override
    protected void onPostExecute(String result) 
    {
       	progDialog.dismiss();
        if (result.length() == 0) {
            this.activity.alert ("Unable to find track data. Try again later.");
            return;
        }
        
        try {
			JSONObject respObj = new JSONObject(result);
			JSONObject dataObj = respObj.getJSONObject("Dolar");
            /*BigDecimal valorDolarHoyCompra = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorDolarHoyVenta = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorDolarBlueCompra = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorDolarBlueVenta = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorDolarTarjeta = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorEuroHoyCompra = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal valorEuroHoyVenta = new BigDecimal(dataObj.getString("name").replaceAll(",", "").replaceAll(".", ""));*/

            BigDecimal valorDolarHoyCompra = new BigDecimal("5.3");
            BigDecimal valorDolarHoyVenta = new BigDecimal("5.3");
            BigDecimal valorDolarBlueCompra = new BigDecimal("5.3");
            BigDecimal valorDolarBlueVenta = new BigDecimal("5.3");
            BigDecimal valorDolarTarjeta = new BigDecimal("5.3");
            BigDecimal valorEuroHoyCompra = new BigDecimal("5.3");
            BigDecimal valorEuroHoyVenta = new BigDecimal("5.3");

            Data data = new Data( valorDolarHoyCompra,
                    valorDolarHoyVenta,
                    valorDolarBlueCompra,
                    valorDolarBlueVenta,
                    valorDolarTarjeta,
                    valorEuroHoyCompra,
                    valorEuroHoyVenta);

            this.activity.setData(data);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}