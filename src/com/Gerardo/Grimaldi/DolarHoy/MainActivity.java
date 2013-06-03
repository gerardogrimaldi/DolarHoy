package com.Gerardo.Grimaldi.DolarHoy;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.Toast;
import com.Gerardo.Grimaldi.DolarHoy.Infraestructure.DolarHoyFragmentAdapter;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyWebAPITask;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

public class MainActivity extends FragmentActivity {
    DolarHoyFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    private Data data;
    private DolarHoyWebAPITask DhTask;
    private boolean fragmentsOn = false;

    public boolean isFragmentsOn() {
        return fragmentsOn;
    }

    public void setFragmentsOn(boolean fragmentsOn) {
        this.fragmentsOn = fragmentsOn;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 1, "Menu").setEnabled(false).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        SubMenu subMenu = menu.addSubMenu(0, 0, 2,"");
        subMenu.add(0, 2, 2, "Refrescar...").setIcon(R.drawable.ic_menu_refresh_inverse);
        MenuItem subMenuItem = subMenu.getItem();
        subMenuItem.setIcon(R.drawable.ic_menu_config);
        subMenuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (data == null){
            execTask();
        }else{
            startFragments();
        }

    }

    public void startFragments(){
        setFragmentsOn(true);

        ViewGroup vg = (ViewGroup) findViewById (R.id.main);
        if(vg != null)
        {vg.removeAllViews();
        vg.invalidate();
        vg.refreshDrawableState();}

        mAdapter = new DolarHoyFragmentAdapter(getSupportFragmentManager(), getData());
        setContentView(R.layout.main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 2:
                try {
                    execTask();
                } catch (Exception e) {
                    DhTask.cancel(true);
                    alert(getResources().getString(R.string.no_internet));
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public void alert (String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    protected void execTask() {
        DhTask = new DolarHoyWebAPITask(MainActivity.this);
        DhTask.execute();
    }
}
