package com.lkl.utils;

import android.content.Context;

import com.lkl.IApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * Created by LeiKelong on 2016/6/3.
 */
public class Utils {


    public static String getFromAssets(String fileName) {
        String result = "";
        try {

            Context mContext  = IApplication.instance ;
            InputStreamReader inputReader = new InputStreamReader(mContext.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";

            while ((line = bufReader.readLine()) != null)
                result += line;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  result;
    }
}
