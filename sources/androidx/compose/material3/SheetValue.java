package androidx.compose.material3;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class SheetValue {
    public static final /* synthetic */ SheetValue[] $VALUES;
    public static final SheetValue Expanded;
    public static final SheetValue Hidden;
    public static final SheetValue PartiallyExpanded;

    static {
        SheetValue sheetValue = new SheetValue("Hidden", 0);
        Hidden = sheetValue;
        SheetValue sheetValue2 = new SheetValue("Expanded", 1);
        Expanded = sheetValue2;
        SheetValue sheetValue3 = new SheetValue("PartiallyExpanded", 2);
        PartiallyExpanded = sheetValue3;
        $VALUES = new SheetValue[]{sheetValue, sheetValue2, sheetValue3};
    }

    public static SheetValue valueOf(String str) {
        return (SheetValue) Enum.valueOf(SheetValue.class, str);
    }

    public static SheetValue[] values() {
        return (SheetValue[]) $VALUES.clone();
    }
}
