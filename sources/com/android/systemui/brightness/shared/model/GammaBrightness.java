package com.android.systemui.brightness.shared.model;

import androidx.appsearch.app.GenericDocument$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GammaBrightness {
    public final int value;

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m789toStringimpl(int i) {
        return GenericDocument$$ExternalSyntheticOutline0.m("GammaBrightness(value=", ")", i);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof GammaBrightness) {
            return this.value == ((GammaBrightness) obj).value;
        }
        return false;
    }

    public final int hashCode() {
        return Integer.hashCode(this.value);
    }

    public final String toString() {
        return m789toStringimpl(this.value);
    }
}
