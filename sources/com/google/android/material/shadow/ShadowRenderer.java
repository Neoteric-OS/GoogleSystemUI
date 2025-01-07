package com.google.android.material.shadow;

import android.graphics.Paint;
import android.graphics.Path;
import androidx.core.graphics.ColorUtils;
import com.android.systemui.plugins.DarkIconDispatcher;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ShadowRenderer {
    public static final int[] cornerColors = null;
    public static final float[] cornerPositions = null;
    public static final int[] edgeColors = null;
    public static final float[] edgePositions = null;
    public final Paint cornerShadowPaint;
    public final Paint edgeShadowPaint;
    public final Path scratch;
    public final Paint shadowPaint;
    public final int shadowStartColor;
    public final Paint transparentPaint;

    public ShadowRenderer() {
        new Path();
        Paint paint = new Paint();
        Paint paint2 = new Paint();
        this.shadowStartColor = ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 68);
        ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 20);
        ColorUtils.setAlphaComponent(DarkIconDispatcher.DEFAULT_INVERSE_ICON_TINT, 0);
        paint2.setColor(this.shadowStartColor);
        paint.setColor(0);
        Paint paint3 = new Paint(4);
        paint3.setStyle(Paint.Style.FILL);
        new Paint(paint3);
    }
}
