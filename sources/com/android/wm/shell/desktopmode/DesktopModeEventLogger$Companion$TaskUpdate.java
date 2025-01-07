package com.android.wm.shell.desktopmode;

import androidx.appsearch.safeparcel.PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DesktopModeEventLogger$Companion$TaskUpdate {
    public final int instanceId;
    public final int taskHeight;
    public final int taskWidth;
    public final int taskX;
    public final int taskY;
    public final int uid;
    public final int visibleTaskCount;

    public DesktopModeEventLogger$Companion$TaskUpdate(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.instanceId = i;
        this.uid = i2;
        this.taskHeight = i3;
        this.taskWidth = i4;
        this.taskX = i5;
        this.taskY = i6;
        this.visibleTaskCount = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DesktopModeEventLogger$Companion$TaskUpdate)) {
            return false;
        }
        DesktopModeEventLogger$Companion$TaskUpdate desktopModeEventLogger$Companion$TaskUpdate = (DesktopModeEventLogger$Companion$TaskUpdate) obj;
        return this.instanceId == desktopModeEventLogger$Companion$TaskUpdate.instanceId && this.uid == desktopModeEventLogger$Companion$TaskUpdate.uid && this.taskHeight == desktopModeEventLogger$Companion$TaskUpdate.taskHeight && this.taskWidth == desktopModeEventLogger$Companion$TaskUpdate.taskWidth && this.taskX == desktopModeEventLogger$Companion$TaskUpdate.taskX && this.taskY == desktopModeEventLogger$Companion$TaskUpdate.taskY && this.visibleTaskCount == desktopModeEventLogger$Companion$TaskUpdate.visibleTaskCount;
    }

    public final int hashCode() {
        return Integer.hashCode(this.visibleTaskCount) + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskY, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskX, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskWidth, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.taskHeight, KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.uid, Integer.hashCode(this.instanceId) * 31, 31), 31), 31), 31), 29791);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("TaskUpdate(instanceId=");
        sb.append(this.instanceId);
        sb.append(", uid=");
        sb.append(this.uid);
        sb.append(", taskHeight=");
        sb.append(this.taskHeight);
        sb.append(", taskWidth=");
        sb.append(this.taskWidth);
        sb.append(", taskX=");
        sb.append(this.taskX);
        sb.append(", taskY=");
        sb.append(this.taskY);
        sb.append(", minimizeReason=null, unminimizeReason=null, visibleTaskCount=");
        return PropertyConfigParcel$JoinableConfigParcel$$ExternalSyntheticOutline0.m(sb, this.visibleTaskCount, ")");
    }
}
