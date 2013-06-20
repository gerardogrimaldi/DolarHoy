package com.Gerardo.Grimaldi.DolarHoy.Controllers;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.Gerardo.Grimaldi.DolarHoy.MainActivity;
import com.Gerardo.Grimaldi.DolarHoy.R;

public class Dolar extends Fragment{
    EditText dolarCompra;
    EditText dolarVenta;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View myFragmentView = inflater.inflate(R.layout.dolar, null);//, container, false);

        dolarCompra = (EditText) myFragmentView.findViewById(R.id.dolarCompra);
        dolarVenta = (EditText) myFragmentView.findViewById(R.id.dolarVenta);

        dolarCompra.setText(getArguments().getString("dolarCompra"));
        dolarVenta.setText(getArguments().getString("dolarVenta"));

        Button copy1 = (Button) myFragmentView.findViewById(R.id.CopiarDolarCompra);
        Button copy2 = (Button) myFragmentView.findViewById(R.id.CopiarDolarVenta);
        copy1.setOnClickListener(onClickListener);
        copy2.setOnClickListener(onClickListener);

        return myFragmentView;//inflater.inflate(R.layout.dolar, null);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(final View v) {
            switch(v.getId()){
                case R.id.CopiarDolarCompra:
                    copyToClipboard(dolarCompra.getText().toString());
                    break;
                case R.id.CopiarDolarVenta:
                    copyToClipboard(dolarVenta.getText().toString());
                    break;
            }
        }
    };



    public void copyToClipboard(String toCopy){
        int sdk = android.os.Build.VERSION.SDK_INT;
        MainActivity activity = (MainActivity) getActivity();
        if(sdk < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(toCopy);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("text label",toCopy);
            clipboard.setPrimaryClip(clip);
        }
    }
}
