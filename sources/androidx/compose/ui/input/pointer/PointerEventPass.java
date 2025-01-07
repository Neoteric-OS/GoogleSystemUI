package androidx.compose.ui.input.pointer;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class PointerEventPass {
    public static final /* synthetic */ PointerEventPass[] $VALUES;
    public static final PointerEventPass Final;
    public static final PointerEventPass Initial;
    public static final PointerEventPass Main;

    static {
        PointerEventPass pointerEventPass = new PointerEventPass("Initial", 0);
        Initial = pointerEventPass;
        PointerEventPass pointerEventPass2 = new PointerEventPass("Main", 1);
        Main = pointerEventPass2;
        PointerEventPass pointerEventPass3 = new PointerEventPass("Final", 2);
        Final = pointerEventPass3;
        $VALUES = new PointerEventPass[]{pointerEventPass, pointerEventPass2, pointerEventPass3};
    }

    public static PointerEventPass valueOf(String str) {
        return (PointerEventPass) Enum.valueOf(PointerEventPass.class, str);
    }

    public static PointerEventPass[] values() {
        return (PointerEventPass[]) $VALUES.clone();
    }
}
