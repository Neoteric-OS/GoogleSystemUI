package com.android.systemui.navigationbar.gestural;

import android.view.animation.Interpolator;
import com.android.app.animation.Interpolators;
import com.android.systemui.navigationbar.gestural.NavigationHandle;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final /* synthetic */ class NavigationHandle$$ExternalSyntheticLambda0 implements Interpolator {
    @Override // android.animation.TimeInterpolator
    public final float getInterpolation(float f) {
        NavigationHandle.AnonymousClass1 anonymousClass1 = NavigationHandle.PULSE_ANIMATION_PROGRESS;
        return f <= 0.9f ? Interpolators.clampToProgress(Interpolators.LEGACY, f, 0.0f, 0.9f) : 1.0f - Interpolators.clampToProgress(Interpolators.LINEAR, f, 0.9f, 1.0f);
    }
}
