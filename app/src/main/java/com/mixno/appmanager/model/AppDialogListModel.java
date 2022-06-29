package com.mixno.appmanager.model;

import android.graphics.drawable.Drawable;
import android.view.View;

public class AppDialogListModel {

    public String text;
    public Drawable icon;
    public View.OnClickListener result;

    public AppDialogListModel(String text, Drawable icon, View.OnClickListener result) {
        this.text = text;
        this.icon = icon;
        this.result = result;
    }

    public String getText() {
        return text;
    }
    public Drawable getIcon() {
        return icon;
    }
    public View.OnClickListener getResult() {
        return result;
    }
}
