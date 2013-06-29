package com.Gerardo.Grimaldi.DolarHoy.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.ViewGroup;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.Gerardo.Grimaldi.DolarHoy.R;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class DolarHoyWebAPITask extends AsyncTask<String, Integer, String>{
	private ProgressDialog progDialog;
	private Context context;
	private MainActivity activity;
	private static final String debugTag = "DolarHoyWebAPITask";

    public DolarHoyWebAPITask(MainActivity activity) {
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
        Log.d(debugTag,"Background:" + Thread.currentThread().getName());
        String result = null;
        try {
            result = DolarHoyHelper.downloadFromServer(params);
        } catch (DolarHoyHelper.ApiException e) {
            //e.printStackTrace();
            try {
                result = DolarHoyHelper.downloadFromServer(params);
            } catch (DolarHoyHelper.ApiException ex) {
                //e.printStackTrace();
                return new String();
            }
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result)
    {
        progDialog.dismiss();
        super.onPostExecute(result);
        if (result.length() == 0) {
            this.activity.alert("El servidor no contesta. Intente mas tarde.");
            return;
        }
        try {
			JSONObject respObj = new JSONObject(result);//JSONObject dataObj = respObj.getJSONObject("Dolar");
            BigDecimal valorDolarHoyCompra = new BigDecimal(respObj.getString("dolarCompra"));
            BigDecimal valorDolarHoyVenta = new BigDecimal(respObj.getString("dolarVenta"));
            BigDecimal valorDolarBlueCompra = new BigDecimal(respObj.getString("dolarBlueCompra"));
            BigDecimal valorDolarBlueVenta = new BigDecimal(respObj.getString("dolarBlueVenta"));
            BigDecimal valorDolarTarjeta = new BigDecimal(respObj.getString("dolarTarjeta"));
            BigDecimal valorEuroHoyCompra = new BigDecimal(respObj.getString("euroCompra"));
            BigDecimal valorEuroHoyVenta = new BigDecimal(respObj.getString("euroVenta"));

            Data data = new Data( valorDolarHoyCompra,
                    valorDolarHoyVenta,
                    valorDolarBlueCompra,
                    valorDolarBlueVenta,
                    valorDolarTarjeta,
                    valorEuroHoyCompra,
                    valorEuroHoyVenta);
            this.activity.setData(data);
            this.activity.startFragments();
        } catch (JSONException e) {
			e.printStackTrace();
		}
    }
}