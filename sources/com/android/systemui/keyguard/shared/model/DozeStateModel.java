package com.android.systemui.keyguard.shared.model;

import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
/* loaded from: classes.dex */
public final class DozeStateModel {
    public static final /* synthetic */ DozeStateModel[] $VALUES;
    public static final Companion Companion;
    public static final DozeStateModel DOZE;
    public static final DozeStateModel DOZE_AOD;
    public static final DozeStateModel DOZE_AOD_DOCKED;
    public static final DozeStateModel DOZE_AOD_PAUSED;
    public static final DozeStateModel DOZE_AOD_PAUSING;
    public static final DozeStateModel DOZE_PULSE_DONE;
    public static final DozeStateModel DOZE_PULSING;
    public static final DozeStateModel DOZE_PULSING_BRIGHT;
    public static final DozeStateModel DOZE_REQUEST_PULSE;
    public static final DozeStateModel DOZE_SUSPEND_TRIGGERS;
    public static final DozeStateModel FINISH;
    public static final DozeStateModel INITIALIZED;
    public static final DozeStateModel UNINITIALIZED;

    /* compiled from: go/retraceme 97024faaf470985feb378c0f604e66d2eca678dbbb151206fad2ab4525fd6f86 */
    public final class Companion {
    }

    static {
        DozeStateModel dozeStateModel = new DozeStateModel("UNINITIALIZED", 0);
        UNINITIALIZED = dozeStateModel;
        DozeStateModel dozeStateModel2 = new DozeStateModel("INITIALIZED", 1);
        INITIALIZED = dozeStateModel2;
        DozeStateModel dozeStateModel3 = new DozeStateModel("DOZE", 2);
        DOZE = dozeStateModel3;
        DozeStateModel dozeStateModel4 = new DozeStateModel("DOZE_SUSPEND_TRIGGERS", 3);
        DOZE_SUSPEND_TRIGGERS = dozeStateModel4;
        DozeStateModel dozeStateModel5 = new DozeStateModel("DOZE_AOD", 4);
        DOZE_AOD = dozeStateModel5;
        DozeStateModel dozeStateModel6 = new DozeStateModel("DOZE_REQUEST_PULSE", 5);
        DOZE_REQUEST_PULSE = dozeStateModel6;
        DozeStateModel dozeStateModel7 = new DozeStateModel("DOZE_PULSING", 6);
        DOZE_PULSING = dozeStateModel7;
        DozeStateModel dozeStateModel8 = new DozeStateModel("DOZE_PULSING_BRIGHT", 7);
        DOZE_PULSING_BRIGHT = dozeStateModel8;
        DozeStateModel dozeStateModel9 = new DozeStateModel("DOZE_PULSE_DONE", 8);
        DOZE_PULSE_DONE = dozeStateModel9;
        DozeStateModel dozeStateModel10 = new DozeStateModel("FINISH", 9);
        FINISH = dozeStateModel10;
        DozeStateModel dozeStateModel11 = new DozeStateModel("DOZE_AOD_PAUSED", 10);
        DOZE_AOD_PAUSED = dozeStateModel11;
        DozeStateModel dozeStateModel12 = new DozeStateModel("DOZE_AOD_PAUSING", 11);
        DOZE_AOD_PAUSING = dozeStateModel12;
        DozeStateModel dozeStateModel13 = new DozeStateModel("DOZE_AOD_DOCKED", 12);
        DOZE_AOD_DOCKED = dozeStateModel13;
        DozeStateModel[] dozeStateModelArr = {dozeStateModel, dozeStateModel2, dozeStateModel3, dozeStateModel4, dozeStateModel5, dozeStateModel6, dozeStateModel7, dozeStateModel8, dozeStateModel9, dozeStateModel10, dozeStateModel11, dozeStateModel12, dozeStateModel13};
        $VALUES = dozeStateModelArr;
        EnumEntriesKt.enumEntries(dozeStateModelArr);
        Companion = new Companion();
    }

    public static DozeStateModel valueOf(String str) {
        return (DozeStateModel) Enum.valueOf(DozeStateModel.class, str);
    }

    public static DozeStateModel[] values() {
        return (DozeStateModel[]) $VALUES.clone();
    }
}
