package com.shubham.ordel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.asksira.loopingviewpager.LoopingPagerAdapter;

import java.util.ArrayList;

public class DemoInfiniteAdapter extends LoopingPagerAdapter<Integer> {

    private static final int VIEW_TYPE_NORMAL = 100;
    private static final int VIEW_TYPE_SPECIAL = 101;

    public DemoInfiniteAdapter(Context context, ArrayList<Integer> itemList, boolean isInfinite) {
        super(context, itemList, isInfinite);
    }

    @Override
    protected int getItemViewType(int listPosition) {
        if (itemList.get(listPosition) == 0) return VIEW_TYPE_SPECIAL;
        return VIEW_TYPE_NORMAL;
    }

    @Override
    protected View inflateView(int viewType, ViewGroup container, int listPosition) {
        return LayoutInflater.from(context).inflate(R.layout.item_pager, container, false);
    }

    @Override
    protected void bindView(View convertView, int listPosition, int viewType) {
        //Toast.makeText(context, "PAGE "+listPosition, Toast.LENGTH_SHORT).show();
        ImageView img=convertView.findViewById(R.id.image);
        img.setImageResource(getBackgroundColor(listPosition));
        TextView description = convertView.findViewById(R.id.description);
        description.setText(String.valueOf(itemList.get(listPosition)));
    }


    private int getBackgroundColor (int number) {

        switch (number) {
            case 0:
                return R.drawable.maggi;
            case 1:
                return R.drawable.samosa;
            case 2:
                return R.drawable.dabeli;
            case 3:
                return R.drawable.fried;
            case 4:
                return R.drawable.chicken;
            case 5:
                return R.drawable.babycorn;
            case 6:
                return R.drawable.cake;
                default:
                return R.drawable.cake;
        }
    }
}
