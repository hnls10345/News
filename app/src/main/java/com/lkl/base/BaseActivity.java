package com.lkl.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.lkl.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 *
 * Created by LeiKelong on 2016/6/2.
 */
public abstract  class BaseActivity extends AppCompatActivity {
    private static String TAG = BaseActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //KitKat translucent modes
        //setTranslucentStatus(this, true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


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
