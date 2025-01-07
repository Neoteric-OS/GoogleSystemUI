package com.android.systemui.surfaceeffects.loadingeffect;

import android.content.Context;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public class LoadingEffectView extends View {
    public BlendMode blendMode;
    public Paint paint;

    public LoadingEffectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.blendMode = BlendMode.SRC_OVER;
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        Paint paint;
        if (canvas.isHardwareAccelerated() && (paint = this.paint) != null) {
            canvas.drawPaint(paint);
        }
    }
}
