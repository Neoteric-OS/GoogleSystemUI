package com.android.systemui.privacy;

import androidx.activity.result.ActivityResultRegistry$$ExternalSyntheticOutline0;
import androidx.compose.animation.Scale$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PrivacyItem {
    public final PrivacyApplication application;
    public final String log;
    public final boolean paused;
    public final PrivacyType privacyType;
    public final long timeStampElapsed;

    public PrivacyItem(PrivacyType privacyType, PrivacyApplication privacyApplication, long j, boolean z) {
        this.privacyType = privacyType;
        this.application = privacyApplication;
        this.timeStampElapsed = j;
        this.paused = z;
        StringBuilder m = ActivityResultRegistry$$ExternalSyntheticOutline0.m("(", privacyType.getLogName(), ", ");
        m.append(privacyApplication.packageName);
        m.append("(");
        m.append(privacyApplication.uid);
        m.append("), ");
        m.append(j);
        m.append(", paused=");
        m.append(z);
        m.append(")");
        this.log = m.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PrivacyItem)) {
            return false;
        }
        PrivacyItem privacyItem = (PrivacyItem) obj;
        return this.privacyType == privacyItem.privacyType && Intrinsics.areEqual(this.application, privacyItem.application) && this.timeStampElapsed == privacyItem.timeStampElapsed && this.paused == privacyItem.paused;
    }

    public final int hashCode() {
        return Boolean.hashCode(this.paused) + Scale$$ExternalSyntheticOutline0.m((this.application.hashCode() + (this.privacyType.hashCode() * 31)) * 31, 31, this.timeStampElapsed);
    }

    public final String toString() {
        return "PrivacyItem(privacyType=" + this.privacyType + ", application=" + this.application + ", timeStampElapsed=" + this.timeStampElapsed + ", paused=" + this.paused + ")";
    }
}
