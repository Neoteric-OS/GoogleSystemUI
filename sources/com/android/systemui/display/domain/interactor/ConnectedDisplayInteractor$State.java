package com.android.systemui.display.domain.interactor;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class ConnectedDisplayInteractor$State {
    public static final /* synthetic */ ConnectedDisplayInteractor$State[] $VALUES;
    public static final ConnectedDisplayInteractor$State CONNECTED;
    public static final ConnectedDisplayInteractor$State CONNECTED_SECURE;
    public static final ConnectedDisplayInteractor$State DISCONNECTED;

    static {
        ConnectedDisplayInteractor$State connectedDisplayInteractor$State = new ConnectedDisplayInteractor$State("DISCONNECTED", 0);
        DISCONNECTED = connectedDisplayInteractor$State;
        ConnectedDisplayInteractor$State connectedDisplayInteractor$State2 = new ConnectedDisplayInteractor$State("CONNECTED", 1);
        CONNECTED = connectedDisplayInteractor$State2;
        ConnectedDisplayInteractor$State connectedDisplayInteractor$State3 = new ConnectedDisplayInteractor$State("CONNECTED_SECURE", 2);
        CONNECTED_SECURE = connectedDisplayInteractor$State3;
        ConnectedDisplayInteractor$State[] connectedDisplayInteractor$StateArr = {connectedDisplayInteractor$State, connectedDisplayInteractor$State2, connectedDisplayInteractor$State3};
        $VALUES = connectedDisplayInteractor$StateArr;
        EnumEntriesKt.enumEntries(connectedDisplayInteractor$StateArr);
    }

    public static ConnectedDisplayInteractor$State valueOf(String str) {
        return (ConnectedDisplayInteractor$State) Enum.valueOf(ConnectedDisplayInteractor$State.class, str);
    }

    public static ConnectedDisplayInteractor$State[] values() {
        return (ConnectedDisplayInteractor$State[]) $VALUES.clone();
    }
}
