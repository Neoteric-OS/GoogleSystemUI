package androidx.compose.foundation.text;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class Handle {
    public static final /* synthetic */ Handle[] $VALUES;
    public static final Handle Cursor;
    public static final Handle SelectionEnd;
    public static final Handle SelectionStart;

    static {
        Handle handle = new Handle("Cursor", 0);
        Cursor = handle;
        Handle handle2 = new Handle("SelectionStart", 1);
        SelectionStart = handle2;
        Handle handle3 = new Handle("SelectionEnd", 2);
        SelectionEnd = handle3;
        $VALUES = new Handle[]{handle, handle2, handle3};
    }

    public static Handle valueOf(String str) {
        return (Handle) Enum.valueOf(Handle.class, str);
    }

    public static Handle[] values() {
        return (Handle[]) $VALUES.clone();
    }
}
