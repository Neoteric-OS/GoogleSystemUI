package com.android.wm.shell.common.split;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import com.android.wm.shell.R;
import com.android.wm.shell.shared.animation.Interpolators;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class DividerHandleView extends View {
    public static final AnonymousClass1 HEIGHT_PROPERTY;
    public static final AnonymousClass1 WIDTH_PROPERTY;
    public AnimatorSet mAnimator;
    public int mCurrentHeight;
    public int mCurrentWidth;
    public int mHeight;
    public boolean mHovering;
    public int mHoveringHeight;
    public int mHoveringWidth;
    public boolean mIsLeftRightSplit;
    public final Paint mPaint;
    public boolean mTouching;
    public int mTouchingHeight;
    public int mTouchingWidth;
    public int mWidth;

    /* JADX WARN: Type inference failed for: r0v0, types: [com.android.wm.shell.common.split.DividerHandleView$1] */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.android.wm.shell.common.split.DividerHandleView$1] */
    static {
        Class<Integer> cls = Integer.class;
        final int i = 0;
        WIDTH_PROPERTY = new Property(cls, "width") { // from class: com.android.wm.shell.common.split.DividerHandleView.1
            @Override // android.util.Property
            public final Object get(Object obj) {
                switch (i) {
                    case 0:
                        return Integer.valueOf(((DividerHandleView) obj).mCurrentWidth);
                    default:
                        return Integer.valueOf(((DividerHandleView) obj).mCurrentHeight);
                }
            }

            @Override // android.util.Property
            public final void set(Object obj, Object obj2) {
                switch (i) {
                    case 0:
                        DividerHandleView dividerHandleView = (DividerHandleView) obj;
                        dividerHandleView.mCurrentWidth = ((Integer) obj2).intValue();
                        dividerHandleView.invalidate();
                        break;
                    default:
                        DividerHandleView dividerHandleView2 = (DividerHandleView) obj;
                        dividerHandleView2.mCurrentHeight = ((Integer) obj2).intValue();
                        dividerHandleView2.invalidate();
                        break;
                }
            }
        };
        final int i2 = 1;
        HEIGHT_PROPERTY = new Property(cls, "height") { // from class: com.android.wm.shell.common.split.DividerHandleView.1
            @Override // android.util.Property
            public final Object get(Object obj) {
                switch (i2) {
                    case 0:
                        return Integer.valueOf(((DividerHandleView) obj).mCurrentWidth);
                    default:
                        return Integer.valueOf(((DividerHandleView) obj).mCurrentHeight);
                }
            }

            @Override // android.util.Property
            public final void set(Object obj, Object obj2) {
                switch (i2) {
                    case 0:
                        DividerHandleView dividerHandleView = (DividerHandleView) obj;
                        dividerHandleView.mCurrentWidth = ((Integer) obj2).intValue();
                        dividerHandleView.invalidate();
                        break;
                    default:
                        DividerHandleView dividerHandleView2 = (DividerHandleView) obj;
                        dividerHandleView2.mCurrentHeight = ((Integer) obj2).intValue();
                        dividerHandleView2.invalidate();
                        break;
                }
            }
        };
    }

    public DividerHandleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setColor(getResources().getColor(R.color.docked_divider_handle, null));
        paint.setAntiAlias(true);
        updateDimens();
    }

    @Override // android.view.View
    public final boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        int width = (getWidth() / 2) - (this.mCurrentWidth / 2);
        int height = getHeight() / 2;
        int i = this.mCurrentHeight;
        float min = Math.min(this.mCurrentWidth, i) / 2;
        canvas.drawRoundRect(width, height - (i / 2), width + this.mCurrentWidth, r1 + this.mCurrentHeight, min, min, this.mPaint);
    }

    public final void setInputState(int i, int i2, boolean z) {
        AnimatorSet animatorSet = this.mAnimator;
        if (animatorSet != null) {
            animatorSet.cancel();
            this.mAnimator = null;
        }
        if (!z) {
            i = this.mWidth;
        }
        if (!z) {
            i2 = this.mHeight;
        }
        ObjectAnimator ofInt = ObjectAnimator.ofInt(this, WIDTH_PROPERTY, this.mCurrentWidth, i);
        ObjectAnimator ofInt2 = ObjectAnimator.ofInt(this, HEIGHT_PROPERTY, this.mCurrentHeight, i2);
        AnimatorSet animatorSet2 = new AnimatorSet();
        this.mAnimator = animatorSet2;
        animatorSet2.playTogether(ofInt, ofInt2);
        this.mAnimator.setDuration(z ? 150L : 200L);
        this.mAnimator.setInterpolator(z ? Interpolators.TOUCH_RESPONSE : Interpolators.FAST_OUT_SLOW_IN);
        this.mAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.android.wm.shell.common.split.DividerHandleView.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DividerHandleView.this.mAnimator = null;
            }
        });
        this.mAnimator.start();
    }

    public final void updateDimens() {
        Resources resources = getResources();
        boolean z = this.mIsLeftRightSplit;
        int i = R.dimen.split_divider_handle_width;
        this.mWidth = resources.getDimensionPixelSize(z ? R.dimen.split_divider_handle_height : R.dimen.split_divider_handle_width);
        Resources resources2 = getResources();
        if (!this.mIsLeftRightSplit) {
            i = R.dimen.split_divider_handle_height;
        }
        int dimensionPixelSize = resources2.getDimensionPixelSize(i);
        this.mHeight = dimensionPixelSize;
        int i2 = this.mWidth;
        this.mCurrentWidth = i2;
        this.mCurrentHeight = dimensionPixelSize;
        this.mTouchingWidth = i2 > dimensionPixelSize ? i2 / 2 : i2;
        this.mTouchingHeight = dimensionPixelSize > i2 ? dimensionPixelSize / 2 : dimensionPixelSize;
        this.mHoveringWidth = i2 > dimensionPixelSize ? (int) (i2 * 1.5f) : i2;
        if (dimensionPixelSize > i2) {
            dimensionPixelSize = (int) (dimensionPixelSize * 1.5f);
        }
        this.mHoveringHeight = dimensionPixelSize;
    }
}
