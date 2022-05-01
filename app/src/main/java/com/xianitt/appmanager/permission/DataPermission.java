package com.xianitt.appmanager.permission;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.xianitt.appmanager.R;

public class DataPermission {

	private Context context;
	private static final String ANDROID = "android";

	public DataPermission(Context context) {
		this.context = context;
	}


	@Nullable
    public static Drawable getPermissionDrawable(Context ctx, String permission) {
        switch (permission) {
            case "android.permission.CHANGE_WIFI_MULTICAST_STATE":
                return ctx.getResources().getDrawable(R.drawable.permission_wifi);
            case "android.permission.INTERNET":
                return ctx.getResources().getDrawable(R.drawable.permission_internet);
            case "com.android.vending.BILLING":
                return ctx.getResources().getDrawable(R.drawable.permission_billing);
            case "android.permission.ACCESS_NETWORK_STATE":
                return ctx.getResources().getDrawable(R.drawable.permission_network);
            case "android.permission.VIBRATE":
                return ctx.getResources().getDrawable(R.drawable.permission_vibrate);
            case "android.permission.WRITE_EXTERNAL_STORAGE":
                return ctx.getResources().getDrawable(R.drawable.permission_storage);
            case "android.permission.READ_EXTERNAL_STORAGE":
                return ctx.getResources().getDrawable(R.drawable.permission_storage);
            case "android.permission.WAKE_LOCK":
                return ctx.getResources().getDrawable(R.drawable.permission_lock);
            case "com.google.android.c2dm.permission.RECEIVE":
                return ctx.getResources().getDrawable(R.drawable.permission_receive);
            default:
                return ctx.getResources().getDrawable(R.drawable.permission_no_icon);
        }
    }
}
