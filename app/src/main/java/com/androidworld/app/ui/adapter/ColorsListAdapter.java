package com.androidworld.app.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidworld.app.R;
import com.androidworld.app.ui.adapter.base.BaseContentAdapter;

import java.util.List;

/**
 * <h3>颜色列表适配器</h3>
 * @author LQC
 * 当前时间：2016/6/13 20:24
 */
public class ColorsListAdapter extends BaseContentAdapter<Integer> {

    private int checkItem;

    public ColorsListAdapter(Context context, List<Integer> list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.colors_image_layout, null);
            holder = new Holder();
            holder.imageView1 = (ImageView)convertView.findViewById(R.id.img_1);
            holder.imageView2 = (ImageView)convertView.findViewById(R.id.img_2);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.imageView1.setImageResource(mDataList.get(position));
        if (checkItem == position){
            holder.imageView2.setImageResource(R.drawable.ic_done_white);
        }

        return convertView;
    }

    public int getCheckItem() {
        return checkItem;
    }

    public void setCheckItem(int checkItem) {
        this.checkItem = checkItem;
    }

    static class Holder {
        ImageView imageView1;
        ImageView imageView2;
    }
}
