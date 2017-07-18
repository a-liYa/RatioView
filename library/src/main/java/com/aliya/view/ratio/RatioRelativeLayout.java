package com.aliya.view.ratio;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * 保持固定宽高比的RelativeLayout
 * <p>
 * eg：布局添加属性 <code>app:ratio_w2h="1280:720"</code>
 * 表示：宽／高 = 1280／720
 *
 * @author a_liYa
 * @date 2017/7/18 下午11:50.
 */
public class RatioRelativeLayout extends RelativeLayout {

    private RatioHelper helper;

    public RatioRelativeLayout(@NonNull Context context) {
        this(context, null);
    }

    public RatioRelativeLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioRelativeLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = new RatioHelper(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        widthMeasureSpec = helper.widthMeasureSpec(widthMeasureSpec, heightMeasureSpec);
        heightMeasureSpec = helper.heightMeasureSpec(widthMeasureSpec, heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 设置固定宽高比
     *
     * @param w2h 宽高比率
     */
    public void setRatio(float w2h) {
        if (w2h > 0 && helper.getRatio() != w2h) {
            helper.setRatio(w2h);
            requestLayout();
        }
    }

    public float setRatio() {
        return helper.getRatio();
    }

}
