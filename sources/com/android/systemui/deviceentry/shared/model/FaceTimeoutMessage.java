package com.android.systemui.deviceentry.shared.model;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class FaceTimeoutMessage extends FaceMessage {
    public final String faceTimeoutMessage;

    public FaceTimeoutMessage(String str) {
        super(str);
        this.faceTimeoutMessage = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof FaceTimeoutMessage) && Intrinsics.areEqual(this.faceTimeoutMessage, ((FaceTimeoutMessage) obj).faceTimeoutMessage);
    }

    public final int hashCode() {
        String str = this.faceTimeoutMessage;
        if (str == null) {
            return 0;
        }
        return str.hashCode();
    }

    public final String toString() {
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(new StringBuilder("FaceTimeoutMessage(faceTimeoutMessage="), this.faceTimeoutMessage, ")");
    }
}
