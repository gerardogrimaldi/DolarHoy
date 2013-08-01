package com.Gerardo.Grimaldi.DolarHoy.tasks;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class DolarHoyMailHelper {
    private static final String GetValuestUrl = "http://dolarhoymailserver.herokuapp.com/mail/Hola123!/";


	private static final int HTTP_STATUS_OK = 200;
	private static byte[] buff = new byte[1024];
	//private static final String logTag = "DolarHoyHelper";

	public static class ApiException extends Exception {
		private static final long serialVersionUID = 1L;
    	public ApiException (String msg) {
			super (msg);
		}

		public ApiException (String msg, Throwable thr)	{
			super (msg, thr);
		}
	}

	/**
	 * download most popular tracks in given metro.
	 * @param params search strings
	 * @return Array of json strings returned by the API.
	 * @throws ApiException
	 */
	public static synchronized boolean sendMailFromServer(String... params) throws ApiException	{
        //String retrival;
        String name = params[0];
        String subject = params[1];
        String text = params[2];

        String url = GetValuestUrl + name + "/" + subject + "/" + text + "/" ;
		//Log.d(logTag, "Fetching " + url);
		// create an http client and a request object.
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		try {
			// execute the request
			HttpResponse response = client.execute(request);
			StatusLine status = response.getStatusLine();
			if (status.getStatusCode() != HTTP_STATUS_OK) {
				// handle error here
				throw new ApiException("Respuesta Invalida " + status.toString());
			}
            return true;
			// process the content.
//			HttpEntity entity = response.getEntity();
//			InputStream ist = entity.getContent();
//			ByteArrayOutputStream content = new ByteArrayOutputStream();
//
//			int readCount = 0;
//			while ((readCount = ist.read(buff)) != -1) {
//				content.write(buff, 0, readCount);
//			}
//			retrival = content.toString();

		} catch (Exception e) {
			throw new ApiException("Problema conectandose al servidor " + e.getMessage(), e);
		}
	}
}
