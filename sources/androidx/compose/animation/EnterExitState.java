package androidx.compose.animation;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class EnterExitState {
    public static final /* synthetic */ EnterExitState[] $VALUES;
    public static final EnterExitState PostExit;
    public static final EnterExitState PreEnter;
    public static final EnterExitState Visible;

    static {
        EnterExitState enterExitState = new EnterExitState("PreEnter", 0);
        PreEnter = enterExitState;
        EnterExitState enterExitState2 = new EnterExitState("Visible", 1);
        Visible = enterExitState2;
        EnterExitState enterExitState3 = new EnterExitState("PostExit", 2);
        PostExit = enterExitState3;
        $VALUES = new EnterExitState[]{enterExitState, enterExitState2, enterExitState3};
    }

    public static EnterExitState valueOf(String str) {
        return (EnterExitState) Enum.valueOf(EnterExitState.class, str);
    }

    public static EnterExitState[] values() {
        return (EnterExitState[]) $VALUES.clone();
    }
}
