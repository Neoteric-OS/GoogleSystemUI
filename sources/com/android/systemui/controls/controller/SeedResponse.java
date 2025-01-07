package com.android.systemui.controls.controller;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SeedResponse {
    public final boolean accepted;
    public final String packageName;

    public SeedResponse(String str, boolean z) {
        this.packageName = str;
        this.accepted = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeedResponse)) {
            return false;
        }
        SeedResponse seedResponse = (SeedResponse) obj;
        return this.packageName.equals(seedResponse.packageName) && this.accepted == seedResponse.accepted;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.accepted) + (this.packageName.hashCode() * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("SeedResponse(packageName=");
        sb.append(this.packageName);
        sb.append(", accepted=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.accepted, ")");
    }
}
