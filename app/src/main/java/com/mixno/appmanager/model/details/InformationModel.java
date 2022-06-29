package com.mixno.appmanager.model.details;

public class InformationModel {
    private String name = "NaN";
    private String data = "NaN";
	private boolean clickable = false;
	
    public InformationModel(String name, String data, boolean clickable) {
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
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
	public boolean isClickable() {
		return clickable;
	}
	public void setClickable(boolean clickable) {
		this.clickable = clickable;
	}
}
