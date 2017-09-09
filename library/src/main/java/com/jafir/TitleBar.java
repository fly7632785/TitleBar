package com.jafir;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.IntDef;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jafir on 17/9/9.
 */

public class TitleBar extends ViewGroup {
    private static final int DEFAULT_MAIN_TEXT_SIZE = 17;
    private static final int DEFAULT_MAIN_CENTER_TEXT_SIZE = 20;
    private static final int DEFAULT_TITLE_BAR_HEIGHT = 56;
    private static final int DEFAULT_IMG_SIZE = dip2px(40);

    public final static int TOOLBAR_MODE_NONE  = 0;
    public final static int TOOLBAR_MODE_BACK  = 1;
    public final static int TOOLBAR_MODE_MENU  = 2;
    public final static int TOOLBAR_MODE_CLOSE = 3;

    private TextView mLeftText;
    private TextView mRightText;
    private ImageView mLeftImg;
    private ImageView mRightImg1;
    private ImageView mRightImg2;
    private LinearLayout mLeftLayout;
    private LinearLayout mRightLayout;
    private LinearLayout mCenterLayout;
    private TextView mCenterText;
    private View mCustomCenterView;
    private View mDividerView;

    private int mHeight;
    private int mScreenWidth;
    private int mOutPadding;
    private int mTextColor;
    private int mImgPadding;
    private int mTextPadding;
    private int mDividerViewHeight = dip2px(3);
    private int mLeftTextLeftDrawableSize = dip2px(20);

    @IntDef({TOOLBAR_MODE_NONE, TOOLBAR_MODE_BACK, TOOLBAR_MODE_MENU, TOOLBAR_MODE_CLOSE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToolbarMode{}

    @ToolbarMode
    private int mode = TOOLBAR_MODE_NONE;

    public TitleBar(Context context) {
        super(context);
        init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        if (attrs != null)
            initAttr(context.obtainStyledAttributes(attrs, R.styleable.TitleBar));
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mTextPadding = dip2px(5);
        mImgPadding = dip2px(6);
        mOutPadding = dip2px(10);
        mHeight = dip2px(DEFAULT_TITLE_BAR_HEIGHT);
        mTextColor = Color.WHITE;
        setBackgroundResource(R.color.colorPrimary);
        initView(context);
    }

    private void initAttr(TypedArray typedArray) {
        if (typedArray.hasValue(R.styleable.TitleBar_tb_left_img_type)) {
            mLeftImg.setVisibility(VISIBLE);
            setMode(typedArray.getInt(R.styleable.TitleBar_tb_left_img_type,TOOLBAR_MODE_NONE));
        }

        if (typedArray.hasValue(R.styleable.TitleBar_tb_left_img)) {
            mLeftImg.setVisibility(VISIBLE);
            mLeftImg.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_tb_left_img, R.drawable.ic_launcher));
        }
        if (typedArray.hasValue(R.styleable.TitleBar_tb_left_text)) {
            mLeftText.setVisibility(VISIBLE);
            mLeftText.setText(typedArray.getString(R.styleable.TitleBar_tb_left_text));
        }
        if (typedArray.hasValue(R.styleable.TitleBar_tb_left_text_left_drawable)) {
            Drawable leftDrawable = getResources().getDrawable(typedArray.getResourceId(R.styleable.TitleBar_tb_left_text_left_drawable,R.drawable.ic_launcher));
            leftDrawable.setBounds(0,0,mLeftTextLeftDrawableSize,mLeftTextLeftDrawableSize);
            mLeftText.setCompoundDrawables(leftDrawable,null,null,null);
        }

        if (typedArray.hasValue(R.styleable.TitleBar_tb_center_text)) {
            mCenterText.setVisibility(VISIBLE);
            mCenterText.setText(typedArray.getString(R.styleable.TitleBar_tb_center_text));
        }

        if (typedArray.hasValue(R.styleable.TitleBar_tb_right_img1)) {
            mRightImg1.setVisibility(VISIBLE);
            mRightImg1.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_tb_right_img1, R.drawable.ic_launcher));
        }
        if (typedArray.hasValue(R.styleable.TitleBar_tb_right_img2)) {
            mRightImg2.setVisibility(VISIBLE);
            mRightImg2.setImageResource(typedArray.getResourceId(R.styleable.TitleBar_tb_right_img2, R.drawable.ic_launcher));
        }
        if (typedArray.hasValue(R.styleable.TitleBar_tb_right_text)) {
            mRightText.setVisibility(VISIBLE);
            mRightText.setText(typedArray.getString(R.styleable.TitleBar_tb_right_text));
        }
    }

    public void setMode(int mode) {
        this.mode = mode;
        switch (mode) {
            case TOOLBAR_MODE_NONE:
                mLeftImg.setVisibility(GONE);
                break;
            case TOOLBAR_MODE_BACK:
                mLeftImg.setImageResource(R.drawable.ic_home_as_up_arrow);
                break;
            case TOOLBAR_MODE_MENU:
                mLeftImg.setImageResource(R.drawable.ic_home_up_menu);
                break;
            case TOOLBAR_MODE_CLOSE:
                mLeftImg.setImageResource(R.drawable.ic_home_up_close);
                break;
        }
    }

    private void initView(Context context) {
        LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);

        mLeftLayout = new LinearLayout(context);
        mLeftLayout.setOrientation(LinearLayout.HORIZONTAL);
        mLeftLayout.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
        mLeftLayout.setPadding(mOutPadding, 0, mOutPadding, 0);
        mLeftText = new TextView(context);
        mLeftText.setTextSize(DEFAULT_MAIN_TEXT_SIZE);
        mLeftText.setSingleLine();
        mLeftText.setGravity(Gravity.CENTER_VERTICAL);
        mLeftText.setTextColor(mTextColor);
        mLeftText.setPadding(mTextPadding, 0, mTextPadding, 0);
        mLeftImg = new ImageView(context);
        mLeftImg.setLayoutParams(new LinearLayout.LayoutParams(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE));
        mLeftImg.setPadding(mImgPadding, mImgPadding, mImgPadding, mImgPadding);
        mLeftLayout.addView(mLeftImg);
        mLeftLayout.addView(mLeftText);

        mCenterLayout = new LinearLayout(context);
        mCenterText = new TextView(context);
        mCenterText.setTextColor(mTextColor);
        mCenterLayout.setGravity(Gravity.CENTER);
        mCenterText.setTypeface(Typeface.DEFAULT_BOLD);
        mCenterText.setTextSize(DEFAULT_MAIN_CENTER_TEXT_SIZE);
        mCenterText.setSingleLine();
        mCenterText.setGravity(Gravity.CENTER);
        mCenterText.setEllipsize(TextUtils.TruncateAt.END);
        mCenterLayout.addView(mCenterText);

        mRightLayout = new LinearLayout(context);
        mRightLayout.setOrientation(LinearLayout.HORIZONTAL);
        mRightLayout.setPadding(mOutPadding, 0, mOutPadding, 0);
        mRightLayout.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
        mRightText = new TextView(context);
        mRightText.setTextColor(mTextColor);
        mRightText.setTextSize(DEFAULT_MAIN_TEXT_SIZE);
        mRightText.setSingleLine();
        mRightText.setGravity(Gravity.CENTER_VERTICAL);
        mRightText.setPadding(mTextPadding, 0, mTextPadding, 0);
        mRightImg1 = new ImageView(context);
        mRightImg1.setLayoutParams(new LinearLayout.LayoutParams(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE));
        mRightImg1.setPadding(mImgPadding, mImgPadding, mImgPadding, mImgPadding);
        mRightImg2 = new ImageView(context);
        mRightImg2.setLayoutParams(new LinearLayout.LayoutParams(DEFAULT_IMG_SIZE, DEFAULT_IMG_SIZE));
        mRightImg2.setPadding(mImgPadding, mImgPadding, mImgPadding, mImgPadding);
        mRightLayout.addView(mRightText);
        mRightLayout.addView(mRightImg1);
        mRightLayout.addView(mRightImg2);

        addView(mLeftLayout, layoutParams);
        addView(mCenterLayout);
        addView(mRightLayout, layoutParams);

        mDividerView = new View(context);
        mDividerView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, mDividerViewHeight));
        mDividerView.setBackgroundResource(R.drawable.shape_title);
        addView(mDividerView);
        initData();
        initVisiable();
    }

    private void initVisiable() {
        mLeftImg.setVisibility(GONE);
        mLeftText.setVisibility(GONE);
        mRightImg1.setVisibility(GONE);
        mRightImg2.setVisibility(GONE);
        mRightText.setVisibility(GONE);
    }

    private void initData() {
        mLeftImg.setImageResource(R.drawable.ic_launcher);
        mRightImg1.setImageResource(R.drawable.ic_launcher);
        mRightImg2.setImageResource(R.drawable.ic_launcher);
        mCenterText.setText("title");
        mLeftText.setText("返回");
        mRightText.setText("点赞");
    }

    public void setCustomTitle(View titleView) {
        if (titleView == null) {
            mCenterText.setVisibility(View.VISIBLE);
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }
        } else {
            if (mCustomCenterView != null) {
                mCenterLayout.removeView(mCustomCenterView);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
            mCustomCenterView = titleView;
            mCenterLayout.addView(titleView, layoutParams);
            mCenterText.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int height;
        if (heightMode != MeasureSpec.EXACTLY) {
            height = mHeight;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY);
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec);
        }
        mScreenWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChild(mLeftLayout, widthMeasureSpec, heightMeasureSpec);
        measureChild(mRightLayout, widthMeasureSpec, heightMeasureSpec);
        measureChild(mDividerView, widthMeasureSpec, heightMeasureSpec);
        if (mLeftLayout.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mLeftLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        } else {
            mCenterLayout.measure(
                    MeasureSpec.makeMeasureSpec(mScreenWidth - 2 * mRightLayout.getMeasuredWidth(), MeasureSpec.EXACTLY)
                    , heightMeasureSpec);
        }
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), height);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int dividerHeight = mDividerView.getMeasuredHeight();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            dividerHeight = 0;
        }
        mLeftLayout.layout(0, 0, mLeftLayout.getMeasuredWidth(), mLeftLayout.getMeasuredHeight());
        mRightLayout.layout(mScreenWidth - mRightLayout.getMeasuredWidth(), 0,
                mScreenWidth, mRightLayout.getMeasuredHeight());
        mDividerView.layout(0, mHeight - dividerHeight, mScreenWidth, mHeight);
        if (mLeftLayout.getMeasuredWidth() > mRightLayout.getMeasuredWidth()) {
            mCenterLayout.layout(mLeftLayout.getMeasuredWidth(), 0,
                    mScreenWidth - mLeftLayout.getMeasuredWidth(), getMeasuredHeight() - dividerHeight);
        } else {
            mCenterLayout.layout(mRightLayout.getMeasuredWidth(), 0,
                    mScreenWidth - mRightLayout.getMeasuredWidth(), getMeasuredHeight() - dividerHeight);
        }
    }

    private static int dip2px(int defaultTitleBarHeight) {
        return (int) (Resources.getSystem().getDisplayMetrics().density * defaultTitleBarHeight);
    }

    public TextView getLeftText() {
        return mLeftText;
    }

    public TextView getRightText() {
        return mRightText;
    }

    public ImageView getLeftImg() {
        return mLeftImg;
    }

    public ImageView getRightImg1() {
        return mRightImg1;
    }

    public ImageView getRightImg2() {
        return mRightImg2;
    }

    public LinearLayout getLeftLayout() {
        return mLeftLayout;
    }

    public LinearLayout getRightLayout() {
        return mRightLayout;
    }

    public LinearLayout getCenterLayout() {
        return mCenterLayout;
    }

    public TextView getCenterText() {
        return mCenterText;
    }

    public View getCustomCenterView() {
        return mCustomCenterView;
    }

    public View getDividerView() {
        return mDividerView;
    }

    public void setImgPadding(int mImgPadding) {
        mLeftImg.setPadding(mImgPadding,mImgPadding,mImgPadding,mImgPadding);
        mRightImg1.setPadding(mImgPadding,mImgPadding,mImgPadding,mImgPadding);
        mRightImg2.setPadding(mImgPadding,mImgPadding,mImgPadding,mImgPadding);
    }

    public void setTextPadding(int mTextPadding) {
        mLeftText.setPadding(mTextPadding,mTextPadding,mTextPadding,mTextPadding);
        mCenterText.setPadding(mTextPadding,mTextPadding,mTextPadding,mTextPadding);
        mRightText.setPadding(mTextPadding,mTextPadding,mTextPadding,mTextPadding);
    }

    public void setTextColor(int mTextColor) {
        mLeftText.setTextColor(mTextColor);
        mCenterText.setTextColor(mTextColor);
        mRightText.setTextColor(mTextColor);
    }
}
