package com.android.systemui.shade.transition;

import android.util.MathUtils;
import com.android.systemui.animation.ShadeInterpolation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LargeScreenPortraitShadeInterpolator implements LargeScreenShadeInterpolator {
    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getBehindScrimAlpha(float f) {
        return MathUtils.constrainedMap(0.0f, 1.0f, 0.0f, 0.3f, f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationContentAlpha(float f) {
        return ShadeInterpolation.getContentAlpha(f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationFooterAlpha(float f) {
        return ShadeInterpolation.getContentAlpha(f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getNotificationScrimAlpha(float f) {
        return MathUtils.constrainedMap(0.0f, 1.0f, 0.3f, 0.75f, f);
    }

    @Override // com.android.systemui.shade.transition.LargeScreenShadeInterpolator
    public final float getQsAlpha(float f) {
        return ShadeInterpolation.getContentAlpha(f);
    }
}
