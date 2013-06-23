package com.Gerardo.Grimaldi.DolarHoy;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.Gerardo.Grimaldi.DolarHoy.Infraestructure.DolarHoyFragmentAdapter;
import com.Gerardo.Grimaldi.DolarHoy.Model.Data;
import com.Gerardo.Grimaldi.DolarHoy.tasks.DolarHoyWebAPITask;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;
import com.google.ads.*;

public class MainActivity extends FragmentActivity {
    DolarHoyFragmentAdapter mAdapter;
    ViewPager mPager;
    PageIndicator mIndicator;
    private Data data;
    private DolarHoyWebAPITask DhTask;
    private AdView adView;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
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
        } else {
            startFragments();
        }
    }

    public void startFragments(){
        ViewGroup vg = (ViewGroup) findViewById (R.id.main);

        if(vg != null) {
            getWindow().getDecorView().findViewById(R.id.main).invalidate();
            vg.removeAllViews();
            vg.refreshDrawableState();
            vg.invalidate();
        }

        mAdapter = new DolarHoyFragmentAdapter(getSupportFragmentManager(), getData());
        setContentView(R.layout.main);

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        mPager.setCurrentItem(0);
        mAdapter.getItemPosition(0);
        mAdapter.getItem(0);

        mAdapter.notifyDataSetChanged();
        mIndicator.notifyDataSetChanged();


        // Create the adView
        adView = new AdView(this, AdSize.IAB_LEADERBOARD, "a151c714b6e5599");

        // Lookup your LinearLayout assuming it's been given
        // the attribute android:id="@+id/mainLayout"
        LinearLayout layout = (LinearLayout)findViewById(R.id.main);

        // Add the adView to it
        layout.addView(adView);

        AdRequest adRequest = new AdRequest();


        // Initiate a generic request to load it with an ad
        adView.loadAd(adRequest);

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
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
