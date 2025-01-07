package com.android.systemui.statusbar.notification.collection.inflation;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public interface NotifInflater {

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Params {
        public final boolean isChildInGroup;
        public final boolean isGroupSummary;
        public final boolean isMinimized;
        public final boolean needsRedaction;
        public final String reason;
        public final boolean showSnooze;

        public Params(boolean z, String str, boolean z2, boolean z3, boolean z4, boolean z5) {
            this.isMinimized = z;
            this.reason = str;
            this.showSnooze = z2;
            this.isChildInGroup = z3;
            this.isGroupSummary = z4;
            this.needsRedaction = z5;
        }
    }
}
