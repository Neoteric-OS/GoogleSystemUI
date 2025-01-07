package com.android.systemui.education.data.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.contextualeducation.GestureType;
import java.time.Instant;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class GestureEduModel {
    public final int educationShownCount;
    public final GestureType gestureType;
    public final Instant lastEducationTime;
    public final Instant lastShortcutTriggeredTime;
    public final int signalCount;
    public final Instant usageSessionStartTime;
    public final int userId;

    public GestureEduModel(GestureType gestureType, int i, int i2, Instant instant, Instant instant2, Instant instant3, int i3) {
        this.gestureType = gestureType;
        this.signalCount = i;
        this.educationShownCount = i2;
        this.lastShortcutTriggeredTime = instant;
        this.usageSessionStartTime = instant2;
        this.lastEducationTime = instant3;
        this.userId = i3;
    }

    public static GestureEduModel copy$default(GestureEduModel gestureEduModel, int i, int i2, Instant instant, Instant instant2, Instant instant3, int i3) {
        GestureType gestureType = gestureEduModel.gestureType;
        if ((i3 & 2) != 0) {
            i = gestureEduModel.signalCount;
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            i2 = gestureEduModel.educationShownCount;
        }
        int i5 = i2;
        if ((i3 & 8) != 0) {
            instant = gestureEduModel.lastShortcutTriggeredTime;
        }
        Instant instant4 = instant;
        if ((i3 & 16) != 0) {
            instant2 = gestureEduModel.usageSessionStartTime;
        }
        Instant instant5 = instant2;
        if ((i3 & 32) != 0) {
            instant3 = gestureEduModel.lastEducationTime;
        }
        int i6 = gestureEduModel.userId;
        gestureEduModel.getClass();
        return new GestureEduModel(gestureType, i4, i5, instant4, instant5, instant3, i6);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GestureEduModel)) {
            return false;
        }
        GestureEduModel gestureEduModel = (GestureEduModel) obj;
        return this.gestureType == gestureEduModel.gestureType && this.signalCount == gestureEduModel.signalCount && this.educationShownCount == gestureEduModel.educationShownCount && Intrinsics.areEqual(this.lastShortcutTriggeredTime, gestureEduModel.lastShortcutTriggeredTime) && Intrinsics.areEqual(this.usageSessionStartTime, gestureEduModel.usageSessionStartTime) && Intrinsics.areEqual(this.lastEducationTime, gestureEduModel.lastEducationTime) && this.userId == gestureEduModel.userId;
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.educationShownCount, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.signalCount, this.gestureType.hashCode() * 31, 31), 31);
        Instant instant = this.lastShortcutTriggeredTime;
        int hashCode = (m + (instant == null ? 0 : instant.hashCode())) * 31;
        Instant instant2 = this.usageSessionStartTime;
        int hashCode2 = (hashCode + (instant2 == null ? 0 : instant2.hashCode())) * 31;
        Instant instant3 = this.lastEducationTime;
        return Integer.hashCode(this.userId) + ((hashCode2 + (instant3 != null ? instant3.hashCode() : 0)) * 31);
    }

    public final String toString() {
        Instant instant = this.lastShortcutTriggeredTime;
        Instant instant2 = this.usageSessionStartTime;
        Instant instant3 = this.lastEducationTime;
        StringBuilder sb = new StringBuilder("GestureEduModel(gestureType=");
        sb.append(this.gestureType);
        sb.append(", signalCount=");
        sb.append(this.signalCount);
        sb.append(", educationShownCount=");
        sb.append(this.educationShownCount);
        sb.append(", lastShortcutTriggeredTime=");
        sb.append(instant);
        sb.append(", usageSessionStartTime=");
        sb.append(instant2);
        sb.append(", lastEducationTime=");
        sb.append(instant3);
        sb.append(", userId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userId, ")");
    }
}
