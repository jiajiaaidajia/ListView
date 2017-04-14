package com.example.guojiajia.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guojiajia on 2017/4/12.
 */

public class ListAdapter extends BaseAdapter{
    private OnButtenClick mLisenter;


    LayoutInflater inflater;
    private ArrayList<String> showText;
    private ArrayList<Boolean> isShow;

    /**
     * 点击接口
     * 以接口来传参，可以避免持有activity的引用，从而优化内存
     * 点击事件可以直接写在activity中，避免adapter过于复杂
     */
    public interface OnButtenClick{
        void OnButtenClick(View view);
    }
    public ListAdapter(final Context context, ArrayList<String> showtext, ArrayList<Boolean> isshow, OnButtenClick listener){
        this.inflater = LayoutInflater.from(context);
        this.showText = showtext;
        this.isShow = isshow;
        this.mLisenter = listener;
    }
    @Override
    public int getCount() {
        return showText.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder  listItem;
        if(convertView==null){
            listItem = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item,null);
            listItem.textView = (TextView) convertView.findViewById(R.id.show_text_view);
            listItem.button = (Button) convertView.findViewById(R.id.show_button) ;
            convertView.setTag(listItem);
        }else {
            listItem = (ViewHolder)convertView.getTag();
        }

            listItem.textView.setText(showText.get(position).toString());
            listItem.button.setVisibility(isShow.get(position) ? View.VISIBLE:View.INVISIBLE);
            listItem.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mLisenter!=null){
                        mLisenter.OnButtenClick(listItem.button);
                    }
                }
            });
        return convertView;
    }


    static class ViewHolder {
        public TextView textView;
        public Button button;
    }
}
