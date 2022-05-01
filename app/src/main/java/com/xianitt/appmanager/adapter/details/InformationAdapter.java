package com.xianitt.appmanager.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.adapter.details.data.InfoData;
import com.xianitt.appmanager.model.details.InformationModel;
import java.util.ArrayList;

public class InformationAdapter extends RecyclerView.Adapter<InformationAdapter.InformationHolder> {
    // List to store all the contact details
    private ArrayList<InformationModel> list;
    private Context mContext;
    // Counstructor for the Class
    public InformationAdapter(Context context, ArrayList<InformationModel> list) {
        this.list = list;
        this.mContext = context;
    }
    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public InformationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.row_information, parent, false);
        return new InformationHolder(view);
    }
    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull InformationHolder holder, final int position) {
        final InformationModel model = list.get(position);

        // Set the data to the views here
        holder.txtName.setText(model.getName());
        holder.txtData.setText(model.getData());
		holder.item.setClickable(model.isClickable());
		// You can set click listners to indvidual items in the viewholder here
		// make sure you pass down the listner or make the Data members of the viewHolder public
		
		holder.item.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					if(model.getName().equals("App name") || model.getName().equals("Package") || model.getName().equals("Developer")) {
						InfoData.setShowData(mContext, model.getName(), model.getData());
					}
				}
			});

    }
    // This is your ViewHolder class that helps to populate data to the view
    public class InformationHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtData;
		private LinearLayout item;

        public InformationHolder(View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.name_id);
            txtData = itemView.findViewById(R.id.text_id);
			item = itemView.findViewById(R.id.item);
        }
    }
}
