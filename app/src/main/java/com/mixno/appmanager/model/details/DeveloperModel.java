package com.mixno.appmanager.model.details;

public class DeveloperModel {
    private String name;
    private String data;
	private boolean clickable;

    public DeveloperModel(String name, String data, boolean clickable) {
        this.name = name;
        this.data = data;
		this.clickable = clickable;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
	public void setClick(boolean clickable) {
        this.clickable = clickable;
    }
	public boolean isClickable() {
        return clickable;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
