package com.example.jie.popupwindow;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, PopupWindow.OnDismissListener{
    private Button btn_popupwindow;
    private PopupWindow popupWindow;
    private int navigationHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        InitClick();
        InitData();
    }

    private void InitData() {
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        navigationHeight = getResources().getDimensionPixelSize(resourceId);
    }

    private void InitClick() {
        btn_popupwindow.setOnClickListener(this);
    }

    private void Init() {
        btn_popupwindow = (Button) findViewById(R.id.bt_pwindow);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_pwindow:
                openPopupWindow(v);
                break;
            case R.id.tv_pick_phone:
                popupWindow.dismiss();
                break;
            case R.id.tv_pick_zone:
                popupWindow.dismiss();
                break;
            case R.id.tv_try:
                popupWindow.dismiss();
                break;
            case R.id.tv_pick_auto:
                popupWindow.dismiss();
                break;
            case R.id.tv_cancel:
                popupWindow.dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 弹框设置
     *
     * @param v
     */
    private void openPopupWindow(View v) {
        //防止重复按按钮
        if (popupWindow != null && popupWindow.isShowing()) {
            return;
        }
        //设置PopupWindow的View
        View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.view_popupwindow1, null);
        popupWindow = new PopupWindow(view, RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        //设置背景,这个没什么效果，不添加会报错
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        //设置点击弹窗外隐藏自身
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        //设置动画
        popupWindow.setAnimationStyle(R.style.PopupWindow);
        //设置位置
        popupWindow.showAtLocation(v, Gravity.BOTTOM, 0, navigationHeight);
        //设置消失监听
        popupWindow.setOnDismissListener(this);
        //设置PopupWindow的View点击事件
        setOnPopupViewClick(view);
        //设置背景色
        setBackgroundAlpha(0.5f);
    }

    //设置屏幕背景透明效果
    public void setBackgroundAlpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = alpha;
        getWindow().setAttributes(lp);
    }

    /**
     * 弹框点击
     *
     * @param view
     */
    private void setOnPopupViewClick(View view) {
        TextView tv_pick_phone, tv_pick_zone, tv_cancel, tv_pick_auto, tv_try, tv_top, tv_else;
        tv_pick_phone = (TextView) view.findViewById(R.id.tv_pick_phone);
        tv_pick_zone = (TextView) view.findViewById(R.id.tv_pick_zone);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_pick_auto = (TextView) view.findViewById(R.id.tv_pick_auto);
        tv_try = (TextView) view.findViewById(R.id.tv_try);
        tv_pick_phone.setOnClickListener(this);
        tv_pick_zone.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        tv_pick_auto.setOnClickListener(this);
        tv_try.setOnClickListener(this);
    }

    @Override
    public void onDismiss() {
        setBackgroundAlpha(1);
    }
}
