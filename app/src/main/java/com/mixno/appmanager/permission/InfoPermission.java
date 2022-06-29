package com.mixno.appmanager.permission;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.mixno.appmanager.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class InfoPermission {

	public static BottomSheetDialog dialog;

	public static void setPermissionMenu(final Context ctx, final String pkg, final String prm) {
		((Activity)ctx).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					final DataPermission dt = new DataPermission(ctx);
					LayoutInflater inflater = (LayoutInflater) ctx.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
					View view = inflater.inflate(R.layout.dialog_permission, null);

					final TextView name = (TextView) view.findViewById(R.id.name_id);
					final TextView perm = (TextView) view.findViewById(R.id.text_id);
					final TextView desc = (TextView) view.findViewById(R.id.data_id);
					final ImageView icon = (ImageView) view.findViewById(R.id.icon_id);

					try {
						PackageManager packageManager = ctx.getPackageManager();
						PermissionInfo pinfo = packageManager.getPermissionInfo(prm, PackageManager.GET_META_DATA);
						name.setText(pinfo.loadLabel(packageManager).toString());
						perm.setText(prm);
						desc.setText(pinfo.loadDescription(packageManager).toString());
						icon.setImageDrawable(DataPermission.getPermissionDrawable(ctx, prm));
						//icon.setImageResource(pinfo.icon);
					} catch (Exception e) {
						((Activity)ctx).runOnUiThread(new Runnable() {
								@Override
								public void run() {
									desc.setVisibility(View.GONE);
									icon.setVisibility(View.GONE);
									name.setText("No permission information");
								}
							});
					}

					dialog = new BottomSheetDialog(ctx);
					dialog.setContentView(view);
					dialog.show();
				}
			});
	}
}
