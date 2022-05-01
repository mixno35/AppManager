package com.xianitt.appmanager.model;

public class AppDataModel {
	
	public String packageName;
	public String appName;
	
	public AppDataModel(String packageName, String appName) {
		this.packageName = packageName;
		this.appName = appName;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public String getAppName() {
		return appName;
	}
}
