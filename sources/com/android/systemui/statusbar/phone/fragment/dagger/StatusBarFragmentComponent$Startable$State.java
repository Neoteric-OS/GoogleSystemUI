package com.android.systemui.statusbar.phone.fragment.dagger;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes2.dex */
public final class StatusBarFragmentComponent$Startable$State {
    public static final /* synthetic */ StatusBarFragmentComponent$Startable$State[] $VALUES;
    public static final StatusBarFragmentComponent$Startable$State NONE;
    public static final StatusBarFragmentComponent$Startable$State STARTED;
    public static final StatusBarFragmentComponent$Startable$State STARTING;
    public static final StatusBarFragmentComponent$Startable$State STOPPED;
    public static final StatusBarFragmentComponent$Startable$State STOPPING;

    static {
        StatusBarFragmentComponent$Startable$State statusBarFragmentComponent$Startable$State = new StatusBarFragmentComponent$Startable$State("NONE", 0);
        NONE = statusBarFragmentComponent$Startable$State;
        StatusBarFragmentComponent$Startable$State statusBarFragmentComponent$Startable$State2 = new StatusBarFragmentComponent$Startable$State("STARTING", 1);
        STARTING = statusBarFragmentComponent$Startable$State2;
        StatusBarFragmentComponent$Startable$State statusBarFragmentComponent$Startable$State3 = new StatusBarFragmentComponent$Startable$State("STARTED", 2);
        STARTED = statusBarFragmentComponent$Startable$State3;
        StatusBarFragmentComponent$Startable$State statusBarFragmentComponent$Startable$State4 = new StatusBarFragmentComponent$Startable$State("STOPPING", 3);
        STOPPING = statusBarFragmentComponent$Startable$State4;
        StatusBarFragmentComponent$Startable$State statusBarFragmentComponent$Startable$State5 = new StatusBarFragmentComponent$Startable$State("STOPPED", 4);
        STOPPED = statusBarFragmentComponent$Startable$State5;
        $VALUES = new StatusBarFragmentComponent$Startable$State[]{statusBarFragmentComponent$Startable$State, statusBarFragmentComponent$Startable$State2, statusBarFragmentComponent$Startable$State3, statusBarFragmentComponent$Startable$State4, statusBarFragmentComponent$Startable$State5};
    }

    public static StatusBarFragmentComponent$Startable$State valueOf(String str) {
        return (StatusBarFragmentComponent$Startable$State) Enum.valueOf(StatusBarFragmentComponent$Startable$State.class, str);
    }

    public static StatusBarFragmentComponent$Startable$State[] values() {
        return (StatusBarFragmentComponent$Startable$State[]) $VALUES.clone();
    }
}
