package androidx.compose.foundation.text;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class HandleState {
    public static final /* synthetic */ HandleState[] $VALUES;
    public static final HandleState Cursor;
    public static final HandleState None;
    public static final HandleState Selection;

    static {
        HandleState handleState = new HandleState("None", 0);
        None = handleState;
        HandleState handleState2 = new HandleState("Selection", 1);
        Selection = handleState2;
        HandleState handleState3 = new HandleState("Cursor", 2);
        Cursor = handleState3;
        $VALUES = new HandleState[]{handleState, handleState2, handleState3};
    }

    public static HandleState valueOf(String str) {
        return (HandleState) Enum.valueOf(HandleState.class, str);
    }

    public static HandleState[] values() {
        return (HandleState[]) $VALUES.clone();
    }
}
