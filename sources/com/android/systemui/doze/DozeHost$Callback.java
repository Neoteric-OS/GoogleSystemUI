package com.android.systemui.doze;

import com.android.systemui.statusbar.phone.DozeServiceHost$$ExternalSyntheticLambda1;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public interface DozeHost$Callback {
    default void onPowerSaveChanged() {
    }

    default void onAlwaysOnSuppressedChanged(boolean z) {
    }

    default void onNotificationAlerted(DozeServiceHost$$ExternalSyntheticLambda1 dozeServiceHost$$ExternalSyntheticLambda1) {
    }
}
