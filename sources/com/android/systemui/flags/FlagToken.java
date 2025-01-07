package com.android.systemui.flags;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FlagToken {
    public final boolean isEnabled;
    public final String name;

    public FlagToken(String str, boolean z) {
        this.name = str;
        this.isEnabled = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlagToken)) {
            return false;
        }
        FlagToken flagToken = (FlagToken) obj;
        return this.name.equals(flagToken.name) && this.isEnabled == flagToken.isEnabled;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.isEnabled) + (this.name.hashCode() * 31);
    }

    public final String toString() {
        return this.name + " (" + (this.isEnabled ? "enabled" : "disabled") + ")";
    }
}
