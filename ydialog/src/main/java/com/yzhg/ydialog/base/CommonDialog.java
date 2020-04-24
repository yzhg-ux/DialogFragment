package com.yzhg.ydialog.base;


import androidx.annotation.LayoutRes;

/*
 * 操作人 :  Administrator
 * 时  间 :  2019/9/21 0021
 * 描  述 :
 */public class CommonDialog extends BaseDialog {

    private ViewConvertListener convertListener;

    public static CommonDialog newInstance() {
        return new CommonDialog();
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置Dialog布局
     */
    public CommonDialog setLayoutId(@LayoutRes int layoutId) {
        this.mLayoutResId = layoutId;
        return this;
    }

    @Override
    public int setUpLayoutId() {
        return mLayoutResId;
    }

    @Override
    public void convertView(ViewHolder holder, BaseDialog dialog) {
        if (convertListener != null) {
            convertListener.convertView(holder, dialog);
        }
    }

    public CommonDialog setConvertListener(ViewConvertListener convertListener) {
        this.convertListener = convertListener;
        return this;
    }
}