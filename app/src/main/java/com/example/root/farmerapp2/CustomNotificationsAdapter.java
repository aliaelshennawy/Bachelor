package com.example.root.farmerapp2;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by root on 27/03/16.
 */
public class CustomNotificationsAdapter extends ArrayAdapter {
    private final Activity context;
    private final String[] itemname;
    private final Integer[] imgid;

    public CustomNotificationsAdapter(Activity context, String[] itemname, Integer[] imgid) {
        super(context, R.layout.notify_item, itemname);
        // TODO Auto-generated constructor stub

        this.context=context;
        this.itemname=itemname;
        this.imgid=imgid;
    }

    public View getView(int position,View view,ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.notify_item, null,true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.itemN);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.iconN);
        //TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        imageView.setImageResource(imgid[position]);
        //extratxt.setText("Description "+itemname[position]);
        return rowView;

    };
    //  public List GetCheckedItems () {
//      SparseBooleanArray checked = list.getCheckedItemPositions();
//
//      for (int i = 0; i < list.getAdapter().getCount(); i++) {
//          if (checked.get(i)) {
//              // Do something
//          }
//      }
//  return }
}
