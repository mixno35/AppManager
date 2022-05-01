package com.xianitt.appmanager.model;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.data.Data;
import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AppModel extends Thread {

    Context context1;

    public AppModel(Context context2){
        context1 = context2;
    }

    public ArrayList<AppDataModel> getInstalledApp(){
        ArrayList<AppDataModel> appPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo resolveInfo : resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if(!isSystemPackage(resolveInfo)){
                appPackageName.add(new AppDataModel(activityInfo.applicationInfo.packageName, getAppName(activityInfo.applicationInfo.packageName)));
            }
        }

        return appPackageName;
    }
	
	public ArrayList<AppDataModel> getInstalledSystemApp(){
        ArrayList<AppDataModel> appPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo resolveInfo : resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            if(isSystemPackage(resolveInfo)){
                appPackageName.add(new AppDataModel(activityInfo.applicationInfo.packageName, getAppName(activityInfo.applicationInfo.packageName)));
            }
        }

        return appPackageName;
    }
	
	public ArrayList<AppDataModel> getInstalledAllApp(){
        ArrayList<AppDataModel> appPackageName = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_MAIN,null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);

        List<ResolveInfo> resolveInfoList = context1.getPackageManager().queryIntentActivities(intent, 0);

        for(ResolveInfo resolveInfo : resolveInfoList){
            ActivityInfo activityInfo = resolveInfo.activityInfo;

            appPackageName.add(new AppDataModel(activityInfo.applicationInfo.packageName, getAppName(activityInfo.applicationInfo.packageName)));
        }

        return appPackageName;
    }

    public ArrayList<AppDataModel> getInstalledAllHApp(){
        ArrayList<AppDataModel> appPackageName = new ArrayList<>();

        List<ApplicationInfo> resolveInfoList = context1.getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);

        for(ApplicationInfo resolveInfo : resolveInfoList){
            appPackageName.add(new AppDataModel(resolveInfo.packageName, getAppName(resolveInfo.packageName)));
        }

        return appPackageName;
    }

    public boolean isSystemPackage(ResolveInfo resolveInfo){
        return ((resolveInfo.activityInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }

    public Drawable getAppIcon(String packageName){
        Drawable drawable;

        try{
            drawable = context1.getPackageManager().getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
            drawable = ContextCompat.getDrawable(context1, R.drawable.ic_launcher);
        }
        return drawable;
    }

    public String getAppName(String packageName){
        String name = "NaN";

        ApplicationInfo applicationInfo;
        PackageManager packageManager = context1.getPackageManager();

        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 0);

            if(applicationInfo != null){
                name = (String)packageManager.getApplicationLabel(applicationInfo);
            }
        }catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return name;
    }
	
	public String getVersionName(String packageName){
        String version = "NaN";

        try {
			PackageInfo pInfo = context1.getPackageManager().getPackageInfo(packageName, 0);
			version = pInfo.versionName;
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
        return version;
    }
	
	public String getVersionCode(String packageName){
        String version = "NaN";

        try {
			PackageInfo pInfo = context1.getPackageManager().getPackageInfo(packageName, 0);
			version = pInfo.versionCode + "";
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
        return version;
    }
	
	public String getAppLastUpdateTime(String ApkPackageName){
        String Update = "NaN";
		try {
			PackageInfo pInfo = context1.getPackageManager().getPackageInfo(ApkPackageName, 0);
			Date updateTime = new Date(pInfo.lastUpdateTime);
			Update = Data.modifyDate(updateTime.toString(), "yyyy-MM-dd");
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

        return Update;
    }
	
	public String getAppInstallTime(String ApkPackageName){
        String Install = "NaN";
		try {
			PackageInfo pInfo = context1.getPackageManager().getPackageInfo(ApkPackageName, 0);
			Date installTime = new Date(pInfo.firstInstallTime);
			Install = Data.modifyDate(installTime.toString(), "yyyy-MM-dd");
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}

        return Install;
    }
	
	public String getAppSize(String ApkPackageName){
        String Install = "NaN";
		try {
			float value = new File(context1.getPackageManager().getApplicationInfo(ApkPackageName, 0).publicSourceDir).length();

			if (value <= 0) {
				value = 1;
			}
			Install = Data.sizeFormat((long)value);
		} catch(Exception e) {}

        return Install;
    }
	
	public String getAppDeveloper(String pkg) {
		String[] split = pkg.split("\\.");
		String devName = split[1];
		String cap = devName.substring(0, 1).toUpperCase() + devName.substring(1);
		String value = cap.replaceAll("_", " ");

		return value;
	}
	
	public boolean isSystemApp(String _package) {
        try {
            ApplicationInfo ai = context1.getPackageManager().getApplicationInfo(_package, 0);
            int mask = ApplicationInfo.FLAG_SYSTEM | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP;
            return (ai.flags & mask) == 0;
        }catch (PackageManager.NameNotFoundException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }

	public boolean isGame(String packageName) {
		try {
			ApplicationInfo info = context1.getPackageManager().getApplicationInfo(packageName, 0);
			return (info.flags & ApplicationInfo.FLAG_IS_GAME) == ApplicationInfo.FLAG_IS_GAME;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}

	public boolean isAppRunning(final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context1.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningTaskInfo> processInfos = activityManager.getRunningTasks(Integer.MAX_VALUE);
        for (final ActivityManager.RunningTaskInfo processInfoResult : processInfos) {
            if (packageName.equalsIgnoreCase(processInfoResult.baseActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }
}
