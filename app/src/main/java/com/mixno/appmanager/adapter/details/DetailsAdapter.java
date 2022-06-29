package com.mixno.appmanager.adapter.details;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.mixno.appmanager.fragment.details.DetailsFragment;
import com.mixno.appmanager.fragment.details.InformationFragment;

public class DetailsAdapter extends FragmentStatePagerAdapter {
	int mNumOfTabs; 

	public DetailsAdapter(FragmentManager fm, int NumOfTabs) {
		super(fm); 
		this.mNumOfTabs = NumOfTabs; 
	} 

	@Override 
	public Fragment getItem(int position) {

		switch (position) { 
			case 0: 
				InformationFragment tab0 = new InformationFragment(); 
				return tab0; 
			case 1: 
				DetailsFragment tab1 = new DetailsFragment(); 
				return tab1; 
			/*case 2: 
				PermissionFragment tab2 = new PermissionFragment();
				return tab2; */
			/*case 3: 
				ClassesFragment tab3 = new ClassesFragment();
				return tab3; */
			default: 
				return null; 
		} 
	} 

	@Override 
	public int getCount() { 
		return mNumOfTabs; 
	} 
} 
