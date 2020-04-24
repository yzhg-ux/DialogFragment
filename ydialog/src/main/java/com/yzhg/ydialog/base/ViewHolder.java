package com.yzhg.ydialog.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;


/*
 * 操作人 :  Administrator
 * 时  间 :  2019/9/21 0021
 * 描  述 :
 */public class ViewHolder {

    private SparseArray<View> views;

    private View convertView;

    public ViewHolder(View convertView) {
        this.convertView = convertView;
        views = new SparseArray<>();
    }

    public static ViewHolder create(View view) {
        return new ViewHolder(view);
    }

    public <T extends View> T getView(@IdRes int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }


    /**
     * 操作人 : yzhg
     * 描  述 : 设置文本
     */
    public void setText(int viewId, String text) {
        TextView textView = getView(viewId);
        textView.setText(text);
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置Text字体颜色
     */
    public void setTextColor(int viewId, int colorId) {
        TextView textView = getView(viewId);
        textView.setTextColor(colorId);
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置背景图片
     */
    public void setBackgroundResource(int viewId, int resId) {
        View view = getView(viewId);
        view.setBackgroundResource(resId);
    }
    /**
     * 设置背景颜色
     *
     * @param viewId
     * @param colorId
     */
    public void setBackgroundColor(int viewId, int colorId) {
        View view = getView(viewId);
        view.setBackgroundColor(colorId);
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    public void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }
}














