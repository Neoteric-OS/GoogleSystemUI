package kotlin.collections;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class State {
    public static final /* synthetic */ State[] $VALUES;
    public static final State Done;
    public static final State Failed;
    public static final State NotReady;
    public static final State Ready;

    static {
        State state = new State("Ready", 0);
        Ready = state;
        State state2 = new State("NotReady", 1);
        NotReady = state2;
        State state3 = new State("Done", 2);
        Done = state3;
        State state4 = new State("Failed", 3);
        Failed = state4;
        State[] stateArr = {state, state2, state3, state4};
        $VALUES = stateArr;
        EnumEntriesKt.enumEntries(stateArr);
    }

    public static State valueOf(String str) {
        return (State) Enum.valueOf(State.class, str);
    }

    public static State[] values() {
        return (State[]) $VALUES.clone();
    }
}
