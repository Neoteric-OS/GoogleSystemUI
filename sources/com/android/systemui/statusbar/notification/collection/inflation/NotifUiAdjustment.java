package com.android.systemui.statusbar.notification.collection.inflation;

import java.util.List;

/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class NotifUiAdjustment {
    public final boolean isChildInGroup;
    public final boolean isConversation;
    public final boolean isGroupSummary;
    public final boolean isMinimized;
    public final boolean isSnoozeEnabled;
    public final boolean needsRedaction;
    public final List smartActions;
    public final List smartReplies;

    public NotifUiAdjustment(List list, List list2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.smartActions = list;
        this.smartReplies = list2;
        this.isConversation = z;
        this.isSnoozeEnabled = z2;
        this.isMinimized = z3;
        this.needsRedaction = z4;
        this.isChildInGroup = z5;
        this.isGroupSummary = z6;
    }
}
