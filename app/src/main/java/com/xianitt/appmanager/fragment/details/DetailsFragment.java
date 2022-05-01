package com.xianitt.appmanager.fragment.details;

import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.adapter.details.DetailsFragAdapter;
import com.xianitt.appmanager.data.InstallLocation;
import com.xianitt.appmanager.model.AppModel;
import com.xianitt.appmanager.model.details.DeveloperModel;

import java.io.File;
import java.util.ArrayList;
import com.xianitt.appmanager.data.AndroidSdk;
import com.jaredrummler.apkparser.ApkParser;

public class DetailsFragment extends Fragment {

	private RecyclerView mRecyclerView;
	private DetailsFragAdapter mAdapter;
    private ArrayList<DeveloperModel> mList = new ArrayList<>();
	private LinearLayoutManager mLayoutManager;

	private String packageName = "";
	
	private String nan = "NaN";
	
	private int minSDK = 0;
	private int targetSDK = 0;
	private String icon = nan;
	private String installLocation = nan;
	private String classNAME = nan;
	private String backupAGENT = nan;
	private String dataDIR = nan;
	private boolean appENABLED = false;
	private String manageSPACE = nan;
	private String name = nan;
	private String nativeLIBRARY = nan;
	private String protectedDeviceDir = nan;
	private String publicSourceDir = nan;
	private String sourceDir = nan;
	private String pathIcon = nan;
	private String baseApk = nan;
	private String uid = nan;
	private String test = nan;

	private ApkParser apkParser;

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        View view = inflater.inflate(R.layout.fragment_details, container, false); 
		mRecyclerView = view.findViewById(R.id.recycler_view);

		//mRecyclerView.setNestedScrollingEnabled(true);

		AppModel appModel = new AppModel(getActivity());
		packageName = getActivity().getIntent().getStringExtra("packageName");

		try {
			ApplicationInfo appInfo = getActivity().getPackageManager().getApplicationInfo(packageName, 0);
			apkParser = ApkParser.create(appInfo);
		} catch (Exception e) {}

		//minSdk
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				minSDK = getActivity().getPackageManager().getApplicationInfo(packageName, 0).minSdkVersion;
			}
		} catch (Exception e) {}
		
		//targetSdk
		try {
			targetSDK = getActivity().getPackageManager().getApplicationInfo(packageName, 0).targetSdkVersion;
		} catch (Exception e) {}

		//icon
		try {
			icon = apkParser.getApkMeta().icon;
			if(icon.equals("")) {
				icon = nan;
			}
		} catch (Exception e) {
			icon = nan;
		}

		//installLocation
		try {
			installLocation = InstallLocation.string(apkParser.getApkMeta().installLocation) + " (" + apkParser.getApkMeta().installLocation + ")";
			if(installLocation.equals("")) {
				installLocation = nan;
			}
		} catch (Exception e) {
			installLocation = nan;
		}
		
		//className
		try {
			classNAME = getActivity().getPackageManager().getApplicationInfo(packageName, 0).className;
			if(classNAME.equals("")) {
				classNAME = nan;
			}
		} catch (Exception e) {
			classNAME = nan;
		}

		//backupAgentName
		try {
			backupAGENT = getActivity().getPackageManager().getApplicationInfo(packageName, 0).backupAgentName;
			if(backupAGENT.equals("")) {
				backupAGENT = nan;
			}
		} catch (Exception e) {
			backupAGENT = nan;
		}
		
		//dataDir
		try {
			dataDIR = getActivity().getPackageManager().getApplicationInfo(packageName, 0).dataDir;
			if(dataDIR.equals("")) {
				dataDIR = nan;
			}
		} catch (Exception e) {
			dataDIR = nan;
		}
		
		//enabled
		try {
			appENABLED = getActivity().getPackageManager().getApplicationInfo(packageName, 0).enabled;
		} catch (Exception e) {
			appENABLED = false;
		}
		
		//manageSpaceActivity
		try {
			manageSPACE = getActivity().getPackageManager().getApplicationInfo(packageName, 0).manageSpaceActivityName;
			if(manageSPACE.equals("")) {
				manageSPACE = nan;
			}
		} catch (Exception e) {
			manageSPACE = nan;
		}
		
		//name
		try {
			name = getActivity().getPackageManager().getApplicationInfo(packageName, 0).name;
			if(name.equals("")) {
				name = nan;
			}
		} catch (Exception e) {
			name = nan;
		}
		
		//nativeLibraryDir
		try {
			nativeLIBRARY = getActivity().getPackageManager().getApplicationInfo(packageName, 0).nativeLibraryDir;
			if(nativeLIBRARY.equals("")) {
				nativeLIBRARY = nan;
			}
		} catch (Exception e) {
			nativeLIBRARY = nan;
		}
		
		//protectedDeviceDir
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
				protectedDeviceDir = getActivity().getPackageManager().getApplicationInfo(packageName, 0).deviceProtectedDataDir;
			}
			if(protectedDeviceDir.equals("")) {
				protectedDeviceDir = nan;
			}
		} catch (Exception e) {
			protectedDeviceDir = nan;
		}
		
		//publicSourceDir
		try {
			publicSourceDir = getActivity().getPackageManager().getApplicationInfo(packageName, 0).publicSourceDir;
			if(publicSourceDir.equals("")) {
				publicSourceDir = nan;
			}
		} catch (Exception e) {
			publicSourceDir = nan;
		}
		
		//sourceDir
		try {
			sourceDir = getActivity().getPackageManager().getApplicationInfo(packageName, 0).sourceDir;
			if(sourceDir.equals("")) {
				sourceDir = nan;
			}
		} catch (Exception e) {
			sourceDir = nan;
		}

		//pathIcon
		try {
			pathIcon = apkParser.getIconFile().path;
			if(pathIcon.equals("")) {
				pathIcon = nan;
			}
		} catch (Exception e) {
			pathIcon = nan;
		}

		//baseApk
		try {
			baseApk = getActivity().getPackageManager().getApplicationInfo(packageName, 0).publicSourceDir + File.separator + "base.apk";
			if(baseApk.equals("")) {
				baseApk = nan;
			}
		} catch (Exception e) {
			baseApk = nan;
		}
		
		//uid
		try {
			uid = getActivity().getPackageManager().getApplicationInfo(packageName, 0).uid + "";
			if(uid.equals("")) {
				uid = nan;
			}
		} catch (Exception e) {
			uid = nan;
		}

		//test
		try {
			test = "" + "";
			if(test.equals("")) {
				test = nan;
			}
		} catch (Exception e) {
			test = nan;
		}
		
		
		mLayoutManager = new GridLayoutManager(getActivity(), 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
		
		
		
		if(minSDK != 0) {
			mList.add(new DeveloperModel("Minimal OS", AndroidSdk.getFullAndroidName(minSDK) + " (" + minSDK + ")", false));
		}
		if(targetSDK != 0) {
			mList.add(new DeveloperModel("Target OS", AndroidSdk.getFullAndroidName(targetSDK) + " (" + targetSDK + ")", false));
		}
		if(!icon.equals(nan)) {
			mList.add(new DeveloperModel("Icon", icon, false));
		}
		if(!installLocation.equals(nan)) {
			mList.add(new DeveloperModel("Install location", installLocation, false));
		}
		if(!classNAME.equals(nan)) {
			mList.add(new DeveloperModel("Class name", classNAME, true));
		}
		if(!backupAGENT.equals(nan)) {
			mList.add(new DeveloperModel("Backup agent", backupAGENT, true));
		}
		if(!dataDIR.equals(nan)) {
			mList.add(new DeveloperModel("Data directory", dataDIR, true));
		}
		//mList.add(new DeveloperModel("enabled", String.valueOf(appENABLED), false));
		if(!manageSPACE.equals(nan)) {
			mList.add(new DeveloperModel("Space activity manager", manageSPACE, false));
		}
		if(!name.equals(nan)) {
			mList.add(new DeveloperModel("Application", name, true));
		}
		if(!nativeLIBRARY.equals(nan)) {
			mList.add(new DeveloperModel("Native libraries directory", nativeLIBRARY, true));
		}
		if(!protectedDeviceDir.equals(nan)) {
			mList.add(new DeveloperModel("Device protected data directory", protectedDeviceDir, true));
		}
		if(!publicSourceDir.equals(nan)) {
			mList.add(new DeveloperModel("Public source directory", publicSourceDir, true));
		}
		if(!sourceDir.equals(nan)) {
			mList.add(new DeveloperModel("Source directory", sourceDir, true));
		}
		/*if(!pathIcon.equals(nan)) {
			mList.add(new DeveloperModel("Path icon", pathIcon, true));
		}*/
		/*if(!baseApk.equals(nan)) {
			mList.add(new DeveloperModel("APK base", baseApk, true));
		}*/
		if(!uid.equals(nan)) {
			mList.add(new DeveloperModel("UID", uid, true));
		}
		/*if(!test.equals(nan)) {
			mList.add(new DeveloperModel("Test", test, false));
		}*/
		
		mAdapter = new DetailsFragAdapter(getActivity(), mList);
		mRecyclerView.setAdapter(mAdapter);
		mAdapter.notifyDataSetChanged();

		return view; 
	} 
}
