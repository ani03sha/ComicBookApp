package com.digitalgeenie.comicbookapp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void shortToast(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void longToast(String msg, Context ctx) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }
}
