package com.mixno.appmanager.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mixno.appmanager.DetailsActivity;
import com.mixno.appmanager.R;
import com.mixno.appmanager.data.Data;
import com.mixno.appmanager.model.AppModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class DialogApp {

	public static void show(final Context context, final String packageName, final int type) {
		final AppModel appModel = new AppModel(context);
		final BottomSheetDialog bsd = new BottomSheetDialog(context);
		View view = ((Activity)context).getLayoutInflater().inflate(R.layout.dialog_app, null);  
		bsd.setContentView(view);
		
		final TextView textName = view.findViewById(R.id.name_id);
		final TextView textPackage = view.findViewById(R.id.name_package);
		final ImageView icon_id = view.findViewById(R.id.icon_id);
		final Button btnGp = view.findViewById(R.id.btnGp);

//		final ArrayList<AppDialogListModel> list = null;
//		final RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view);
//		final LinearLayoutManager mLayoutManager = new GridLayoutManager(context, 1);
//		mRecyclerView.setLayoutManager(mLayoutManager);
//
//		list.add(new AppDialogListModel("Open", context.getDrawable(R.drawable.baseline_play_arrow_24), openApp(context, packageName)));
//
//		final AppDialogListAdapter mAdapter = new AppDialogListAdapter(context, list);
//		// mRecyclerView.setNestedScrollingEnabled(true);
		
		final LinearLayout btnOpen = view.findViewById(R.id.btnOpen);
		final LinearLayout btnRemove = view.findViewById(R.id.btnRemove);
		final LinearLayout btnShare = view.findViewById(R.id.btnShare);
		final LinearLayout btnSettings = view.findViewById(R.id.btnSettings);
		final LinearLayout btnDetails = view.findViewById(R.id.btnDetails);

		textName.post(new Runnable() {
			@Override
			public void run() {
				textName.setText(appModel.getAppName(packageName));
			}
		});
		textPackage.post(new Runnable() {
			@Override
			public void run() {
				textPackage.setText(packageName);
			}
		});
		icon_id.post(new Runnable() {
			@Override
			public void run() {
				icon_id.setImageDrawable(appModel.getAppIcon(packageName));
			}
		});
		
		if(type == 1) {
			((Activity)context).runOnUiThread(new Runnable() {
					@Override
					public void run() {
						btnDetails.setVisibility(View.GONE);
					}
				});
		}

		((Activity)context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					if(!appModel.isSystemApp(packageName)) {
						btnRemove.setVisibility(View.GONE);
					}
				}
			});

		btnOpen.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
					if(intent != null){
						context.startActivity(intent);
					} else {
						Toast.makeText(context, "Error open app, Please Try Again.", Toast.LENGTH_LONG).show();
					}
				}
			});
		btnRemove.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent intent = new Intent(Intent.ACTION_DELETE);
					if(intent != null) {
						intent.setData(Uri.parse("package:" + packageName));
						context.startActivity(intent);
					} else {
						Toast.makeText(context, "Error remove app, Please Try Again.", Toast.LENGTH_LONG).show();
					}
				}
			});
		btnShare.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					try {
						Data.shareApplication(context, packageName, appModel.getAppName(packageName));
					} catch (Exception e) {
						Toast.makeText(context, "Error share apk" + " - " + e.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}
			});
		btnSettings.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					try {
						Data.settingsApp(context, packageName);
					} catch (Exception e) {
						Toast.makeText(context, "Error open settings" + " - " + e.getMessage(), Toast.LENGTH_SHORT).show();
					}
				}
			});
		btnDetails.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					context.startActivity(new Intent(context, DetailsActivity.class).putExtra("packageName", packageName));
				}
			});
		btnGp.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					Data.searchAppGooglePlay(context, packageName);
				}
			});
		
		bsd.show(); 
	}
}
