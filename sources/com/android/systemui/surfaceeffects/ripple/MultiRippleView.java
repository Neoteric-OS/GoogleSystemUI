package com.android.systemui.surfaceeffects.ripple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import java.util.ArrayList;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class MultiRippleView extends View {
    public final Paint ripplePaint;
    public final ArrayList ripples;

    public MultiRippleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.ripples = new ArrayList();
        this.ripplePaint = new Paint();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        boolean z;
        if (canvas.isHardwareAccelerated()) {
            loop0: while (true) {
                for (RippleAnimation rippleAnimation : this.ripples) {
                    this.ripplePaint.setShader(rippleAnimation.rippleShader);
                    canvas.drawPaint(this.ripplePaint);
                    z = z || rippleAnimation.animator.isRunning();
                }
            }
            if (z) {
                invalidate();
            }
        }
    }

    public static /* synthetic */ void getRipples$annotations() {
    }
}
