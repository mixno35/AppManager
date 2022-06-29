package com.mixno.appmanager.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mixno.appmanager.R;
import com.mixno.appmanager.model.details.DevModel;
import java.util.ArrayList;

public class DevAdapter extends RecyclerView.Adapter<DevAdapter.DevHolder> {
    // List to store all the contact details
    private ArrayList<DevModel> list;
    private Context mContext;
    // Counstructor for the Class
    public DevAdapter(Context context, ArrayList<DevModel> list) {
        this.list = list;
        this.mContext = context;
    }
    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public DevHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.row_dev, parent, false);
        return new DevHolder(view);
    }
    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull DevHolder holder, final int position) {
        final DevModel model = list.get(position);

        // Set the data to the views here
        holder.txtText.setText(model.getName());
        holder.imgIcon.setImageResource(model.getIcon());
		// You can set click listners to indvidual items in the viewholder here
		// make sure you pass down the listner or make the Data members of the viewHolder public

		holder.item.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					mContext.startActivity(model.getIntengClass());
				}
			});

    }
    // This is your ViewHolder class that helps to populate data to the view
    public class DevHolder extends RecyclerView.ViewHolder {
        private TextView txtText;
        private ImageView imgIcon;
		private LinearLayout item;

        public DevHolder(View itemView) {
            super(itemView);
            txtText = itemView.findViewById(R.id.text_id);
            imgIcon = itemView.findViewById(R.id.icon_id);
			item = itemView.findViewById(R.id.item);
        }
    }
}
