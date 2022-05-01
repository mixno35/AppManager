package com.xianitt.appmanager.fragment.details;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xianitt.appmanager.R;
import com.xianitt.appmanager.adapter.details.ClassesAdapter;
import com.xianitt.appmanager.model.details.ClassesModel;
import dalvik.system.DexFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ClassesFragment extends Fragment {

	public String packageName = "";

	private RecyclerView mRecyclerView;
	private ClassesAdapter mAdapter;
	private RecyclerView.LayoutManager mLayoutManager;


    @Override 
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) { 
        View view = inflater.inflate(R.layout.fragment_classes, container, false); 
		mRecyclerView = view.findViewById(R.id.recycler_view);

		//mRecyclerView.setNestedScrollingEnabled(true);

		packageName = getActivity().getIntent().getStringExtra("packageName");

		try {
			mLayoutManager = new LinearLayoutManager(((Activity)getActivity()));
			mRecyclerView.setLayoutManager(mLayoutManager);
			mAdapter = new ClassesAdapter(getActivity(), getClassesOfPackage(getActivity(), packageName));
			mRecyclerView.setAdapter(mAdapter);
		} catch (Exception e){}

		return view; 
	}
	
	public static ArrayList<ClassesModel> getClassesOfPackage(Context context, String packageName) {
		ArrayList<ClassesModel> classes = new ArrayList<ClassesModel>();
		try {
			String packageCodePath = context.getPackageCodePath();
			packageCodePath = packageCodePath.replaceAll("com.doctorsteep.appmanager", packageName);
			DexFile df = new DexFile(packageCodePath);
			Toast.makeText(context, packageCodePath, Toast.LENGTH_SHORT).show();
			for (Enumeration<String> iter = df.entries(); iter.hasMoreElements(); ) {
				String className = iter.nextElement();
				if (className.contains(packageName)) {
					classes.add(new ClassesModel(className.substring(className.lastIndexOf(".") + 1, className.length()).toString(), className, packageName));
					//classes.add(new ClassesModel(className));
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}
}
