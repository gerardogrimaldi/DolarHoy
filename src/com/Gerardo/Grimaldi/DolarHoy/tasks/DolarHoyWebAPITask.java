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
            return new String();
        }
        return result;
    }


    @Override
    protected void onPostExecute(String result)
    {
        progDialog.dismiss();
        super.onPostExecute(result);
        if (result.length() == 0) {
            this.activity.alert(R.string.ErrorNoData + "");
            return;
        }
        try {
			JSONObject respObj = new JSONObject(result);
			//JSONObject dataObj = respObj.getJSONObject("Dolar");
            BigDecimal dolarCompra = new BigDecimal(respObj.getString("dolarCompra").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal dolarVenta = new BigDecimal(respObj.getString("dolarVenta").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal dolarBlueCompra = new BigDecimal(respObj.getString("dolarBlueCompra").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal dolarBlueVenta = new BigDecimal(respObj.getString("dolarBlueVenta").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal dolarTarjeta = new BigDecimal(respObj.getString("dolarTarjeta").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal euroCompra = new BigDecimal(respObj.getString("euroCompra").replaceAll(",", "").replaceAll(".", ""));
            BigDecimal euroVenta = new BigDecimal(respObj.getString("euroVenta").replaceAll(",", "").replaceAll(".", ""));

            BigDecimal valorDolarHoyCompra = dolarCompra;
            BigDecimal valorDolarHoyVenta = dolarVenta;
            BigDecimal valorDolarBlueCompra = dolarBlueCompra;
            BigDecimal valorDolarBlueVenta = dolarBlueVenta;
            BigDecimal valorDolarTarjeta = dolarTarjeta;
            BigDecimal valorEuroHoyCompra = euroCompra;
            BigDecimal valorEuroHoyVenta = euroVenta;

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