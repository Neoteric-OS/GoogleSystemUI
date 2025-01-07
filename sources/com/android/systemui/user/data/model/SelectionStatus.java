package com.android.systemui.user.data.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class SelectionStatus {
    public static final /* synthetic */ SelectionStatus[] $VALUES;
    public static final SelectionStatus SELECTION_COMPLETE;
    public static final SelectionStatus SELECTION_IN_PROGRESS;

    static {
        SelectionStatus selectionStatus = new SelectionStatus("SELECTION_IN_PROGRESS", 0);
        SELECTION_IN_PROGRESS = selectionStatus;
        SelectionStatus selectionStatus2 = new SelectionStatus("SELECTION_COMPLETE", 1);
        SELECTION_COMPLETE = selectionStatus2;
        SelectionStatus[] selectionStatusArr = {selectionStatus, selectionStatus2};
        $VALUES = selectionStatusArr;
        EnumEntriesKt.enumEntries(selectionStatusArr);
    }

    public static SelectionStatus valueOf(String str) {
        return (SelectionStatus) Enum.valueOf(SelectionStatus.class, str);
    }

    public static SelectionStatus[] values() {
        return (SelectionStatus[]) $VALUES.clone();
    }
}
