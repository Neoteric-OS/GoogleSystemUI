package com.android.systemui.biometrics.shared.model;

import com.airbnb.lottie.model.KeyPath;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class LottieCallback {
    public final int color;
    public final KeyPath keypath;

    public LottieCallback(KeyPath keyPath, int i) {
        this.keypath = keyPath;
        this.color = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LottieCallback)) {
            return false;
        }
        LottieCallback lottieCallback = (LottieCallback) obj;
        return Intrinsics.areEqual(this.keypath, lottieCallback.keypath) && this.color == lottieCallback.color;
    }

    public final int hashCode() {
        return Integer.hashCode(this.color) + (this.keypath.hashCode() * 31);
    }

    public final String toString() {
        return "LottieCallback(keypath=" + this.keypath + ", color=" + this.color + ")";
    }
}
