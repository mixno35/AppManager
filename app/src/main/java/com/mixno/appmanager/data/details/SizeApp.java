package com.mixno.appmanager.data.details;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import com.mixno.appmanager.R;
import com.mixno.appmanager.model.AppModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class SizeApp {
    
	public static void setSizeApp(Context context, String packageName) {
		final AppModel appModel = new AppModel(context);
		final BottomSheetDialog bsd = new BottomSheetDialog(context);
		View view = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_size_app, null);  
		bsd.setContentView(view);

		bsd.show(); 
	}
}
