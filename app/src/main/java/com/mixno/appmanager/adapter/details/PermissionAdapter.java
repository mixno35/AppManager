package com.mixno.appmanager.adapter.details;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mixno.appmanager.R;
import com.mixno.appmanager.permission.InfoPermission;
import java.util.ArrayList;

public class PermissionAdapter extends RecyclerView.Adapter<PermissionAdapter.PermissionViewHolder> {

    ArrayList<String> permissionList;
	Context context;
	String pkg;

    public PermissionAdapter(ArrayList<String> permissionList, String pkg, Context context) {
        this.permissionList = permissionList;
		this.context = context;
		this.pkg = pkg;
    }

    @Override
    public PermissionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_permission, parent, false);
        PermissionViewHolder viewHolder = new PermissionViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final PermissionViewHolder holder, final int position) {
        holder.text.setText(permissionList.get(position));
		
		try {
			PackageManager packageManager = context.getPackageManager();
			PermissionInfo pinfo = packageManager.getPermissionInfo(permissionList.get(position), PackageManager.GET_META_DATA);
			holder.name.setText(pinfo.loadLabel(packageManager).toString());
		} catch (Exception e) {
			
		}
		
		holder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					InfoPermission.setPermissionMenu(context, pkg, permissionList.get(position));
				}
			});
		holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View p1) {

					return false;
				}
			});
    }

    @Override
    public int getItemCount() {
        return permissionList.size();
    }

    public static class PermissionViewHolder extends RecyclerView.ViewHolder{

        protected TextView text;
		protected TextView name;

        public PermissionViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text_id);
			name = itemView.findViewById(R.id.name_id);
		}
    }
}
