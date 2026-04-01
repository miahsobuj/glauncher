package com.glauncher.launcher.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.glauncher.launcher.models.AppInfo;

import java.util.List;

/**
 * Adapter for displaying apps in a grid or list
 * Used by the Launcher tool
 */
public class AppAdapter extends BaseAdapter {

    private final Context context;
    private final List<AppInfo> apps;

    public AppAdapter(Context context, List<AppInfo> apps) {
        this.context = context;
        this.apps = apps;
    }

    @Override
    public int getCount() {
        return apps.size();
    }

    @Override
    public Object getItem(int position) {
        return apps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    android.R.layout.simple_list_item_2, parent, false);
            holder = new ViewHolder();
            holder.icon = convertView.findViewById(android.R.id.icon);
            holder.text1 = convertView.findViewById(android.R.id.text1);
            holder.text2 = convertView.findViewById(android.R.id.text2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AppInfo app = apps.get(position);
        holder.text1.setText(app.getName());
        holder.text2.setText(app.getPackageName());
        holder.icon.setImageDrawable(app.getIcon());

        return convertView;
    }

    private static class ViewHolder {
        ImageView icon;
        TextView text1;
        TextView text2;
    }
}
