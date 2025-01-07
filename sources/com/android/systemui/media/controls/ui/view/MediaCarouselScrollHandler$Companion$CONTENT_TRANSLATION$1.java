package com.android.systemui.media.controls.ui.view;

import androidx.dynamicanimation.animation.FloatPropertyCompat;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class MediaCarouselScrollHandler$Companion$CONTENT_TRANSLATION$1 extends FloatPropertyCompat {
    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public final float getValue(Object obj) {
        return ((MediaCarouselScrollHandler) obj).contentTranslation;
    }

    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public final void setValue(Object obj, float f) {
        ((MediaCarouselScrollHandler) obj).setContentTranslation(f);
    }
}
