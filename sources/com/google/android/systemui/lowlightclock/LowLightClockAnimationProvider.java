package com.google.android.systemui.lowlightclock;

import com.android.app.animation.Interpolators;
import com.android.dream.lowlight.util.TruncatedInterpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class LowLightClockAnimationProvider {
    public final long mAlphaAnimationDurationMillis;
    public final long mAlphaAnimationInStartDelayMillis;
    public final TruncatedInterpolator mTranslationOutInterpolator;
    public final long mYTranslationAnimationInDurationMillis;
    public final int mYTranslationAnimationInStartOffset;

    public LowLightClockAnimationProvider(int i, long j, long j2, long j3) {
        this.mYTranslationAnimationInStartOffset = i;
        this.mYTranslationAnimationInDurationMillis = j;
        this.mAlphaAnimationInStartDelayMillis = j2;
        this.mAlphaAnimationDurationMillis = j3;
        this.mTranslationOutInterpolator = new TruncatedInterpolator(Interpolators.EMPHASIZED, j, j3);
    }
}
