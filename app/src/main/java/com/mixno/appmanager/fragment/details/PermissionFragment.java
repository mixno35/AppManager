package com.mixno.appmanager.fragment.details;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mixno.appmanager.R;
import com.mixno.appmanager.adapter.details.PermissionAdapter;
import com.mixno.appmanager.model.AppModel;
import java.util.ArrayList;

public class PermissionFragment extends Fragment {

	public String packageName = "";

	private RecyclerView mRecyclerView;
	private RecyclerView.Adapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;
	

    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        View view = inflater.inflate(R.layout.fragment_permission, container, false); 
		mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

		//mRecyclerView.setNestedScrollingEnabled(true);
		
		AppModel appModel = new AppModel(getActivity());
		packageName = getActivity().getIntent().getStringExtra("packageName");

		try {
			mLayoutManager = new LinearLayoutManager(((Activity)getActivity()));
			mRecyclerView.setLayoutManager(mLayoutManager);
			mAdapter = new PermissionAdapter(getGrantedPermissions(getActivity(), packageName), packageName, ((Activity)getActivity()));
			mRecyclerView.setAdapter(mAdapter);
		} catch (Exception e){}
		
		return view; 
	}
	
	public static ArrayList<String> getGrantedPermissions(Context context, final String appPackage) {
		ArrayList<String> granted = new ArrayList<String>();
		try {
			PackageInfo pi = context.getPackageManager().getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS);
			for (int i = 0; i < pi.requestedPermissions.length; i++) {
				if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
					granted.add(pi.requestedPermissions[i]);
				}
			}
		} catch (Exception e) {}
		return granted;
	}
}
