package com.android.systemui.statusbar;

import android.frameworks.stats.AnnotationValue$1$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public abstract class StatusBarState {
    public static String toString(int i) {
        return i != 0 ? i != 1 ? i != 2 ? AnnotationValue$1$$ExternalSyntheticOutline0.m(i, "UNKNOWN: ") : "SHADE_LOCKED" : "KEYGUARD" : "SHADE";
    }
}
