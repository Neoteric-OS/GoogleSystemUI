package com.android.systemui.controls.controller;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ControlInfo {
    public final String controlId;
    public final CharSequence controlSubtitle;
    public final CharSequence controlTitle;
    public final int deviceType;

    public ControlInfo(String str, CharSequence charSequence, CharSequence charSequence2, int i) {
        this.controlId = str;
        this.controlTitle = charSequence;
        this.controlSubtitle = charSequence2;
        this.deviceType = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ControlInfo)) {
            return false;
        }
        ControlInfo controlInfo = (ControlInfo) obj;
        return Intrinsics.areEqual(this.controlId, controlInfo.controlId) && Intrinsics.areEqual(this.controlTitle, controlInfo.controlTitle) && Intrinsics.areEqual(this.controlSubtitle, controlInfo.controlSubtitle) && this.deviceType == controlInfo.deviceType;
    }

    public final int hashCode() {
        return Integer.hashCode(this.deviceType) + ((this.controlSubtitle.hashCode() + ((this.controlTitle.hashCode() + (this.controlId.hashCode() * 31)) * 31)) * 31);
    }

    public final String toString() {
        return ":" + this.controlId + ":" + ((Object) this.controlTitle) + ":" + this.deviceType;
    }
}
