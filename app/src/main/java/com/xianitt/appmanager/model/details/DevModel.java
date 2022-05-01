package com.xianitt.appmanager.model.details;
import android.content.Intent;

public class DevModel {
	
	private String name;
	private String packageName;
	private int icon;
	private Intent intent;
	
	public DevModel(String name, String packageName, int icon, Intent intent) {
		this.name = name;
		this.packageName = packageName;
		this.icon = icon;
		this.intent = intent;
	}
	
	public String getName() {
		return name;
	}
	public String getPackageName() {
		return packageName;
	}
	public int getIcon() {
		return icon;
	}
	public Intent getIntengClass() {
		return intent;
	}
}
