package com.android.systemui.education.shared.model;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import com.android.systemui.contextualeducation.GestureType;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EducationInfo {
    public final EducationUiType educationUiType;
    public final GestureType gestureType;
    public final int userId;

    public EducationInfo(GestureType gestureType, EducationUiType educationUiType, int i) {
        this.gestureType = gestureType;
        this.educationUiType = educationUiType;
        this.userId = i;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EducationInfo)) {
            return false;
        }
        EducationInfo educationInfo = (EducationInfo) obj;
        return this.gestureType == educationInfo.gestureType && this.educationUiType == educationInfo.educationUiType && this.userId == educationInfo.userId;
    }

    public final int hashCode() {
        return Integer.hashCode(this.userId) + ((this.educationUiType.hashCode() + (this.gestureType.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("EducationInfo(gestureType=");
        sb.append(this.gestureType);
        sb.append(", educationUiType=");
        sb.append(this.educationUiType);
        sb.append(", userId=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.userId, ")");
    }
}
