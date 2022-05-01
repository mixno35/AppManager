package com.xianitt.appmanager.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.dialog.DialogApp;
import com.xianitt.appmanager.model.AppDataModel;
import com.xianitt.appmanager.model.AppModel;
import java.util.ArrayList;

public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> implements Filterable {


    Context context1;
    ArrayList<AppDataModel> stringList;
	ArrayList<AppDataModel> stringListFiltered;
	AppDataModel model;

    public AppAdapter(Context context, ArrayList<AppDataModel> list) {
        this.context1 = context;
        this.stringList = list;
		this.stringListFiltered = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageIcon, imageRunning;
        public TextView textName;
        public TextView textPackage;
		public TextView textVersion;

        public ViewHolder(View view) {
            super(view);

            imageIcon = (ImageView) view.findViewById(R.id.icon_id);
            imageRunning = (ImageView) view.findViewById(R.id.icon_running);
            textName = (TextView) view.findViewById(R.id.name_id);
            textPackage = (TextView) view.findViewById(R.id.package_id);
			textVersion = (TextView) view.findViewById(R.id.version_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(context1).inflate(R.layout.row_app, parent, false);
        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {

        AppModel appModel = new AppModel(context1);

        model = stringList.get(position);

		final String packageName = model.getPackageName();
        String labelName = appModel.getAppName(packageName);
		String versionName = appModel.getVersionName(packageName);
        Drawable drawable = appModel.getAppIcon(packageName);

        viewHolder.textName.setText(labelName);
        viewHolder.textPackage.setText(packageName);
		viewHolder.textVersion.setText(versionName);
        viewHolder.imageIcon.setImageDrawable(drawable);

        ((Activity)context1).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (new AppModel(context1).isAppRunning(packageName)) {
                    viewHolder.imageRunning.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.imageRunning.setVisibility(View.GONE);
                }
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					/**/
					DialogApp.show(context1, packageName, 0);
				}
			});
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


	@Override
	public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    stringList = stringListFiltered;
                } else {
                    ArrayList<AppDataModel> filteredList = new ArrayList<>();
                    for (AppDataModel row : stringListFiltered) {

                        // здесь мы отбираем нужные данные 
                        if (row.getAppName().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    stringList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = stringList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                stringList = (ArrayList<AppDataModel>) filterResults.values;

                // обновляем список отфильтрованных данных
                notifyDataSetChanged();
            }
        };
    }
}
