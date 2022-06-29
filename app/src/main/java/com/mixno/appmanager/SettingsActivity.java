package com.mixno.appmanager;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mixno.appmanager.data.Data;
import com.mixno.appmanager.fragment.SettingsFragment;

public class SettingsActivity extends AppCompatActivity {
	
	private Toolbar mToolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setDisplayShowHomeEnabled(true);
		getSupportActionBar().setTitle("Settings");
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction().add(R.id.contentSettings, new SettingsFragment()).commit();
		}
	}
}
