package thebrazilians.geoalarm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import thebrazilians.geoalarm.R;
import thebrazilians.geoalarm.models.Activity;

/**
 * Created by Jean on 4/2/16.
 */
public class AdapterListViewLocation extends BaseAdapter {

    private List<Activity> list;
    private LayoutInflater inflater;
    public AdapterListViewLocation(Context context, List<Activity> list){
        this.list = list;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return (Activity) list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            convertView = inflater.inflate(R.layout.item_activity, null);
            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.tvNameActivity= (TextView) convertView.findViewById(R.id.tvNameActivity);

        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvNameActivity.setText(list.get(position).getName());



        return convertView;
    }

    private class ViewHolder{
        public TextView tvNameActivity;
    }
}
