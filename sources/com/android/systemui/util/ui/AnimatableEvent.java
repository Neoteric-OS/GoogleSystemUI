package com.android.systemui.util.ui;

import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class AnimatableEvent {
    public final boolean startAnimating;
    public final Object value;

    public AnimatableEvent(Object obj, boolean z) {
        this.value = obj;
        this.startAnimating = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnimatableEvent)) {
            return false;
        }
        AnimatableEvent animatableEvent = (AnimatableEvent) obj;
        return Intrinsics.areEqual(this.value, animatableEvent.value) && this.startAnimating == animatableEvent.startAnimating;
    }

    public final int hashCode() {
        Object obj = this.value;
        return Boolean.hashCode(this.startAnimating) + ((obj == null ? 0 : obj.hashCode()) * 31);
    }

    public final String toString() {
        return "AnimatableEvent(value=" + this.value + ", startAnimating=" + this.startAnimating + ")";
    }
}
