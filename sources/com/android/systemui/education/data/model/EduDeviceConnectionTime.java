package com.android.systemui.education.data.model;

import java.time.Instant;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EduDeviceConnectionTime {
    public final Instant keyboardFirstConnectionTime;
    public final Instant touchpadFirstConnectionTime;

    public EduDeviceConnectionTime(Instant instant, Instant instant2) {
        this.keyboardFirstConnectionTime = instant;
        this.touchpadFirstConnectionTime = instant2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EduDeviceConnectionTime)) {
            return false;
        }
        EduDeviceConnectionTime eduDeviceConnectionTime = (EduDeviceConnectionTime) obj;
        return Intrinsics.areEqual(this.keyboardFirstConnectionTime, eduDeviceConnectionTime.keyboardFirstConnectionTime) && Intrinsics.areEqual(this.touchpadFirstConnectionTime, eduDeviceConnectionTime.touchpadFirstConnectionTime);
    }

    public final int hashCode() {
        Instant instant = this.keyboardFirstConnectionTime;
        int hashCode = (instant == null ? 0 : instant.hashCode()) * 31;
        Instant instant2 = this.touchpadFirstConnectionTime;
        return hashCode + (instant2 != null ? instant2.hashCode() : 0);
    }

    public final String toString() {
        return "EduDeviceConnectionTime(keyboardFirstConnectionTime=" + this.keyboardFirstConnectionTime + ", touchpadFirstConnectionTime=" + this.touchpadFirstConnectionTime + ")";
    }
}
