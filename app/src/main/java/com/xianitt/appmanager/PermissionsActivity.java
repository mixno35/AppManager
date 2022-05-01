package com.xianitt.appmanager;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.adapter.details.PermissionAdapter;
import com.xianitt.appmanager.data.Data;
import com.xianitt.appmanager.model.AppModel;
import java.util.ArrayList;

public class PermissionsActivity extends AppCompatActivity {

	public String packageName = "";

	private Toolbar mToolbar;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private PermissionAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
		mRecyclerView = findViewById(R.id.recycler_view);
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);

		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});

		packageName = getIntent().getStringExtra("packageName");

		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		AppModel appModel = new AppModel(this);
		packageName = getIntent().getStringExtra("packageName");

		try {
			mLayoutManager = new LinearLayoutManager(this);
			mRecyclerView.setLayoutManager(mLayoutManager);
			mAdapter = new PermissionAdapter(getGrantedPermissions(packageName), packageName, this);
			mRecyclerView.setAdapter(mAdapter);
		} catch (Exception e){}

		getSupportActionBar().setTitle("Permissions (" + getGrantedPermissions(packageName).size() + ")");

	}

	private ArrayList<String> getGrantedPermissions(final String appPackage) {
		ArrayList<String> granted = new ArrayList<String>();
		try {
			PackageInfo pi = getPackageManager().getPackageInfo(appPackage, PackageManager.GET_PERMISSIONS);
			for (int i = 0; i < pi.requestedPermissions.length; i++) {
				if ((pi.requestedPermissionsFlags[i] & PackageInfo.REQUESTED_PERMISSION_GRANTED) != 0) {
					granted.add(pi.requestedPermissions[i]);
				}
			}
		} catch (Exception e) {}
		return granted;
	}
}
