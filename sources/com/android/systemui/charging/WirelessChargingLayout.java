package com.android.systemui.charging;

import android.widget.FrameLayout;
import com.android.systemui.surfaceeffects.ripple.RippleShader;
import com.android.systemui.surfaceeffects.ripple.RippleView;
import java.util.Arrays;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class WirelessChargingLayout extends FrameLayout {
    public RippleView mRippleView;
    public RippleShader.SizeAtProgress[] mSizeAtProgressArray;

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (this.mRippleView != null) {
            int measuredWidth = getMeasuredWidth();
            int measuredHeight = getMeasuredHeight();
            float f = measuredWidth;
            float f2 = measuredHeight;
            this.mRippleView.setCenter(f * 0.5f, 0.5f * f2);
            RippleShader.RippleShape rippleShape = this.mRippleView.rippleShape;
            if (rippleShape == null) {
                rippleShape = null;
            }
            if (rippleShape == RippleShader.RippleShape.ROUNDED_BOX) {
                RippleShader.SizeAtProgress[] sizeAtProgressArr = this.mSizeAtProgressArray;
                if (sizeAtProgressArr == null) {
                    float max = Math.max(f, f2);
                    this.mSizeAtProgressArray = new RippleShader.SizeAtProgress[]{new RippleShader.SizeAtProgress(0.0f, 0.0f, 0.0f), new RippleShader.SizeAtProgress(0.3f, f * 0.4f, f2 * 0.4f), new RippleShader.SizeAtProgress(1.0f, max, max)};
                } else {
                    RippleShader.SizeAtProgress sizeAtProgress = sizeAtProgressArr[0];
                    sizeAtProgress.t = 0.0f;
                    sizeAtProgress.width = 0.0f;
                    sizeAtProgress.height = 0.0f;
                    RippleShader.SizeAtProgress sizeAtProgress2 = sizeAtProgressArr[1];
                    sizeAtProgress2.t = 0.3f;
                    sizeAtProgress2.width = f * 0.4f;
                    sizeAtProgress2.height = 0.4f * f2;
                    float max2 = Math.max(f, f2);
                    RippleShader.SizeAtProgress sizeAtProgress3 = this.mSizeAtProgressArray[2];
                    sizeAtProgress3.t = 1.0f;
                    sizeAtProgress3.width = max2;
                    sizeAtProgress3.height = max2;
                }
                RippleView rippleView = this.mRippleView;
                RippleShader.SizeAtProgress[] sizeAtProgressArr2 = this.mSizeAtProgressArray;
                RippleShader rippleShader = rippleView.rippleShader;
                (rippleShader != null ? rippleShader : null).rippleSize.setSizeAtProgresses((RippleShader.SizeAtProgress[]) Arrays.copyOf(sizeAtProgressArr2, sizeAtProgressArr2.length));
            } else {
                float max3 = Math.max(measuredWidth, measuredHeight);
                RippleShader rippleShader2 = this.mRippleView.rippleShader;
                RippleShader.RippleSize rippleSize = (rippleShader2 != null ? rippleShader2 : null).rippleSize;
                rippleSize.getClass();
                rippleSize.setSizeAtProgresses(rippleSize.initialSize, new RippleShader.SizeAtProgress(1.0f, max3, max3));
            }
        }
        super.onLayout(z, i, i2, i3, i4);
    }
}
