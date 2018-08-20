package com.little.game.adpter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.little.game.R;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private List<Boolean> button;
    private Context context;
    private int defautColumn = 3;
    private int color;
    private boolean flag;

    public MyAdapter(List<Boolean> button, Context context, int color) {
        this.button = button;
        this.context = context;
        this.color = color;

    }


    public void setColor(int color) {
        this.color = color;
    }
    public void setColumn(int column) {
        this.defautColumn = column;
    }

    public void itemClick(int i, int defaultRow) {
        defautColumn = defaultRow;
        int row = i / defaultRow;
        int column = i % defaultRow;
        int top = -1, right = -1, bottom = -1, left = -1;
        int x, y;
        if (row - 1 >= 0) {
            top = (row - 1) * defaultRow + column;
            button.set(top, !button.get(top));
        }
        if (column + 1 < defaultRow) {
            right = i + 1;
            button.set(right, !button.get(right));
        }
        if (row + 1 < defaultRow) {
            bottom = (row + 1) * defaultRow + column;
            button.set(bottom, !button.get(bottom));
        }
        if (column - 1 >= 0) {
            left = i - 1;
            button.set(left, !button.get(left));
        }
        button.set(i, !button.get(i));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return button.size();
    }

    @Override
    public Object getItem(int i) {
        return button.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ItemView itemView;
        if (view == null) {
            itemView = new ItemView();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_layout, null);
            view.setTag(itemView);
        } else {
            itemView = (ItemView) view.getTag();
        }
        int width = getRectWidth();
        itemView.itemButton = (TextView) view.findViewById(R.id.item_button);
        itemView.itemButton.setLayoutParams(new LinearLayout.LayoutParams(width, width));
        if (button.get(i)) {
            itemView.itemButton.setBackgroundColor(color);
        } else {
            itemView.itemButton.setBackgroundColor(Color.WHITE);
        }
//        if (check()) {
//            if (complete != null && flag) {
//                complete.onComplete();
//            }
//        }

        return view;
    }

    private OnComplete complete;

    public void setComplete(OnComplete complete) {
        this.complete = complete;
    }

    public interface OnComplete{
        void onComplete();
    }

    private boolean check() {
        for (int i = 0; i < button.size(); i++) {
            if (!button.get(i)) {
                flag = false;
                return flag;
            }
        }
        flag = true;
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


    private int getRectWidth() {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        float density = dm.density;
        return dm.widthPixels  / defautColumn;

    }

    static class ItemView {
        private TextView itemButton;
    }
}
