package com.xianitt.appmanager;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.jaredrummler.apkparser.ApkParser;
import com.xianitt.appmanager.R;
import com.xianitt.appmanager.data.Data;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ManifestActivity extends AppCompatActivity {

	public String packageName = "";

	private Toolbar mToolbar;
	private LinearLayout progress;
	private TextView txtText;
	private TextView txtLog;

	private String packageCodePath;
	private String text;
	
	private ZipFile apk;
	private ZipEntry manifest;
	private InputStream stream;

	private String readableAndroidManifest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
		Data.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manifest);
		mToolbar = findViewById(R.id.toolbar);
		progress = findViewById(R.id.progressLayout);
		txtText = findViewById(R.id.text_view);
		txtLog = findViewById(R.id.txtLog);
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

		txtLog.post(new Runnable() {
				@Override
				public void run() {
					txtLog.setText("Processing...");
					
					txtLog.postDelayed(new Runnable() {
							@Override
							public void run() {
								txtLog.setText("Unziping manifest...");
							}
						}, 600);
				}
			});
		
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
				public void run() {
					try {
						ApplicationInfo appInfo = getPackageManager().getApplicationInfo(packageName, 0);
						final ApkParser apkParser = ApkParser.create(appInfo);
						readableAndroidManifest = apkParser.getManifestXml();
						
						txtLog.post(new Runnable() {
								@Override
								public void run() {
									txtLog.setText("AndroidManifest.xml reading...");
								}
							});
						
						Handler handler1 = new Handler();
						handler1.postDelayed(new Runnable() {
								public void run() {
									try {
										txtText.post(new Runnable() {
												@Override
												public void run() {
													txtText.setText(readableAndroidManifest);
													runOnUiThread(new Runnable() {
															@Override
															public void run() {
																progress.setVisibility(View.GONE);
																Toast.makeText(getApplicationContext(), "Success manifest unziping!", Toast.LENGTH_SHORT).show();			
															}
														});
												}
											});
									} catch(final Exception e) {
										txtLog.post(new Runnable() {
												@Override
												public void run() {
													txtLog.setText("Manifest reading failed!\nData: " + e.getMessage());
												}
											});
									}
								}
							}, 700);
					} catch (final Exception e) {
						// TODO Auto-generated catch block
						txtLog.post(new Runnable() {
								@Override
								public void run() {
									txtLog.setText("Manifest unziping failed!\nData: " + e.getMessage());
								}
							});
						e.printStackTrace();
					}
				}
			}, 1500);

		getSupportActionBar().setTitle("Manifest");
    }
}
