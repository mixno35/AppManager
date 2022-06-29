package com.mixno.appmanager.model.details;

public class ClassesModel {

	private String className;
	private String fullClassName;
	private String packageName;

	public ClassesModel(String className, String fullClassName, String packageName) {
		this.className = className;
		this.fullClassName = fullClassName;
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}
	public String getFullClassName() {
		return fullClassName;
	}
	public String getPackageName() {
		return packageName;
	}
}
