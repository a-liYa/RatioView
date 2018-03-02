package com.aliya.view.ratio;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 保持固定宽高比的LinearLayout
 * <p>
 * eg：布局添加属性 <code>app:ratio_w2h="1280:720"</code>
 * 表示：宽／高 = 1280／720
 * </p>
 *
 * @author a_liYa
 * @date 2017/7/18 下午11:49.
 */
public class RatioLinearLayout extends LinearLayout {

    private RatioHelper helper;

    public RatioLinearLayout(@NonNull Context context) {
        this(context, null);
    }

    public RatioLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioLinearLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        helper = new RatioHelper(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(
                helper.widthMeasureSpec(widthMeasureSpec, heightMeasureSpec, getLayoutParams()),
                helper.heightMeasureSpec(widthMeasureSpec, heightMeasureSpec, getLayoutParams())
        );
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

    public void clearRatio() {
        helper.setRatio(RatioHelper.NO_VALUE);
        requestLayout();
    }

}
