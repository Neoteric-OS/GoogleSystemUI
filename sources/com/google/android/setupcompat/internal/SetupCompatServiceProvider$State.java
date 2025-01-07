package com.google.android.setupcompat.internal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
final class SetupCompatServiceProvider$State {
    public static final /* synthetic */ SetupCompatServiceProvider$State[] $VALUES;
    public static final SetupCompatServiceProvider$State CONNECTED;

    /* JADX INFO: Fake field, exist only in values array */
    SetupCompatServiceProvider$State EF0;

    static {
        SetupCompatServiceProvider$State setupCompatServiceProvider$State = new SetupCompatServiceProvider$State("NOT_STARTED", 0);
        SetupCompatServiceProvider$State setupCompatServiceProvider$State2 = new SetupCompatServiceProvider$State("BIND_FAILED", 1);
        SetupCompatServiceProvider$State setupCompatServiceProvider$State3 = new SetupCompatServiceProvider$State("BINDING", 2);
        SetupCompatServiceProvider$State setupCompatServiceProvider$State4 = new SetupCompatServiceProvider$State("CONNECTED", 3);
        CONNECTED = setupCompatServiceProvider$State4;
        $VALUES = new SetupCompatServiceProvider$State[]{setupCompatServiceProvider$State, setupCompatServiceProvider$State2, setupCompatServiceProvider$State3, setupCompatServiceProvider$State4, new SetupCompatServiceProvider$State("DISCONNECTED", 4), new SetupCompatServiceProvider$State("SERVICE_NOT_USABLE", 5), new SetupCompatServiceProvider$State("REBIND_REQUIRED", 6)};
    }

    public static SetupCompatServiceProvider$State valueOf(String str) {
        return (SetupCompatServiceProvider$State) Enum.valueOf(SetupCompatServiceProvider$State.class, str);
    }

    public static SetupCompatServiceProvider$State[] values() {
        return (SetupCompatServiceProvider$State[]) $VALUES.clone();
    }
}
