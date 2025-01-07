package com.android.systemui.biometrics.ui.viewmodel;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class RemainingAttempts {
    public final String message;
    public final Integer remaining;

    public /* synthetic */ RemainingAttempts() {
        this(null, "");
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemainingAttempts)) {
            return false;
        }
        RemainingAttempts remainingAttempts = (RemainingAttempts) obj;
        return Intrinsics.areEqual(this.remaining, remainingAttempts.remaining) && Intrinsics.areEqual(this.message, remainingAttempts.message);
    }

    public final int hashCode() {
        Integer num = this.remaining;
        return this.message.hashCode() + ((num == null ? 0 : num.hashCode()) * 31);
    }

    public final String toString() {
        return "RemainingAttempts(remaining=" + this.remaining + ", message=" + this.message + ")";
    }

    public RemainingAttempts(Integer num, String str) {
        this.remaining = num;
        this.message = str;
    }
}
