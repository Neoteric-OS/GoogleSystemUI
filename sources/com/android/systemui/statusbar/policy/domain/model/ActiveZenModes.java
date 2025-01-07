package com.android.systemui.statusbar.policy.domain.model;

import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class ActiveZenModes {
    public final ZenModeInfo mainMode;
    public final List modeNames;

    public ActiveZenModes(List list, ZenModeInfo zenModeInfo) {
        this.modeNames = list;
        this.mainMode = zenModeInfo;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ActiveZenModes)) {
            return false;
        }
        ActiveZenModes activeZenModes = (ActiveZenModes) obj;
        return Intrinsics.areEqual(this.modeNames, activeZenModes.modeNames) && Intrinsics.areEqual(this.mainMode, activeZenModes.mainMode);
    }

    public final int hashCode() {
        int hashCode = this.modeNames.hashCode() * 31;
        ZenModeInfo zenModeInfo = this.mainMode;
        return hashCode + (zenModeInfo == null ? 0 : zenModeInfo.hashCode());
    }

    public final String toString() {
        return "ActiveZenModes(modeNames=" + this.modeNames + ", mainMode=" + this.mainMode + ")";
    }
}
