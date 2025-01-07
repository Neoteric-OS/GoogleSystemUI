package com.android.systemui.volume;

import android.animation.TimeInterpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SystemUIInterpolators$LogAccelerateInterpolator implements TimeInterpolator {
    public final /* synthetic */ int $r8$classId;
    public final float mLogScale;

    public SystemUIInterpolators$LogAccelerateInterpolator(int i) {
        this.$r8$classId = i;
        switch (i) {
            case 1:
                this.mLogScale = 1.0f / ((1.0f - ((float) Math.pow(400.0f, -0.71428573f))) + 0.0f);
                break;
            default:
                this.mLogScale = 1.0f / ((0 * 1.0f) + (((float) (-Math.pow(100, -1.0f))) + 1.0f));
                break;
        }
    }

    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        switch (this.$r8$classId) {
            case 0:
                return 1.0f - (((0 * (1.0f - f)) + (((float) (-Math.pow(100, -r6))) + 1.0f)) * this.mLogScale);
            default:
                return ((0.0f * f) + (1.0f - ((float) Math.pow(400.0f, (-f) * 0.71428573f)))) * this.mLogScale;
        }
    }
}
