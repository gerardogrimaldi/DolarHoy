package com.Gerardo.Grimaldi.DolarHoy.Infraestructure;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.Gerardo.Grimaldi.DolarHoy.Controllers.*;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.viewpagerindicator.IconPagerAdapter;

import java.math.BigDecimal;

public class DolarHoyFragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {
    Data data;
    private static final String[] CONTENT = new String[] { "Dolar", "Blue" ,"Tarjeta", "Calculadora", "Mapa", "Contacto", "Acerca de..." };
	private int mCount = CONTENT.length;

    public DolarHoyFragmentAdapter(FragmentManager fm, Data data) {
		super(fm);
        this.data = data;
	}

	@Override
	public int getIconResId(int index) {
		return 0;
	}

	@Override
	public Fragment getItem(int position) {
        Fragment fragment = new Dolar();
        Bundle bundle = new Bundle();
        if (data == null)
            data = new Data(new BigDecimal("5.2"),
                   new BigDecimal("5.2"),
                   new BigDecimal("5.2"),
                   new BigDecimal("5.2"),
                   new BigDecimal("5.2"),
                   new BigDecimal("5.2"),
                   new BigDecimal("5.2")) ;
        switch(position){
		case 0:
			fragment = new Dolar();
            bundle.putString("DolarCompra", data.getValorDolarHoyCompra().toString());
            bundle.putString("DolarVenta", data.getValorDolarHoyVenta().toString());
            fragment.setArguments(bundle);
            break;
		case 1:
			fragment = new Blue();
//            bundle.putString("BlueCompra", data.getValorDolarBlueCompra().toString());;
//            bundle.putString("BlueVenta", data.getValorDolarBlueVenta().toString());;
//            fragment.setArguments(bundle);
			break;
		case 2:
			fragment = new Tarjeta();
//            bundle.putString("DolarTarjeta", data.getValorDolarTarjeta().toString());;
//            fragment.setArguments(bundle);
			break;
		case 3:
            fragment = new Calculadora();
//            bundle.putString("DolarTarjeta", data.getValorDolarTarjeta().toString());;
			break;
        case 4:
            fragment = new Mapa();
            break;
        case 5:
            fragment = new Contacto();
            break;
        case 6:
            fragment = new About();
            break;
		}
		return fragment;
	}

	@Override
	public int getCount() {
		return mCount;
	}
	
	@Override
	public CharSequence getPageTitle(int position){
		String title = "";
		switch(position){
		case 0:
			title = CONTENT[0];
			break;
		case 1:
			title = CONTENT[1];
			break;
		case 2:
			title = CONTENT[2];
			break;
		case 3:
			title = CONTENT[3];
			break;
        case 4:
            title = CONTENT[4];
            break;
        case 5:
            title = CONTENT[5];
            break;
        case 6:
            title = CONTENT[6];
            break;
		}
		
		return title;
	}
	
//	public void setCount(int count){
//		if (count > 0 && count < 10){
//			mCount = count;
//			notifyDataSetChanged();
//		}
//	}
	

}
