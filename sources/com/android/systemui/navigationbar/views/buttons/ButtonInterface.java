package com.android.systemui.navigationbar.views.buttons;

import android.graphics.drawable.Drawable;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface ButtonInterface {
    void abortCurrentGesture();

    void setDarkIntensity(float f);

    void setImageDrawable(Drawable drawable);

    void setVertical(boolean z);

    default void animateLongPress(boolean z, boolean z2, long j) {
    }
}
