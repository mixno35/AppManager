package com.xianitt.appmanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xianitt.appmanager.R;
import com.xianitt.appmanager.model.AppDialogListModel;

import java.util.ArrayList;

public class AppDialogListAdapter extends RecyclerView.Adapter<AppDialogListAdapter.ViewHolder> {


    Context context1;
    ArrayList<AppDialogListModel> stringList;
    AppDialogListModel model;

    public AppDialogListAdapter(Context context, ArrayList<AppDialogListModel> list) {
        this.context1 = context;
        this.stringList = list;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView icon;
        public TextView text;

        public ViewHolder(View view) {
            super(view);

            icon = (ImageView) view.findViewById(R.id.icon_id);
            text = (TextView) view.findViewById(R.id.text_id);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view2 = LayoutInflater.from(context1).inflate(R.layout.row_app_dialog, parent, false);
        ViewHolder viewHolder = new ViewHolder(view2);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        model = stringList.get(position);

        viewHolder.text.setText(model.getText());
        viewHolder.icon.setImageDrawable(model.getIcon());
        viewHolder.itemView.setOnClickListener(model.getResult());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
