package com.xianitt.appmanager;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.adapter.AppAdapter;
import com.xianitt.appmanager.data.Data;
import com.xianitt.appmanager.model.AppDataModel;
import com.xianitt.appmanager.model.AppModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

	private Toolbar mToolbar;
	private Spinner spinner;
	private TextView mTextApps;
	private RecyclerView mRecyclerView;
	private LinearLayoutManager mLayoutManager;
	private AppAdapter mAdapter;
	
	private Handler handlerAdapter;

	private ArrayList typeListApps;
	private boolean openSettings = false;
	
	public SearchView searchView;

	private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		mTextApps = findViewById(R.id.textApps);
		spinner = findViewById(R.id.mainSpinner);
		mRecyclerView = findViewById(R.id.recycler_view);
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		setTitle(getString(R.string.app_name));

		progressDialog = new ProgressDialog(this);
		progressDialog.setMessage("Please wait...");
		progressDialog.setCancelable(false);
		
		typeListApps = new ArrayList();
		typeListApps.add("User apps");
		typeListApps.add("System apps");
		typeListApps.add("S & U apps");
		typeListApps.add("All apps");
		ArrayAdapter dataAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, typeListApps);
		dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
		spinner.setAdapter(dataAdapter);
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
				public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
					String selectedItem = parent.getItemAtPosition(position).toString();
					setChooseTypeLoadApps(position);
					
				}
				public void onNothingSelected(AdapterView<?> parent) {

				}           
			});
			
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			
		mLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        mRecyclerView.setLayoutManager(mLayoutManager);
		mRecyclerView.setNestedScrollingEnabled(true);
			
		setChooseTypeLoadApps(0);

    }
	@Override
	protected void onStart() {
		super.onStart();
		if(Data.changeSetting) {
			Data.changeSetting = false;
			this.recreate();
		}
	}
	@Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
		
		MenuItem searchItem = menu.findItem(R.id.action_search);
		
		SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

		searchView = null;
		if (searchItem != null) {
			searchView = (SearchView) searchItem.getActionView();
			
			searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
					@Override
					public boolean onQueryTextSubmit(String query) {
						// Toast like print
						mAdapter.getFilter().filter(query);
						//Toast.makeText(getApplicationContext(), "Text sumbit: " + query, Toast.LENGTH_SHORT).show();
						return false;
					}
					@Override
					public boolean onQueryTextChange(String s) {
						// UserFeedback.show( "SearchOnQueryTextChanged: " + s);
						mAdapter.getFilter().filter(s);
						//Toast.makeText(getApplicationContext(), "Text change: " + s, Toast.LENGTH_SHORT).show();
						return false;
					}
				});
				
			searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
					@Override
					public void onFocusChange(View p1, boolean p2) {
						/*if(p2) {
							menu.findItem(R.id.action_ads).setVisible(false);
							menu.findItem(R.id.action_more).setVisible(false);
						} else {
							menu.findItem(R.id.action_ads).setVisible(true);
							menu.findItem(R.id.action_more).setVisible(true);
						}*/
					}
				});
					
		}
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
		}
		
        return true;
    }
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) { //Настройки
            startActivity(new Intent(this, SettingsActivity.class));
        }
        return true;
    }
	
	private void setListApps(final ArrayList<AppDataModel> list) {
		mTextApps.setText("0");
        mAdapter = new AppAdapter(MainActivity.this, list);
		
		if(searchView != null) {

		}
		mTextApps.setText(mAdapter.getItemCount() + "");
		mRecyclerView.setAdapter(mAdapter);
		Collections.sort(list, new Comparator<AppDataModel>() {
				@Override
				public int compare(AppDataModel lhs, AppDataModel rhs) {
					return lhs.getAppName().compareTo(rhs.getAppName());
				}
			});
		// progressDialog.cancel();
	}
	
	private void setChooseTypeLoadApps(int position) {
    	// progressDialog.show();
		if(position == 0) {
			setListApps(new AppModel(MainActivity.this).getInstalledApp());
		} if(position == 1) {
			setListApps(new AppModel(MainActivity.this).getInstalledSystemApp());
		} if(position == 2) {
			setListApps(new AppModel(MainActivity.this).getInstalledAllApp());
		} if(position == 3) {
			setListApps(new AppModel(MainActivity.this).getInstalledAllHApp());
		}
	}
}
