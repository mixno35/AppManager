package com.xianitt.appmanager.data;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Data {
	
	public static boolean changeSetting = false;
	
	public static void setTheme(Context context) {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		
		customFontAll(context, "SERIF", "fonts/main.ttf");
		
		AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		if(sp.getBoolean("themeKey", false) == true) {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
		} else {
			AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
		}
	}
	
	public static void customFontAll(Context _con, String _defaultFontNameToOverride, String _customFontFileNameInAssets) {
        try {
            final Typeface customFontTypeface = Typeface.createFromAsset(_con.getAssets(), _customFontFileNameInAssets);
			final Field defaultFontTypefaceField = Typeface.class.getDeclaredField(_defaultFontNameToOverride);
            defaultFontTypefaceField.setAccessible(true);
            defaultFontTypefaceField.set(null, customFontTypeface);
        } catch (Exception e) {
            //Log.e("Can not set custom font " + customFontFileNameInAssets + " instead of " + defaultFontNameToOverride);
        }
	}
	
	public static void shareApplication(Context context, String packageName, String name) throws Exception{
		ProgressDialog pd = new ProgressDialog(context);
		pd.setMessage("Loading...");
		pd.setCancelable(false);

		ApplicationInfo app = null;
		PackageManager packageManager = context.getPackageManager();
		try {
			app = packageManager.getApplicationInfo(packageName, 0);
		} catch (PackageManager.NameNotFoundException e) {}

		String filePath = app.sourceDir;
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("*/*");
		File originalApk = new File(filePath);
		pd.show();

		try {
			File tempFile = new File(context.getExternalCacheDir() + "/apk");
			if (!tempFile.isDirectory())
				if (!tempFile.mkdirs())
					return;
			tempFile = new File(tempFile.getPath() + "/" + name + ".apk");
			if (!tempFile.exists()) {
				if (!tempFile.createNewFile()) {
					return;
				}
			}
			Uri apk = FileProvider.getUriForFile(context, "com.doctorsteep.appmanager.provider", tempFile);

			//Copy file to new location
			InputStream in = new FileInputStream(originalApk);
			OutputStream out = new FileOutputStream(tempFile);

			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
			intent.putExtra(Intent.EXTRA_STREAM, apk);
			context.startActivity(Intent.createChooser(intent, "Share"));
			pd.dismiss();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void settingsApp(Context context, String packageName) throws Exception {
		Intent intent = new Intent("android.intent.action.MAIN");
		intent.addCategory("android.intent.category.HOME"); 

		Intent inten = new Intent();
		inten.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		Uri uri = Uri.fromParts("package", packageName, null);
		inten.setData(uri);
		context.startActivity(inten);
	}
	
	public static boolean isVerifyGP(Context context, String packageName) throws Exception {
		List<String> validInstallers = new ArrayList<>(Arrays.asList("com.android.vending", "com.google.android.feedback"));
		final String installer = context.getPackageManager().getInstallerPackageName(packageName);
		return installer != null && validInstallers.contains(installer);
	}
	
	public static void searchAppGooglePlay(Context context, String packageName) {
		try {
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + packageName)));
		} catch (ActivityNotFoundException anfe) {
			context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + packageName)));
		}
	}
	
	public static int randomNumber(int min, int max) {
		int random = 0;
		return random = new Random().nextInt((max - min) + 1) + min;
	}
	
	public static void setClipboard(Context context, String text) {
		ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE); 
		ClipData clip = ClipData.newPlainText("Copied text", text);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(context, "Text copied!", Toast.LENGTH_SHORT).show();
	}
	
	public static String modifyDate(String incomeDate, String dateModify) {
		String dateAsString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(dateModify);
            Date d = sdf.parse(incomeDate);
            SimpleDateFormat sdfOut = new SimpleDateFormat("dd MMM yyyy");
           	dateAsString = sdfOut.format(d);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        return dateAsString;
    }
	
	public static String sizeFormat(long bytes) {
		String s = bytes < 0 ? "-" : "";
		long b = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);
		return b < 1000L ? bytes + " " + "B"
            : b < 999_950L ? String.format("%s%.1f" + " " + "KB", s, b / 1e3)
            : (b /= 1000) < 999_950L ? String.format("%s%.1f" + " " + "MB", s, b / 1e3)
            : (b /= 1000) < 999_950L ? String.format("%s%.1f" + " " + "GB", s, b / 1e3)
            : (b /= 1000) < 999_950L ? String.format("%s%.1f" + " " + "TB", s, b / 1e3)
            : (b /= 1000) < 999_950L ? String.format("%s%.1f" + " " + "PB", s, b / 1e3)
            : String.format("%s%.1f" + "EB", s, b / 1e6);
	}
}
