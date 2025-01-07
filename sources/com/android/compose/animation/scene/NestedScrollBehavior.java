package com.android.compose.animation.scene;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class NestedScrollBehavior {
    public static final /* synthetic */ NestedScrollBehavior[] $VALUES;
    public static final Companion Companion;
    public static final NestedScrollBehavior Default;
    public static final NestedScrollBehavior EdgeAlways = null;
    public static final NestedScrollBehavior EdgeWithPreview = null;
    private final boolean canStartOnPostFling;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        NestedScrollBehavior nestedScrollBehavior = new NestedScrollBehavior(0, "EdgeNoPreview", false);
        NestedScrollBehavior[] nestedScrollBehaviorArr = {nestedScrollBehavior, new NestedScrollBehavior(1, "EdgeWithPreview", true), new NestedScrollBehavior(2, "EdgeAlways", true)};
        $VALUES = nestedScrollBehaviorArr;
        EnumEntriesKt.enumEntries(nestedScrollBehaviorArr);
        Companion = new Companion();
        Default = nestedScrollBehavior;
    }

    public NestedScrollBehavior(int i, String str, boolean z) {
        this.canStartOnPostFling = z;
    }

    public static NestedScrollBehavior valueOf(String str) {
        return (NestedScrollBehavior) Enum.valueOf(NestedScrollBehavior.class, str);
    }

    public static NestedScrollBehavior[] values() {
        return (NestedScrollBehavior[]) $VALUES.clone();
    }

    public final boolean getCanStartOnPostFling() {
        return this.canStartOnPostFling;
    }
}
