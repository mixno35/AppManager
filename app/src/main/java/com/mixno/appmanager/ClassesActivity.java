package com.mixno.appmanager;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mixno.appmanager.data.Data;
import com.jaredrummler.apkparser.ApkParser;
import com.mixno.appmanager.R;
import com.mixno.appmanager.adapter.details.ClassesAdapter;
import com.mixno.appmanager.model.details.ClassesModel;
import dalvik.system.DexFile;

import java.util.ArrayList;
import java.util.Enumeration;

public class ClassesActivity extends AppCompatActivity {

	public String packageName = "";

	private Toolbar mToolbar;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private ClassesAdapter mAdapter;
	private LinearLayout progress;
	private TextView textLog;

	private String packageCodePath;

	private ApkParser apkParser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);
		mRecyclerView = findViewById(R.id.recycler_view);
		mToolbar = findViewById(R.id.toolbar);
		progress = findViewById(R.id.progressLayout);
		textLog = findViewById(R.id.txtLog);
		setSupportActionBar(mToolbar);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});

		mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
		mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setNestedScrollingEnabled(true);

		packageName = getIntent().getStringExtra("packageName");

		changeTextLog("Finding classes...");
		
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		packageCodePath = getPackageCodePath();
		packageCodePath = packageCodePath.replaceAll("com.doctorsteep.appmanager", packageName);

		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, 0);
			apkParser = ApkParser.create(appInfo);

			// apkParser.getAndroidManifest().activities.size();

			// Toast.makeText(getApplicationContext(), apkParser.getDexClasses().length + "", Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error parse finding classes - Data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
			changeTextLog("Error parse finding classes - Data: " + e.getMessage());
		}
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
				public void run() {
					Handler handler1 = new Handler();
					handler1.postDelayed(new Runnable() {
							public void run() {
								mAdapter = new ClassesAdapter(getApplicationContext(), getClassesOfPackage(packageName, true));
								mRecyclerView.setAdapter(mAdapter);
								mAdapter.notifyDataSetChanged();
							}
						}, 100);

					getSupportActionBar().setTitle("Classes (" + getClassesOfPackage(packageName, false).size() + ")");
				}
			}, 1000);
		
		getSupportActionBar().setTitle("Classes");
    }
    private void changeTextLog(final String text) {
		textLog.post(new Runnable() {
			@Override
			public void run() {
				textLog.setText(text);
			}
		});
	}

	private ArrayList<ClassesModel> getClassesOfPackage(String packageName, boolean toast) {
		final ArrayList<ClassesModel> classes = new ArrayList<ClassesModel>();
		try {
			packageCodePath = getPackageManager().getApplicationInfo(packageName, 0).sourceDir;
			DexFile dexFile = new DexFile(packageCodePath);
			//Toast.makeText(context, packageCodePath, Toast.LENGTH_SHORT).show();
			for (Enumeration<String> java = dexFile.entries(); java.hasMoreElements(); ) {
				String className = java.nextElement();
				if (className.contains(packageName) && !className.contains("$")) {
					classes.add(new ClassesModel(className.substring(className.lastIndexOf(".") + 1, className.length()).toString(), className, packageName));
					//classes.add(new ClassesModel(className));
				}
			}
			if (classes.size() > 0) {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progress.setVisibility(View.GONE);
					}
				});
			}
			if (classes.size() == 0) {
				if (toast) {
					Toast.makeText(getApplicationContext(), "No finded classes", Toast.LENGTH_SHORT).show();
				}
				changeTextLog("No finded classes");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (toast) {
				Toast.makeText(getApplicationContext(), "Error parse finding classes - " + packageName, Toast.LENGTH_SHORT).show();
				changeTextLog("Error parse finding classes - " + packageName);
			}
		}

		return classes;
	}
	
	/*private ArrayList<ClassesModel> getClasses() {
		final ArrayList<ClassesModel> classes = new ArrayList<ClassesModel>();
		try {
			ApkParser parser = ApkParser.create(getPackageManager(), packageName);
			AndroidManifest androidManifest = parser.getAndroidManifest();
			for (AndroidComponent component : androidManifest.getComponents()) {
				if (!component.intentFilters.isEmpty()) {
					for (IntentFilter intentFilter : component.intentFilters) {
						// Got an intent filter for activity/service/provider/receiver.
						String t = parser.getAndroidManifest().activities.name;
					}
				}
			}
		} catch(Exception e) {}
		
		return classes;
	}*/
}
