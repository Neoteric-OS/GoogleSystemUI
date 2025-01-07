package androidx.slice.widget;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.slice.view.R$styleable;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RowStyle {
    public final int mActionDividerHeight;
    public final int mBottomDividerEndPadding;
    public final int mBottomDividerStartPadding;
    public final int mContentEndPadding;
    public final int mContentStartPadding;
    public final boolean mDisableRecyclerViewItemAnimator;
    public final int mEndItemEndPadding;
    public final int mEndItemStartPadding;
    public final int mIconSize;
    public final int mImageSize;
    public final int mProgressBarEndPadding;
    public final int mProgressBarInlineWidth;
    public final int mProgressBarStartPadding;
    public final int mSeekBarInlineWidth;
    public final SliceStyle mSliceStyle;
    public final int mSubContentEndPadding;
    public final int mSubContentStartPadding;
    public final Integer mSubtitleColor;
    public final int mTextActionPadding;
    public final Integer mTintColor;
    public final Integer mTitleColor;
    public final int mTitleEndPadding;
    public final int mTitleItemEndPadding;
    public final int mTitleItemStartPadding;
    public final int mTitleStartPadding;

    public RowStyle(Context context, SliceStyle sliceStyle) {
        this.mTitleItemStartPadding = -1;
        this.mTitleItemEndPadding = -1;
        this.mContentStartPadding = -1;
        this.mContentEndPadding = -1;
        this.mTitleStartPadding = -1;
        this.mTitleEndPadding = -1;
        this.mSubContentStartPadding = -1;
        this.mSubContentEndPadding = -1;
        this.mEndItemStartPadding = -1;
        this.mEndItemEndPadding = -1;
        this.mBottomDividerStartPadding = -1;
        this.mBottomDividerEndPadding = -1;
        this.mActionDividerHeight = -1;
        this.mSeekBarInlineWidth = -1;
        this.mProgressBarInlineWidth = -1;
        this.mProgressBarStartPadding = -1;
        this.mProgressBarEndPadding = -1;
        this.mTextActionPadding = -1;
        this.mIconSize = -1;
        this.mDisableRecyclerViewItemAnimator = false;
        this.mSliceStyle = sliceStyle;
        this.mImageSize = context.getResources().getDimensionPixelSize(R.dimen.abc_slice_small_image_size);
    }

    public final int getSubtitleColor() {
        Integer num = this.mSubtitleColor;
        return num != null ? num.intValue() : this.mSliceStyle.mSubtitleColor;
    }

    public RowStyle(Context context, int i, SliceStyle sliceStyle) {
        this.mTitleItemStartPadding = -1;
        this.mTitleItemEndPadding = -1;
        this.mContentStartPadding = -1;
        this.mContentEndPadding = -1;
        this.mTitleStartPadding = -1;
        this.mTitleEndPadding = -1;
        this.mSubContentStartPadding = -1;
        this.mSubContentEndPadding = -1;
        this.mEndItemStartPadding = -1;
        this.mEndItemEndPadding = -1;
        this.mBottomDividerStartPadding = -1;
        this.mBottomDividerEndPadding = -1;
        this.mActionDividerHeight = -1;
        this.mSeekBarInlineWidth = -1;
        this.mProgressBarInlineWidth = -1;
        this.mProgressBarStartPadding = -1;
        this.mProgressBarEndPadding = -1;
        this.mTextActionPadding = -1;
        this.mIconSize = -1;
        this.mDisableRecyclerViewItemAnimator = false;
        this.mSliceStyle = sliceStyle;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(i, R$styleable.RowStyle);
        try {
            this.mTitleItemStartPadding = (int) obtainStyledAttributes.getDimension(22, -1.0f);
            this.mTitleItemEndPadding = (int) obtainStyledAttributes.getDimension(21, -1.0f);
            this.mContentStartPadding = (int) obtainStyledAttributes.getDimension(4, -1.0f);
            this.mContentEndPadding = (int) obtainStyledAttributes.getDimension(3, -1.0f);
            this.mTitleStartPadding = (int) obtainStyledAttributes.getDimension(23, -1.0f);
            this.mTitleEndPadding = (int) obtainStyledAttributes.getDimension(20, -1.0f);
            this.mSubContentStartPadding = (int) obtainStyledAttributes.getDimension(15, -1.0f);
            this.mSubContentEndPadding = (int) obtainStyledAttributes.getDimension(14, -1.0f);
            this.mEndItemStartPadding = (int) obtainStyledAttributes.getDimension(7, -1.0f);
            this.mEndItemEndPadding = (int) obtainStyledAttributes.getDimension(6, -1.0f);
            this.mBottomDividerStartPadding = (int) obtainStyledAttributes.getDimension(2, -1.0f);
            this.mBottomDividerEndPadding = (int) obtainStyledAttributes.getDimension(1, -1.0f);
            this.mActionDividerHeight = (int) obtainStyledAttributes.getDimension(0, -1.0f);
            this.mSeekBarInlineWidth = (int) obtainStyledAttributes.getDimension(13, -1.0f);
            this.mProgressBarInlineWidth = (int) obtainStyledAttributes.getDimension(11, -1.0f);
            this.mProgressBarStartPadding = (int) obtainStyledAttributes.getDimension(12, -1.0f);
            this.mProgressBarEndPadding = (int) obtainStyledAttributes.getDimension(10, -1.0f);
            this.mTextActionPadding = (int) obtainStyledAttributes.getDimension(17, 10.0f);
            this.mIconSize = (int) obtainStyledAttributes.getDimension(8, -1.0f);
            this.mDisableRecyclerViewItemAnimator = obtainStyledAttributes.getBoolean(5, false);
            this.mImageSize = (int) obtainStyledAttributes.getDimension(9, context.getResources().getDimensionPixelSize(R.dimen.abc_slice_small_image_size));
            this.mTintColor = obtainStyledAttributes.hasValue(18) ? Integer.valueOf(obtainStyledAttributes.getColor(18, 0)) : null;
            this.mTitleColor = obtainStyledAttributes.hasValue(19) ? Integer.valueOf(obtainStyledAttributes.getColor(19, 0)) : null;
            this.mSubtitleColor = obtainStyledAttributes.hasValue(16) ? Integer.valueOf(obtainStyledAttributes.getColor(16, 0)) : null;
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
