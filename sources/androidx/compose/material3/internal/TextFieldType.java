package androidx.compose.material3.internal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class TextFieldType {
    public static final /* synthetic */ TextFieldType[] $VALUES;
    public static final TextFieldType Filled;

    static {
        TextFieldType textFieldType = new TextFieldType("Filled", 0);
        Filled = textFieldType;
        $VALUES = new TextFieldType[]{textFieldType, new TextFieldType("Outlined", 1)};
    }

    public static TextFieldType valueOf(String str) {
        return (TextFieldType) Enum.valueOf(TextFieldType.class, str);
    }

    public static TextFieldType[] values() {
        return (TextFieldType[]) $VALUES.clone();
    }
}
