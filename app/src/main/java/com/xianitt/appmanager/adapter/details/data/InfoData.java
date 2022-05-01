package com.xianitt.appmanager.adapter.details.data;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.xianitt.appmanager.data.Data;

public class InfoData {
	
	public static void setShowData(final Context context, String name, final String data) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(name)
			.setMessage(data)
			.setCancelable(true)
			.setPositiveButton("Ok",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					dialog.dismiss();
				}
			})
			.setNegativeButton("Copy",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Data.setClipboard(context, data);
				}
			});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
