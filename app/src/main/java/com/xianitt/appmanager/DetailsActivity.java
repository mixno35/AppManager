package com.xianitt.appmanager;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.xianitt.appmanager.adapter.details.DetailsAdapter;
import com.xianitt.appmanager.data.Data;
import com.xianitt.appmanager.dialog.DialogApp;
import com.xianitt.appmanager.model.AppModel;
import com.google.android.material.tabs.TabLayout;
import com.jaredrummler.apkparser.ApkParser;

public class DetailsActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	private TabLayout tabLayout;
	private ViewPager viewPager;
	// private ImageView imageIconApp;
	
	private String packageName = "";

	private ApkParser apkParser;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);
		mToolbar = findViewById(R.id.toolbar);
		tabLayout = findViewById(R.id.tabLayout);
		viewPager = findViewById(R.id.viewPager);
		// imageIconApp = findViewById(R.id.image_app_icon);
		
		AppModel appModel = new AppModel(this);
		packageName = getIntent().getStringExtra("packageName");

		try {
			ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, 0);
			apkParser = ApkParser.create(appInfo);
		} catch (Exception e) {}
		
		final Drawable icon = appModel.getAppIcon(packageName);

		/*imageIconApp.post(new Runnable() {
			@Override
			public void run() {
				imageIconApp.setImageDrawable(icon);
			}
		});*/
		
		setSupportActionBar(mToolbar);
		setTitle(appModel.getAppName(packageName));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setDisplayUseLogoEnabled(true);
		
		int marginPx = getResources().getDimensionPixelSize(R.dimen.page_margin);
		viewPager.setPageMargin(marginPx);
		viewPager.setPageMarginDrawable(R.drawable.img_divider_vert);
		
		// getSupportActionBar().setIcon(icon);
		getSupportActionBar().setLogo(icon);
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onBackPressed();
				}
			});
		
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		tabLayout.addTab(tabLayout.newTab().setText("Information")); 
		tabLayout.addTab(tabLayout.newTab().setText("Details"));
		//tabLayout.addTab(tabLayout.newTab().setText("Permissions (" + PermissionFragment.getGrantedPermissions(getApplicationContext(), packageName).size() + ")"));
		//tabLayout.addTab(tabLayout.newTab().setText("Classes (" + ClassesFragment.getClassesOfPackage(getApplicationContext(), packageName).size() + ")"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL); 
		tabLayout.setTabMode(TabLayout.MODE_FIXED);

		final DetailsAdapter adapter = new DetailsAdapter(getSupportFragmentManager(), tabLayout.getTabCount()); 
		viewPager.setAdapter(adapter); 
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout)); 
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { 
				@Override 
				public void onTabSelected(TabLayout.Tab tab) { 
					viewPager.setCurrentItem(tab.getPosition()); 
				} 
				@Override 
				public void onTabUnselected(TabLayout.Tab tab) { } 
				@Override 
				public void onTabReselected(TabLayout.Tab tab) { } 
			});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details, menu);
        return true;
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		/*if(item.getItemId() == R.id.action_developer_tools) { //Инструменты разработчика
            
        }*/ if(item.getItemId() == R.id.action_details) { //Подробно
            DialogApp.show(DetailsActivity.this, packageName, 1);
        } 
        return true;
    }
}
