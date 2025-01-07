package com.android.settingslib.animation;

import android.content.Context;
import android.view.animation.Interpolator;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DisappearAnimationUtils extends AppearAnimationUtils {
    public static final AnonymousClass1 ROW_TRANSLATION_SCALER = new AnonymousClass1();

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    /* renamed from: com.android.settingslib.animation.DisappearAnimationUtils$1, reason: invalid class name */
    public final class AnonymousClass1 {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisappearAnimationUtils(Context context, long j, float f, float f2, Interpolator interpolator) {
        super(context, j, f, f2, interpolator);
        AnonymousClass1 anonymousClass1 = ROW_TRANSLATION_SCALER;
        this.mRowTranslationScaler = anonymousClass1;
        this.mAppearing = false;
    }

    @Override // com.android.settingslib.animation.AppearAnimationUtils
    public final long calculateDelay(int i, int i2) {
        return (long) ((((Math.pow(i, 0.4d) + 0.4d) * i2 * 10.0d) + (i * 60)) * this.mDelayScale);
    }
}
