package com.android.systemui.shade.data.repository;

import androidx.compose.animation.FlingCalculator$FlingInfo$$ExternalSyntheticOutline0;
import java.util.UUID;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FlingInfo {
    public final boolean expand;
    public final UUID id;
    public final float velocity;

    public FlingInfo(float f, boolean z) {
        UUID randomUUID = UUID.randomUUID();
        this.expand = z;
        this.velocity = f;
        this.id = randomUUID;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FlingInfo)) {
            return false;
        }
        FlingInfo flingInfo = (FlingInfo) obj;
        return this.expand == flingInfo.expand && Float.compare(this.velocity, flingInfo.velocity) == 0 && Intrinsics.areEqual(this.id, flingInfo.id);
    }

    public final int hashCode() {
        return this.id.hashCode() + FlingCalculator$FlingInfo$$ExternalSyntheticOutline0.m(Boolean.hashCode(this.expand) * 31, this.velocity, 31);
    }

    public final String toString() {
        return "FlingInfo(expand=" + this.expand + ", velocity=" + this.velocity + ", id=" + this.id + ")";
    }
}
