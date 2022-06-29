package com.mixno.appmanager.adapter.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mixno.appmanager.R;
import com.mixno.appmanager.model.details.ClassesModel;
import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ClassesHolder> {
    // List to store all the contact details
    private ArrayList<ClassesModel> list;
    private Context mContext;
    // Counstructor for the Class
    public ClassesAdapter(Context context, ArrayList<ClassesModel> list) {
        this.list = list;
        this.mContext = context;
    }
    // This method creates views for the RecyclerView by inflating the layout
    // Into the viewHolders which helps to display the items in the RecyclerView
    @Override
    public ClassesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Inflate the layout view you have created for the list rows here
        View view = layoutInflater.inflate(R.layout.row_classes, parent, false);
        return new ClassesHolder(view);
    }
    @Override
    public int getItemCount() {
        return list == null? 0: list.size();
    }
    // This method is called when binding the data to the views being created in RecyclerView
    @Override
    public void onBindViewHolder(@NonNull ClassesHolder holder, final int position) {
        final ClassesModel model = list.get(position);

        // Set the data to the views here
		holder.txtName.setText(model.getClassName());
        holder.txtText.setText(model.getFullClassName());
        // You can set click listners to indvidual items in the viewholder here
		// make sure you pass down the listner or make the Data members of the viewHolder public

		holder.item.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View p1) {
					
				}
			});

    }
    // This is your ViewHolder class that helps to populate data to the view
    public class ClassesHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
		private TextView txtText;
		private LinearLayout item;
        
        public ClassesHolder(View itemView) {
            super(itemView);
			txtName = itemView.findViewById(R.id.name_id);
            txtText = itemView.findViewById(R.id.text_id);
			item = itemView.findViewById(R.id.item);
        }
    }
}
