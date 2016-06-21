package com.lkl.base;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.lkl.ActivityManager;
import com.lkl.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 *
 * Created by LeiKelong on 2016/6/21.
 */
public abstract class BaseFragmentActivity extends AppCompatActivity {
    private static String TAG = BaseActivity.class.getSimpleName();
    public LinearLayout contentView;

    public Toolbar toolbar;

    public FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityManager.getAppManager().addActivity(this);

        //KitKat translucent modes
        //setTranslucentStatus(this, true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //结束Activity&从堆栈中移除
        ActivityManager.getAppManager().finishActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {

        if ( R.layout.activity_base == layoutResID) {
            super.setContentView(R.layout.activity_base);
            contentView = (LinearLayout) findViewById(R.id.layout_center);
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            fab = (FloatingActionButton) findViewById(R.id.fab);
            setToolbar();
            contentView.removeAllViews();

        } else {
            View addView = LayoutInflater.from(this).inflate(layoutResID, null);
            contentView.addView(addView);
            initToolBar();
            initViews();
        }

    }


    private void setToolbar(){
        if (toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

//            toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.btn_top_back));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    public abstract void initToolBar();
    public abstract void initViews();


    protected ProgressDialog bongProgressDialog;


    protected void dismissProgressDialog() {
        try {
            if (bongProgressDialog != null && bongProgressDialog.isShowing()) {
                bongProgressDialog.dismiss();
            }
        } catch (final Exception e) {
            // Handle or log or ignore
        } finally {
            bongProgressDialog = null;
        }
    }

    protected void showProgressDialog(int rid){
        bongProgressDialog = ProgressDialog.show(this, null, getString(rid));
        bongProgressDialog.setCancelable(false);
    }
    protected void showProgressDialog(String msg){
        bongProgressDialog = ProgressDialog.show(this, null, msg);
        bongProgressDialog.setCancelable(false);
    }
    protected void showProgressDialog(){
        bongProgressDialog = ProgressDialog.show(this, null, getString(R.string.app_loading));
        bongProgressDialog.setCancelable(false);
    }


    @TargetApi(19)
    public void setTranslucentStatus(Activity activity, boolean on) {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            Window win = activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);

            SystemBarTintManager tintManager = new SystemBarTintManager(activity);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setNavigationBarTintEnabled(false);
            tintManager.setStatusBarTintColor(activity.getResources().getColor(R.color.colorPrimary));
            //tintManager.setNavigationBarTintColor(activity.getResources().getColor(R.color.colorPrimary));
            //			tintManager.setStatusBarTintResource(R.color.colorPrimary);
        }
    }
}