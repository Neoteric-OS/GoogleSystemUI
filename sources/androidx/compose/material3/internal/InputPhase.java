package androidx.compose.material3.internal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
final class InputPhase {
    public static final /* synthetic */ InputPhase[] $VALUES;
    public static final InputPhase Focused;
    public static final InputPhase UnfocusedEmpty;
    public static final InputPhase UnfocusedNotEmpty;

    static {
        InputPhase inputPhase = new InputPhase("Focused", 0);
        Focused = inputPhase;
        InputPhase inputPhase2 = new InputPhase("UnfocusedEmpty", 1);
        UnfocusedEmpty = inputPhase2;
        InputPhase inputPhase3 = new InputPhase("UnfocusedNotEmpty", 2);
        UnfocusedNotEmpty = inputPhase3;
        $VALUES = new InputPhase[]{inputPhase, inputPhase2, inputPhase3};
    }

    public static InputPhase valueOf(String str) {
        return (InputPhase) Enum.valueOf(InputPhase.class, str);
    }

    public static InputPhase[] values() {
        return (InputPhase[]) $VALUES.clone();
    }
}
