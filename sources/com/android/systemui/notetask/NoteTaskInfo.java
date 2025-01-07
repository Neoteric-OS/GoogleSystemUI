package com.android.systemui.notetask;

import android.os.UserHandle;
import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import androidx.compose.animation.core.KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0;
import com.android.systemui.notetask.NoteTaskLaunchMode;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NoteTaskInfo {
    public final NoteTaskEntryPoint entryPoint;
    public final boolean isKeyguardLocked;
    public final NoteTaskLaunchMode launchMode;
    public final String packageName;
    public final int uid;
    public final UserHandle user;

    public NoteTaskInfo(String str, int i, UserHandle userHandle, NoteTaskEntryPoint noteTaskEntryPoint, boolean z) {
        this.packageName = str;
        this.uid = i;
        this.user = userHandle;
        this.entryPoint = noteTaskEntryPoint;
        this.isKeyguardLocked = z;
        this.launchMode = (z || noteTaskEntryPoint == NoteTaskEntryPoint.WIDGET_PICKER_SHORTCUT_IN_MULTI_WINDOW_MODE) ? NoteTaskLaunchMode.Activity.INSTANCE : NoteTaskLaunchMode.AppBubble.INSTANCE;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NoteTaskInfo)) {
            return false;
        }
        NoteTaskInfo noteTaskInfo = (NoteTaskInfo) obj;
        return Intrinsics.areEqual(this.packageName, noteTaskInfo.packageName) && this.uid == noteTaskInfo.uid && Intrinsics.areEqual(this.user, noteTaskInfo.user) && this.entryPoint == noteTaskInfo.entryPoint && this.isKeyguardLocked == noteTaskInfo.isKeyguardLocked;
    }

    public final int hashCode() {
        int hashCode = (this.user.hashCode() + KeyframesSpec$KeyframeEntity$$ExternalSyntheticOutline0.m(this.uid, this.packageName.hashCode() * 31, 31)) * 31;
        NoteTaskEntryPoint noteTaskEntryPoint = this.entryPoint;
        return Boolean.hashCode(this.isKeyguardLocked) + ((hashCode + (noteTaskEntryPoint == null ? 0 : noteTaskEntryPoint.hashCode())) * 31);
    }

    public final String toString() {
        UserHandle userHandle = this.user;
        StringBuilder sb = new StringBuilder("NoteTaskInfo(packageName=");
        sb.append(this.packageName);
        sb.append(", uid=");
        sb.append(this.uid);
        sb.append(", user=");
        sb.append(userHandle);
        sb.append(", entryPoint=");
        sb.append(this.entryPoint);
        sb.append(", isKeyguardLocked=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, this.isKeyguardLocked, ")");
    }
}
