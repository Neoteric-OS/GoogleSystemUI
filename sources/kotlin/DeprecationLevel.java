package kotlin;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class DeprecationLevel {
    public static final /* synthetic */ DeprecationLevel[] $VALUES;
    public static final DeprecationLevel ERROR = null;
    public static final DeprecationLevel WARNING;

    static {
        DeprecationLevel deprecationLevel = new DeprecationLevel("WARNING", 0);
        WARNING = deprecationLevel;
        DeprecationLevel[] deprecationLevelArr = {deprecationLevel, new DeprecationLevel("ERROR", 1), new DeprecationLevel("HIDDEN", 2)};
        $VALUES = deprecationLevelArr;
        EnumEntriesKt.enumEntries(deprecationLevelArr);
    }

    public static DeprecationLevel valueOf(String str) {
        return (DeprecationLevel) Enum.valueOf(DeprecationLevel.class, str);
    }

    public static DeprecationLevel[] values() {
        return (DeprecationLevel[]) $VALUES.clone();
    }
}
