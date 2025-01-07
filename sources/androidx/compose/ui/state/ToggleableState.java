package androidx.compose.ui.state;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ToggleableState {
    public static final /* synthetic */ ToggleableState[] $VALUES;
    public static final ToggleableState Off;
    public static final ToggleableState On;

    static {
        ToggleableState toggleableState = new ToggleableState("On", 0);
        On = toggleableState;
        ToggleableState toggleableState2 = new ToggleableState("Off", 1);
        Off = toggleableState2;
        $VALUES = new ToggleableState[]{toggleableState, toggleableState2, new ToggleableState("Indeterminate", 2)};
    }

    public static ToggleableState valueOf(String str) {
        return (ToggleableState) Enum.valueOf(ToggleableState.class, str);
    }

    public static ToggleableState[] values() {
        return (ToggleableState[]) $VALUES.clone();
    }
}
