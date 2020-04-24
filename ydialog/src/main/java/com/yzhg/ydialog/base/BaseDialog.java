package com.yzhg.ydialog.base;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.FloatRange;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.yzhg.ydialog.R;


/*
 * 操作人 :  Administrator
 * 时  间 :  2019/9/21 0021
 * 描  述 :
 */public abstract class BaseDialog extends DialogFragment {


    @LayoutRes
    protected int mLayoutResId;   //设置自定义布局

    //设置背景透明度
    private float mLucencyFloat = 0f;
    //是否是在底部显示
    private boolean mShowBottomEnable = false;
    //左右边距
    private int mMargin = 0;
    //动画
    private int mAnimStyle = 0;
    //点击外部取消
    private boolean mOutCancel = true;
    //获取上下文
    protected Context mContext;
    //设置宽度
    private int mWidth;
    //设置高度
    private int mHeight;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }


    /**
     * <style name="BaseDialog" parent="@android:style/Theme.Dialog">
     * <item name="android:windowNoTitle">true</item>
     * <item name="android:windowBackground">@android:color/transparent</item>
     * <item name="android:windowFrame">@null</item>
     * <item name="android:windowIsFloating">true</item>
     * <item name="android:windowIsTranslucent">true</item>
     * </style>
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BaseDialog);
        mLayoutResId = setUpLayoutId();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(mLayoutResId, container, false);
        convertView(ViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams params = window.getAttributes();
            params.dimAmount = mLucencyFloat;

            //设置dialog显示位置
            if (mShowBottomEnable) {
                params.gravity = Gravity.BOTTOM;
            } else {
                params.gravity = Gravity.CENTER;
            }

            //设置dialog高度
            if (mWidth == 0) {
                params.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.width = dp2px(mContext, mWidth);
            }

            //设置dialog高度
            if (mHeight == 0) {
                params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                params.height = dp2px(mContext, mHeight);
            }
            //设置dialog动画
            if (mAnimStyle != 0) {
                window.setWindowAnimations(mAnimStyle);
            } else {
                if (mShowBottomEnable) {
                    /*
                     <style name="DialogBottomAnimation" parent="android:Animation">
                     <item name="android:windowEnterAnimation">@anim/dialog_bottom_in</item>
                     <item name="android:windowExitAnimation">@anim/dialog_bottom_out</item>
                     </style>
                     */
                    window.setWindowAnimations(R.style.DialogBottomAnimation);
                } else {

                    /*
                    <style name="DialogCenterAnimation" parent="android:Animation">
                    <item name="android:windowEnterAnimation">@anim/dialog_in</item>
                    <item name="android:windowExitAnimation">@anim/dialog_out</item>
                    </style>
                     */
                    window.setWindowAnimations(R.style.DialogCenterAnimation);
                }
            }
            window.setAttributes(params);
        }
        setCancelable(mOutCancel);
    }


    /**
     * 操作人 : yzhg
     * 描  述 : 设置背景昏暗度
     */
    public BaseDialog setDimAmout(@FloatRange(from = 0, to = 1) float mLucencyFloat) {
        this.mLucencyFloat = mLucencyFloat;
        return this;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 是否显示底部
     */
    public BaseDialog setShowBottom(boolean showBottom) {
        mShowBottomEnable = showBottom;
        return this;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置宽高
     */
    public BaseDialog setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
        return this;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置左右margin
     */
    public BaseDialog setMargin(int margin) {
        mMargin = margin;
        return this;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置进入退出动画
     */
    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        mAnimStyle = animStyle;
        return this;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置是否点击外部取消
     */
    public BaseDialog setOutCancel(boolean outCancel) {
        mOutCancel = outCancel;
        return this;
    }

    public void showDialog(FragmentManager supportFragmentManager) {
        supportFragmentManager.beginTransaction().add(this, getClassName()).commitAllowingStateLoss();
    }

    public void dismissDialog(){
        dismissAllowingStateLoss();
    }

    private static String getClassName() {
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String result = thisMethodStack.getClassName();
        int lastIndex = result.lastIndexOf(".");
        result = result.substring(lastIndex + 1, result.length());
        return result;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 设置布局
     */
    protected abstract int setUpLayoutId();

    /**
     * 操作人 : yzhg
     * 描  述 : 操作dialog
     */
    protected abstract void convertView(ViewHolder viewHolder, BaseDialog baseDialog);


    /**
     * 操作人 : yzhg
     * 描  述 : 获取屏幕宽度
     */
    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    /**
     * 操作人 : yzhg
     * 描  述 : 单位转换
     */
    private static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
