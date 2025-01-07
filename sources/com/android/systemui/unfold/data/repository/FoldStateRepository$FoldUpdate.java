package com.android.systemui.unfold.data.repository;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class FoldStateRepository$FoldUpdate {
    public static final /* synthetic */ FoldStateRepository$FoldUpdate[] $VALUES;
    public static final Companion Companion;
    public static final FoldStateRepository$FoldUpdate FINISH_CLOSED;
    public static final FoldStateRepository$FoldUpdate FINISH_FULL_OPEN;
    public static final FoldStateRepository$FoldUpdate FINISH_HALF_OPEN;
    public static final FoldStateRepository$FoldUpdate START_CLOSING;
    public static final FoldStateRepository$FoldUpdate START_OPENING;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        FoldStateRepository$FoldUpdate foldStateRepository$FoldUpdate = new FoldStateRepository$FoldUpdate("START_OPENING", 0);
        START_OPENING = foldStateRepository$FoldUpdate;
        FoldStateRepository$FoldUpdate foldStateRepository$FoldUpdate2 = new FoldStateRepository$FoldUpdate("START_CLOSING", 1);
        START_CLOSING = foldStateRepository$FoldUpdate2;
        FoldStateRepository$FoldUpdate foldStateRepository$FoldUpdate3 = new FoldStateRepository$FoldUpdate("FINISH_HALF_OPEN", 2);
        FINISH_HALF_OPEN = foldStateRepository$FoldUpdate3;
        FoldStateRepository$FoldUpdate foldStateRepository$FoldUpdate4 = new FoldStateRepository$FoldUpdate("FINISH_FULL_OPEN", 3);
        FINISH_FULL_OPEN = foldStateRepository$FoldUpdate4;
        FoldStateRepository$FoldUpdate foldStateRepository$FoldUpdate5 = new FoldStateRepository$FoldUpdate("FINISH_CLOSED", 4);
        FINISH_CLOSED = foldStateRepository$FoldUpdate5;
        FoldStateRepository$FoldUpdate[] foldStateRepository$FoldUpdateArr = {foldStateRepository$FoldUpdate, foldStateRepository$FoldUpdate2, foldStateRepository$FoldUpdate3, foldStateRepository$FoldUpdate4, foldStateRepository$FoldUpdate5};
        $VALUES = foldStateRepository$FoldUpdateArr;
        EnumEntriesKt.enumEntries(foldStateRepository$FoldUpdateArr);
        Companion = new Companion();
    }

    public static FoldStateRepository$FoldUpdate valueOf(String str) {
        return (FoldStateRepository$FoldUpdate) Enum.valueOf(FoldStateRepository$FoldUpdate.class, str);
    }

    public static FoldStateRepository$FoldUpdate[] values() {
        return (FoldStateRepository$FoldUpdate[]) $VALUES.clone();
    }
}
