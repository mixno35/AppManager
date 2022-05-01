package com.xianitt.appmanager.fragment.details;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.ClassesActivity;
import com.xianitt.appmanager.ManifestActivity;
import com.xianitt.appmanager.PermissionsActivity;
import com.xianitt.appmanager.R;
import com.xianitt.appmanager.ResourcesActivity;
import com.xianitt.appmanager.adapter.details.DevAdapter;
import com.xianitt.appmanager.adapter.details.InformationAdapter;
import com.xianitt.appmanager.adapter.details.InformationGridAdapter;
import com.xianitt.appmanager.model.AppModel;
import com.xianitt.appmanager.model.details.DevModel;
import com.xianitt.appmanager.model.details.InformationModel;
import com.jaredrummler.apkparser.ApkParser;

import java.util.ArrayList;

public class InformationFragment extends Fragment {

	public static String packageName = "";
	private String typeApp = "User";
	
	private RecyclerView mRecyclerView;
	private InformationAdapter mAdapter;
    private ArrayList<InformationModel> mList = new ArrayList<>();
	private LinearLayoutManager mLayoutManager;
	
	private RecyclerView mRecyclerViewGrid;
	private InformationGridAdapter mAdapterGrid;
    private ArrayList<InformationModel> mListGrid = new ArrayList<>();
	private LinearLayoutManager mLayoutManagerGrid;
	
	private RecyclerView mRecyclerViewDev;
	private DevAdapter mAdapterDev;
    private ArrayList<DevModel> mListDev = new ArrayList<>();
	private LinearLayoutManager mLayoutManagerDev;

	private ApkParser apkParser;
	
    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        View view = inflater.inflate(R.layout.fragment_information, container, false); 
		mRecyclerView = view.findViewById(R.id.recycler_view);
		mRecyclerViewGrid = view.findViewById(R.id.recycler_view_grid);
		mRecyclerViewDev = view.findViewById(R.id.recycler_view_dev);
		
		//mRecyclerView.setNestedScrollingEnabled(true);
		
		AppModel appModel = new AppModel(getActivity());
		packageName = getActivity().getIntent().getStringExtra("packageName");

		try {
			ApplicationInfo appInfo = getActivity().getPackageManager().getApplicationInfo(packageName, 0);
			apkParser = ApkParser.create(appInfo);
		} catch (Exception e) {}

		if(!appModel.isSystemApp(packageName)) {
			typeApp= "System";
		}
		
		mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
		
		mLayoutManagerGrid = new GridLayoutManager(getActivity(), 3);
        mRecyclerViewGrid.setLayoutManager(mLayoutManagerGrid);
		
		mLayoutManagerDev = new GridLayoutManager(getActivity(), 4);
        mRecyclerViewDev.setLayoutManager(mLayoutManagerDev);
		
		mList.add(new InformationModel("Package", packageName, true));
		mList.add(new InformationModel("App name", appModel.getAppName(packageName), true));
		mList.add(new InformationModel("Install time", appModel.getAppInstallTime(packageName), false));
		mList.add(new InformationModel("Last update time", appModel.getAppLastUpdateTime(packageName), false));
		mList.add(new InformationModel("Type app", typeApp, false));
		mList.add(new InformationModel("Preferred language", apkParser.getPreferredLocale().getDisplayLanguage() + " (" + apkParser.getPreferredLocale() + ")", false));
		mList.add(new InformationModel("Developer", appModel.getAppDeveloper(packageName), true));
		
		mListGrid.add(new InformationModel("Size", appModel.getAppSize(packageName), false));
		mListGrid.add(new InformationModel("Version name", appModel.getVersionName(packageName), true));
		mListGrid.add(new InformationModel("Version code", appModel.getVersionCode(packageName), true));
		
		mListDev.add(new DevModel("Classes", packageName, R.drawable.baseline_view_carousel_24, new Intent(getActivity(), ClassesActivity.class).putExtra("packageName", packageName)));
		mListDev.add(new DevModel("Permissions", packageName, R.drawable.baseline_security_24, new Intent(getActivity(), PermissionsActivity.class).putExtra("packageName", packageName)));
		mListDev.add(new DevModel("Manifest", packageName, R.drawable.baseline_android_24, new Intent(getActivity(), ManifestActivity.class).putExtra("packageName", packageName)));
		mListDev.add(new DevModel("Resources", packageName, R.drawable.baseline_resources_24, new Intent(getActivity(), ResourcesActivity.class).putExtra("packageName", packageName)));

		mAdapter = new InformationAdapter(getActivity(), mList);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();
		
		mAdapterGrid = new InformationGridAdapter(getActivity(), mListGrid);
		mRecyclerViewGrid.setAdapter(mAdapterGrid);
		mAdapterGrid.notifyDataSetChanged();
		
		mAdapterDev = new DevAdapter(getActivity(), mListDev);
		mRecyclerViewDev.setAdapter(mAdapterDev);
		mAdapterDev.notifyDataSetChanged();
		
		return view; 
	} 
}
