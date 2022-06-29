package com.mixno.appmanager.permission;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.Nullable;

import com.mixno.appmanager.R;

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
            case "android.permission.CHANGE_NETWORK_STATE":
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
            case "android.permission.USE_FINGERPRINT":
                return ctx.getResources().getDrawable(R.drawable.permission_fingerprint);
            case "android.permission.BLUETOOTH":
                return ctx.getResources().getDrawable(R.drawable.permission_bluetooth);
            case "android.permission.ACCESS_WIFI_STATE":
                return ctx.getResources().getDrawable(R.drawable.permission_wifi_state);
            case "android.permission.RECORD_AUDIO":
                return ctx.getResources().getDrawable(R.drawable.permission_record);
            case "android.permission.FOREGROUND_SERVICE":
                return ctx.getResources().getDrawable(R.drawable.permission_service);
            case "android.permission.REORDER_TASKS":
                return ctx.getResources().getDrawable(R.drawable.permission_task);
            case "android.permission.ACCESS_MEDIA_LOCATION":
                return ctx.getResources().getDrawable(R.drawable.permission_media);
            case "android.permission.USE_BIOMETRIC":
                return ctx.getResources().getDrawable(R.drawable.permission_biometric);
            case "android.permission.NFC":
                return ctx.getResources().getDrawable(R.drawable.permission_nfc);
            case "android.permission.GET_PACKAGE_SIZE":
                return ctx.getResources().getDrawable(R.drawable.permission_package_size);


            case "com.android.vending.CHECK_LICENSE":
                return ctx.getResources().getDrawable(R.drawable.permission_check_license);


            case "com.google.android.c2dm.permission.RECEIVE":
                return ctx.getResources().getDrawable(R.drawable.permission_receive);
            default:
                return ctx.getResources().getDrawable(R.drawable.permission_no_icon);
        }
    }
}
