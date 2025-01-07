package com.android.systemui.mediaprojection.permission;

import androidx.activity.ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ScreenShareOption {
    public final int mode;
    public final String spinnerDisabledText;
    public final int spinnerText;
    public final int startButtonText;
    public final int warningText;

    public ScreenShareOption(int i, int i2, int i3, int i4, String str) {
        this.mode = i;
        this.spinnerText = i2;
        this.warningText = i3;
        this.startButtonText = i4;
        this.spinnerDisabledText = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScreenShareOption)) {
            return false;
        }
        ScreenShareOption screenShareOption = (ScreenShareOption) obj;
        return this.mode == screenShareOption.mode && this.spinnerText == screenShareOption.spinnerText && this.warningText == screenShareOption.warningText && this.startButtonText == screenShareOption.startButtonText && Intrinsics.areEqual(this.spinnerDisabledText, screenShareOption.spinnerDisabledText);
    }

    public final int hashCode() {
        int m = KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.startButtonText, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.warningText, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.spinnerText, Integer.hashCode(this.mode) * 31, 31), 31), 31);
        String str = this.spinnerDisabledText;
        return m + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ScreenShareOption(mode=");
        sb.append(this.mode);
        sb.append(", spinnerText=");
        sb.append(this.spinnerText);
        sb.append(", warningText=");
        sb.append(this.warningText);
        sb.append(", startButtonText=");
        sb.append(this.startButtonText);
        sb.append(", spinnerDisabledText=");
        return ComponentActivity$activityResultRegistry$1$$ExternalSyntheticOutline0.m(sb, this.spinnerDisabledText, ")");
    }
}
