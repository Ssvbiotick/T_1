package com.example.t_1;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<ListItemClass> {
    private LayoutInflater inflater;
    private List<ListItemClass> listItem = new ArrayList<>();
    private SharedPreferences def_pref;



    public CustomArrayAdapter(@NonNull Context context, int resource, List<ListItemClass> listItem, LayoutInflater inflater) {
        super(context, resource, listItem);
        this.inflater = inflater;
        this.listItem = listItem;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        ListItemClass listItemMain = listItem.get(position);
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_1, null, false);
            viewHolder = new ViewHolder();

            viewHolder.data1 = convertView.findViewById(R.id.tvData1);
            viewHolder.data2 = convertView.findViewById(R.id.text_Tittle);
//            viewHolder.data3 = convertView.findViewById(R.id.tvData3);
//            viewHolder.data4 = convertView.findViewById(R.id.tvData4);
            convertView.setTag(viewHolder);



        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.data1.setText(listItemMain.getData_1());
        viewHolder.data2.setText(listItemMain.getData_2());


        def_pref = PreferenceManager.getDefaultSharedPreferences(convertView.getContext());
        String text = def_pref.getString("main_text_size","Средний");
        if (text != null)
        {
            switch(text)
            {
                case "Большой":
                    viewHolder.data1.setTextSize(20);
                    break;
                case "Средний":
                    viewHolder.data1.setTextSize(14);
                    break;
                case "Маленький":
                    viewHolder.data1.setTextSize(10);
                    break;
            }
        }

        //        viewHolder.data3.setText(listItemMain.getData_3());
//        viewHolder.data4.setText(listItemMain.getData_4());








        return convertView;
    }
    private class ViewHolder{
        TextView data1;
        TextView data2;
//        TextView data3;
//        TextView data4;


    }
}
