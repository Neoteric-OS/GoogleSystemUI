package com.android.systemui.statusbar.notification.collection;

import androidx.appcompat.app.AppCompatDelegateImpl$$ExternalSyntheticOutline0;
import com.android.systemui.statusbar.notification.collection.listbuilder.NotifSection;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SuppressedAttachState {
    public GroupEntry parent;
    public NotifSection section;
    public boolean wasPruneSuppressed;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SuppressedAttachState)) {
            return false;
        }
        SuppressedAttachState suppressedAttachState = (SuppressedAttachState) obj;
        return Intrinsics.areEqual(this.section, suppressedAttachState.section) && Intrinsics.areEqual(this.parent, suppressedAttachState.parent) && this.wasPruneSuppressed == suppressedAttachState.wasPruneSuppressed;
    }

    public final int hashCode() {
        NotifSection notifSection = this.section;
        int hashCode = (notifSection == null ? 0 : notifSection.hashCode()) * 31;
        GroupEntry groupEntry = this.parent;
        return Boolean.hashCode(this.wasPruneSuppressed) + ((hashCode + (groupEntry != null ? groupEntry.hashCode() : 0)) * 31);
    }

    public final String toString() {
        NotifSection notifSection = this.section;
        GroupEntry groupEntry = this.parent;
        boolean z = this.wasPruneSuppressed;
        StringBuilder sb = new StringBuilder("SuppressedAttachState(section=");
        sb.append(notifSection);
        sb.append(", parent=");
        sb.append(groupEntry);
        sb.append(", wasPruneSuppressed=");
        return AppCompatDelegateImpl$$ExternalSyntheticOutline0.m(sb, z, ")");
    }
}
