package com.mixno.appmanager;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.mixno.appmanager.data.Data;

public class ResourcesActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private String packageName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Data.setTheme(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Resources");

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        packageName = getIntent().getStringExtra("packageName");
    }
}
