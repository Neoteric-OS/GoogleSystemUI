package com.android.keyguard;

import java.util.Objects;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TrustGrantFlags {
    public final int mFlags;

    public TrustGrantFlags(int i) {
        this.mFlags = i;
    }

    public final boolean dismissKeyguardRequested() {
        return (this.mFlags & 2) != 0;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof TrustGrantFlags) && ((TrustGrantFlags) obj).mFlags == this.mFlags;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(this.mFlags));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        int i = this.mFlags;
        sb.append(i);
        sb.append("]=");
        if ((i & 1) != 0) {
            sb.append("initiatedByUser|");
        }
        if (dismissKeyguardRequested()) {
            sb.append("dismissKeyguard|");
        }
        if ((i & 4) != 0) {
            sb.append("temporaryAndRenewable|");
        }
        if ((i & 8) != 0) {
            sb.append("displayMessage|");
        }
        return sb.toString();
    }
}
