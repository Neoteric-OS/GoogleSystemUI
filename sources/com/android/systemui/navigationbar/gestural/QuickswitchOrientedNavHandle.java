package com.android.systemui.navigationbar.gestural;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import com.android.wm.shell.R;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class QuickswitchOrientedNavHandle extends NavigationHandle {
    public int mDeltaRotation;
    public final RectF mTmpBoundsRectF;
    public final int mWidth;

    public QuickswitchOrientedNavHandle(Context context) {
        super(context);
        this.mTmpBoundsRectF = new RectF();
        this.mWidth = context.getResources().getDimensionPixelSize(R.dimen.navigation_home_handle_width);
    }

    public final RectF computeHomeHandleBounds() {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        float f6 = this.mRadius * 2.0f;
        int i = getLocationOnScreen()[1];
        int i2 = this.mDeltaRotation;
        if (i2 == 1) {
            float f7 = this.mBottom;
            f = f7 + f6;
            int i3 = this.mWidth;
            float height = ((getHeight() / 2.0f) - (i3 / 2.0f)) - (i / 2.0f);
            f2 = i3 + height;
            f3 = f7;
            f4 = height;
        } else {
            if (i2 != 3) {
                float f8 = this.mRadius * 2.0f;
                f3 = (getWidth() / 2.0f) - (this.mWidth / 2.0f);
                f4 = (getHeight() - this.mBottom) - f8;
                f = (this.mWidth / 2.0f) + (getWidth() / 2.0f);
                f5 = f8 + f4;
                this.mTmpBoundsRectF.set(f3, f4, f, f5);
                return this.mTmpBoundsRectF;
            }
            f = getWidth() - this.mBottom;
            int i4 = this.mWidth;
            f4 = ((getHeight() / 2.0f) - (i4 / 2.0f)) - (i / 2.0f);
            f2 = i4 + f4;
            f3 = f - f6;
        }
        f5 = f2;
        this.mTmpBoundsRectF.set(f3, f4, f, f5);
        return this.mTmpBoundsRectF;
    }

    @Override // com.android.systemui.navigationbar.gestural.NavigationHandle, android.view.View
    public final void onDraw(Canvas canvas) {
        RectF computeHomeHandleBounds = computeHomeHandleBounds();
        float f = this.mRadius;
        canvas.drawRoundRect(computeHomeHandleBounds, f, f, this.mPaint);
    }
}
